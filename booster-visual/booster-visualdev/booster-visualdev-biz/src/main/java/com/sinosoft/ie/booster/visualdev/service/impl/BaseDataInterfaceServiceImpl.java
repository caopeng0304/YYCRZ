package com.sinosoft.ie.booster.visualdev.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.constant.type.*;
import com.sinosoft.ie.booster.visualdev.entity.BaseDataInterfaceEntity;
import com.sinosoft.ie.booster.visualdev.entity.BaseDbLinkEntity;
import com.sinosoft.ie.booster.visualdev.mapper.BaseDataInterfaceMapper;
import com.sinosoft.ie.booster.visualdev.model.datainterface.PaginationDataInterface;
import com.sinosoft.ie.booster.visualdev.service.BaseDataInterfaceService;
import com.sinosoft.ie.booster.visualdev.service.BaseDbLinkService;
import com.sinosoft.ie.booster.visualdev.util.DataSourceUtil;
import com.sinosoft.ie.booster.visualdev.util.HttpUtil;
import com.sinosoft.ie.booster.visualdev.util.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * 数据接口
 *
 * @author booster code generator
 * @since 2021-09-14
 */
@Service
public class BaseDataInterfaceServiceImpl extends ServiceImpl<BaseDataInterfaceMapper, BaseDataInterfaceEntity> implements BaseDataInterfaceService {
	@Autowired
	private BaseDbLinkService dblinkService;
	@Autowired
	private DataSourceUtil dataSourceUtils;
	@Autowired
	private OAuth2RestTemplate restTemplate;

	@Override
	public List<BaseDataInterfaceEntity> getList(PaginationDataInterface pagination) {
		QueryWrapper<BaseDataInterfaceEntity> queryWrapper = new QueryWrapper<>();
		//关键字
		if (!StrUtil.isEmpty(pagination.getKeyword())) {
			queryWrapper.lambda().and(
					t -> t.like(BaseDataInterfaceEntity::getFullName, pagination.getKeyword())
							.or().like(BaseDataInterfaceEntity::getEncode, pagination.getKeyword())
			);
		}
		//分类
		queryWrapper.lambda().eq(BaseDataInterfaceEntity::getCategoryId, pagination.getCategoryId());
		//排序
		queryWrapper.lambda().orderByAsc(BaseDataInterfaceEntity::getSort);
		Page<BaseDataInterfaceEntity> page = new Page<>(pagination.getCurrentPage(), pagination.getPageSize());
		IPage<BaseDataInterfaceEntity> userIPage = this.page(page, queryWrapper);
		return pagination.setData(userIPage.getRecords(), userIPage.getTotal());
	}

