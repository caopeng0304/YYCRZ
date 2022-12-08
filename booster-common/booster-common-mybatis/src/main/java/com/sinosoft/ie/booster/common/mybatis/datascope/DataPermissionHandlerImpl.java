package com.sinosoft.ie.booster.common.mybatis.datascope;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.sinosoft.ie.booster.common.core.constant.SecurityConstants;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;
import org.springframework.security.core.GrantedAuthority;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义数据权限
 * <p>
 *
 * @author haocy
 * @since 2021-07-30
 */
@Slf4j
@AllArgsConstructor
public class DataPermissionHandlerImpl implements DataPermissionHandler {
	private final DataSource dataSource;

	@Override
	@SneakyThrows
	public Expression getSqlSegment(Expression where, String mappedStatementId) {
		Class<?> clazz = Class.forName(mappedStatementId.substring(0, mappedStatementId.lastIndexOf(".")));
		String methodName = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			DataPermission annotation = method.getAnnotation(DataPermission.class);
			if (ObjectUtils.isNotEmpty(annotation) && (method.getName().equals(methodName) || (method.getName() + "_COUNT").equals(methodName))) {
				// 获取当前的用户
				BoosterUser loginUser = SecurityUtils.getUser();
				if (ObjectUtils.isNotEmpty(loginUser)) {
					return dataScopeFilter(loginUser, annotation.tableAlias(), where);
				}
			}
		}
		return where;
	}


	/**
	 * 构建过滤条件
	 *
	 * @param user       当前登录用户
	 * @param tableAlias 当前Mapper方法的表别名
	 * @param where      当前查询条件
	 * @return 构建后查询条件
	 */
	@SneakyThrows
	public Expression dataScopeFilter(BoosterUser user, String tableAlias, Expression where) {
		Expression expression = null;
		List<String> roleIdList = user.getAuthorities()
				.stream().map(GrantedAuthority::getAuthority)
				.filter(authority -> authority.startsWith(SecurityConstants.ROLE))
				.map(authority -> authority.split("_")[1])
				.collect(Collectors.toList());
		List<Entity> roleDataScopes = Db.use(dataSource)
				.query("SELECT ds_type,ds_scope FROM sys_role where role_id IN (" + CollUtil.join(roleIdList, ",") + ")");
		for (Entity roleDataScope : roleDataScopes) {
			Integer dsType = roleDataScope.getInt("ds_type");
			String dsScope = roleDataScope.getStr("ds_scope");
			// 查询全部
			if (DataScopeTypeEnum.ALL.getType() == dsType) {
				return where;
			}
			// 自定义
			if (DataScopeTypeEnum.CUSTOM.getType() == dsType) {
				ItemsList itemsList = new ExpressionList(Arrays.stream(dsScope.split(","))
						.map(StringValue::new)
						.collect(Collectors.toList())); // 把集合转变为JSQLParser需要的元素列表
				InExpression inExpression = new InExpression(buildColumn(tableAlias, "dept_id"), itemsList);
				expression = ObjectUtils.isNotEmpty(expression) ? new OrExpression(expression, inExpression) : inExpression;
			}
			// 查询本级及其下级
			if (DataScopeTypeEnum.OWN_CHILD_LEVEL.getType() == dsType) {
				ItemsList itemsList = new ExpressionList(Db.use(dataSource)
						.findBy("sys_dept_relation", "ancestor", user.getDeptId())
						.stream().map(entity -> entity.getStr("descendant"))
						.map(StringValue::new)
						.collect(Collectors.toList())); // 把集合转变为JSQLParser需要的元素列表
				InExpression inExpression = new InExpression(buildColumn(tableAlias, "dept_id"), itemsList);
				expression = ObjectUtils.isNotEmpty(expression) ? new OrExpression(expression, inExpression) : inExpression;
			}
			// 只查询本级
			if (DataScopeTypeEnum.OWN_LEVEL.getType() == dsType) {
				EqualsTo equalsTo = new EqualsTo();
				equalsTo.setLeftExpression(buildColumn(tableAlias, "dept_id"));
				equalsTo.setRightExpression(new LongValue(user.getDeptId()));
				expression = ObjectUtils.isNotEmpty(expression) ? new OrExpression(expression, equalsTo) : equalsTo;
			}
			// 只查询本人
			if (DataScopeTypeEnum.OWN_PERSONAL.getType() == dsType) {
				EqualsTo equalsTo = new EqualsTo();
				equalsTo.setLeftExpression(buildColumn(tableAlias, "create_by"));
				equalsTo.setRightExpression(new StringValue(user.getUsername()));
				expression = ObjectUtils.isNotEmpty(expression) ? new OrExpression(expression, equalsTo) : equalsTo;
			}
		}
		return ObjectUtils.isNotEmpty(where) ? new AndExpression(where, new Parenthesis(expression)) : expression;
	}

	/**
	 * 构建Column
	 *
	 * @param tableAlias 表别名
	 * @param columnName 字段名称
	 * @return 带表别名字段
	 */
	public static Column buildColumn(String tableAlias, String columnName) {
		if (StrUtil.isNotEmpty(tableAlias)) {
			columnName = tableAlias + "." + columnName;
		}
		return new Column(columnName);
	}
}
