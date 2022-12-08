package com.sinosoft.ie.booster.admin.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysBillRuleEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.mapper.SysBillRuleMapper;
import com.sinosoft.ie.booster.admin.service.SysBillRuleService;
import com.sinosoft.ie.booster.admin.service.SysDeptService;
import com.sinosoft.ie.booster.common.core.constant.enums.SystemVariableEnum;
import com.sinosoft.ie.booster.common.core.util.PadUtil;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 单据规则
 *
 * @author booster code generator
 * @since 2021-08-13 16:44:44
 */
@Service
@RequiredArgsConstructor
public class SysBillRuleServiceImpl extends ServiceImpl<SysBillRuleMapper, SysBillRuleEntity> implements SysBillRuleService {

	private final SysDeptService sysDeptService;

	@Override
	public boolean isExistByFullName(String fullName, Long id) {
		QueryWrapper<SysBillRuleEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(SysBillRuleEntity::getFullName, fullName);
		if (id != null) {
			queryWrapper.lambda().ne(SysBillRuleEntity::getId, id);
		}
		return this.count(queryWrapper) > 0;
	}

	@Override
	public boolean isExistByEnCode(String enCode, Long id) {
		QueryWrapper<SysBillRuleEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(SysBillRuleEntity::getEncode, enCode);
		if (id != null) {
			queryWrapper.lambda().ne(SysBillRuleEntity::getId, id);
		}
		return this.count(queryWrapper) > 0;
	}

	@Override
	public boolean create(SysBillRuleEntity entity) {
		return this.save(entity);
	}

	@Override
	public boolean update(SysBillRuleEntity entity) {
		return this.updateById(entity);
	}

	@Override
	@Transactional
	public String getBillNumber(String enCode) {
		StringBuilder strNumber = new StringBuilder();
		QueryWrapper<SysBillRuleEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(SysBillRuleEntity::getEncode, enCode);
		SysBillRuleEntity entity = this.getOne(queryWrapper);
		if (entity != null) {
			String billNumberPrefix = getBillNumberPrefix(entity.getPrefix());
			//处理隔天、隔月、隔年流水号归0问题
			if (StringUtils.hasLength(entity.getOutputNumber())) {
				String serialDate = entity.getOutputNumber().substring(billNumberPrefix.length());
				serialDate = serialDate.substring(0, serialDate.length() - entity.getDigit());
				String thisDate = DateUtil.format(new Date(), entity.getDateFormat());
				if (!serialDate.equals(thisDate)) {
					entity.setThisNumber(0);
				}
				entity.setThisNumber(entity.getThisNumber() + 1);
			} else {
				entity.setThisNumber(1);
			}
			//拼接单据编码
			//前缀
			strNumber.append(billNumberPrefix);
			strNumber.append(DateUtil.format(new Date(), entity.getDateFormat()));
			strNumber.append(PadUtil.padRight(String.valueOf(entity.getThisNumber()), entity.getDigit(), '0'));
			//更新流水号
			entity.setOutputNumber(strNumber.toString());
			this.updateById(entity);
		} else {
			strNumber.append("单据规则不存在");
		}
		return strNumber.toString();
	}

	@Override
	public List<SysBillRuleEntity> getList() {
		QueryWrapper<SysBillRuleEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().orderByAsc(SysBillRuleEntity::getSort);
		return this.list(queryWrapper);
	}

	private String getBillNumberPrefix(String originPrefix) {
		String billNumberPrefix = "";
		if (originPrefix.startsWith("@")) {
			originPrefix = originPrefix.substring(1);
			SystemVariableEnum systemVariableEnum = SystemVariableEnum.toEnum(originPrefix);
			if (systemVariableEnum != null) {
				BoosterUser currentUser = SecurityUtils.getUser();
				if (currentUser != null) {
					switch (systemVariableEnum) {
						case DEPT_ID:
							billNumberPrefix = currentUser.getDeptId().toString();
							break;
						case DEPT_CODE:
							SysDeptEntity sysDept = sysDeptService.getById(currentUser.getDeptId());
							if (sysDept != null) {
								billNumberPrefix = sysDept.getCode();
							}
							break;
						case USER_ID:
							billNumberPrefix = currentUser.getId().toString();
							break;
						case USER_NAME:
							billNumberPrefix = currentUser.getUsername();
							break;
						default: break;
					}
				}
			}
		} else {
			billNumberPrefix = originPrefix;
		}
		return billNumberPrefix;
	}
}