	@Override
	public List<BaseDataInterfaceEntity> getList() {
		QueryWrapper<BaseDataInterfaceEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseDataInterfaceEntity::getEnabledFlag, String.valueOf(IntegerNumber.ONE));
		return baseMapper.selectList(queryWrapper);
	}

	@Override
	public BaseDataInterfaceEntity getInfo(Long id) {
		QueryWrapper<BaseDataInterfaceEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseDataInterfaceEntity::getId, id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void create(BaseDataInterfaceEntity entity) throws DataException {
		this.save(entity);
	}

	@Override
	public boolean update(BaseDataInterfaceEntity entity, Long id) throws DataException {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public void delete(BaseDataInterfaceEntity entity) {
		this.removeById(entity.getId());
	}

	@Override
	public boolean isExistByFullName(String fullName, Long id) {
		QueryWrapper<BaseDataInterfaceEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(BaseDataInterfaceEntity::getFullName, fullName);
		if (id != null) {
			queryWrapper.lambda().ne(BaseDataInterfaceEntity::getId, id);
		}
		return this.count(queryWrapper) > 0;
	}

	@Override
	public List<Map<String, Object>> get(Long id, String sql) throws DataException {
		BaseDataInterfaceEntity entity = this.getInfo(id);
		return connection(entity.getDbLinkId(), sql);
	}

	@Override
	public R infoToId(Long id) {
		BaseDataInterfaceEntity entity = this.getInfo(id);
		List<Map<String, Object>> mapList;
		//静态数据
		try {
			if (entity.getDataType() == IntegerNumber.TWO) {
				Map<String, Object> map = JsonUtil.stringToMap(entity.getQuery());
				return R.ok(map);
			}
		} catch (Exception e) {
			try {
				List<Map<String, Object>> list = JsonUtil.getJsonToListMap(entity.getQuery());
				return R.ok(list);
			} catch (Exception exception) {
				Object obj = entity.getQuery();
				return R.ok(obj);
			}
		}
		//通过API查询
		try {
			if (entity.getDataType() == IntegerNumber.THREE) {
				String path = entity.getPath();
				if (RequestType.HTTPS.equals(path.substring(IntegerNumber.ZERO, IntegerNumber.FIVE).toLowerCase())) {
					path = composeRequestParameters(path, entity.getRequestParameters());
					JSONObject get = HttpUtil.httpsRequest(path, MethodType.GET.getMethod(), null);
					return R.ok(get);
				} else if (RequestType.HTTP.equals(path.substring(IntegerNumber.ZERO, IntegerNumber.FOUR).toLowerCase())) {
					path = composeRequestParameters(path, entity.getRequestParameters());
					JSONObject get = HttpUtil.httpRequest(path, MethodType.GET.getMethod(), null);
					return R.ok(get);
				} else if (RequestType.LOCAL.equals(path.substring(IntegerNumber.ZERO, IntegerNumber.FIVE).toLowerCase())) {
					// 内部接口访问URL模式：local://serviceId/path
					String[] pathArray = path.split("//");
					path = composeRequestParameters(pathArray[1], entity.getRequestParameters());
					return restTemplate.getForObject(String.format("http://%s", path), R.class);
				} else {
					return R.failed("接口仅支持HTTP、HTTPS和LOCAL方式");
				}
			}
		} catch (Exception e) {
			return R.failed("调用接口失败，请检查接口路径和参数");
		}
		//通过SQL查询
		try {
			//判断只能使用select
			if (entity.getQuery().length() < IntegerNumber.SIX || !"select".equals(entity.getQuery().trim().substring(IntegerNumber.ZERO, IntegerNumber.SIX).toLowerCase())) {
				return R.failed("该功能只支持Select语句");
			}
			//判断返回值不能为*
			if ("*".equals(entity.getQuery().trim().substring(IntegerNumber.SIX, IntegerNumber.SEVEN))) {
				return R.failed("请指定返回字段");
			}
			//判断只有一个SQL语句
			if (entity.getQuery().trim().contains(";")) {
				int i = entity.getQuery().indexOf(";");
				if (!"".equals(entity.getQuery().trim().substring(i + 1).trim())) {
					return R.failed("只能输入一个sql语句哦");
				}
			}
			//判断注解前是否有等号
			if (entity.getQuery().contains(AnnotationType.USER)) {
				if (!CompareType.EQUALS.equals(entity.getQuery().substring(entity.getQuery().trim().split(AnnotationType.USER)[0].length() - 1, entity.getQuery().trim().split(AnnotationType.USER)[0].length()))) {
					return R.failed(AnnotationType.USER + "前少了等号哦");
				}
			} else if (entity.getQuery().contains(AnnotationType.DEPARTMENT)) {
				if (!CompareType.EQUALS.equals(entity.getQuery().substring(entity.getQuery().trim().split(AnnotationType.DEPARTMENT)[0].length() - 1, entity.getQuery().trim().split(AnnotationType.DEPARTMENT)[0].length()))) {
					return R.failed(AnnotationType.DEPARTMENT + "前少了等号哦");
				}
			} else if (entity.getQuery().contains(AnnotationType.ORGANIZE)) {
				if (!CompareType.EQUALS.equals(entity.getQuery().substring(entity.getQuery().trim().split(AnnotationType.ORGANIZE)[0].length() - 1, entity.getQuery().trim().split(AnnotationType.ORGANIZE)[0].length()))) {
					return R.failed(AnnotationType.ORGANIZE + "前少了等号哦");
				}
			} else if (entity.getQuery().contains(AnnotationType.POSTION)) {
				if (!CompareType.EQUALS.equals(entity.getQuery().substring(entity.getQuery().trim().split(AnnotationType.POSTION)[0].length() - 1, entity.getQuery().trim().split(AnnotationType.POSTION)[0].length()))) {
					return R.failed(AnnotationType.POSTION + "前少了等号哦");
				}
			}
			mapList = this.get(id, entity.getQuery());
		} catch (Exception e) {
			log.error(e.getMessage());
			return R.failed("调用接口失败，请检查SQL语句是否有误");
		}
		return R.ok(mapList);
	}

	/**
	 * 拼接SQL语句并执行
	 *
	 * @param dbLinkId
	 * @param sql
	 * @return
	 * @throws DataException
	 */
	public List<Map<String, Object>> connection(Long dbLinkId, String sql) throws DataException {
		BaseDbLinkEntity linkEntity = dblinkService.getInfo(dbLinkId);
		Connection conn = null;
		if (linkEntity != null) {
			conn = JdbcUtil.getConn(linkEntity.getDbType(), linkEntity.getUserName(), linkEntity.getPassword(), linkEntity.getHost(), linkEntity.getPort(), linkEntity.getServiceName());
		} else {
			String url = dataSourceUtils.getUrl();
			String driverClassName = dataSourceUtils.getDriverClassName();
			conn = JdbcUtil.getConn(dataSourceUtils.getUserName(), dataSourceUtils.getPassword(), url, driverClassName);
		}
		BoosterUser boosterUser = SecurityUtils.getUser();
		if (boosterUser != null) {
			//判断是否有注解
			sql = sql.replaceAll(AnnotationType.USER, "'" + boosterUser.getId() + "'")
					.replaceAll(AnnotationType.ORGANIZE, "'" + boosterUser.getDeptId() + "'");
		}
		ResultSet resultSet =  JdbcUtil.query(conn, sql);
		return JdbcUtil.convertList2(resultSet);
	}

	private String composeRequestParameters(String path, String requestParameters) {
		List<Map<String, Object>> jsonToListMap = JsonUtil.getJsonToListMap(requestParameters);
		if (jsonToListMap != null) {
			path += "?";
			StringBuilder pathBuilder = new StringBuilder(path);
			for (Map<String, Object> map : jsonToListMap) {
				if (map != null) {
					String field = String.valueOf(map.get("field"));
					String value = String.valueOf(map.get("value"));
					pathBuilder.append(field).append(CompareType.EQUALS).append(value).append("&");
				}
			}
			path = pathBuilder.toString();
		}
		return path;
	}
}
