package com.sinosoft.ie.booster.yypass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDeptService;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.yypass.entity.FileEntity;
import com.sinosoft.ie.booster.yypass.entity.PassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.entity.PassFileEntity;
import com.sinosoft.ie.booster.yypass.mapper.PassBasicInfoMapper;
import com.sinosoft.ie.booster.yypass.mapper.PassFileMapper;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.DataTransferExcelVO;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoExcelVO;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoPagination;
import com.sinosoft.ie.booster.yypass.service.DataTransferService;
import com.sinosoft.ie.booster.yypass.service.PassBasicInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
@Service
public class DataTransferServiceImpl extends ServiceImpl<PassBasicInfoMapper, PassBasicInfoEntity> implements DataTransferService {

	@Autowired
	private PassBasicInfoService passBasicInfoService;

	@Autowired
	private RemoteDeptService remoteDeptService;



	public R<List<ErrorMessage>> importPassBasicInfo(List<DataTransferExcelVO> excelVOList){
		excelVOList.forEach(Excel -> {
			PassBasicInfoEntity entity= JsonUtil.getJsonToBean(Excel, PassBasicInfoEntity.class);
			// 设置
			entity.setId(RandomUtil.uuId());
			entity.setPersonState("4");   // 迁移过来的数据，备案通过
			entity.setIsDelete("4");
			entity.setIsInout("1");
            String personType = entity.getPersonType();
            String idCard = entity.getIdCard();
			String s = idCard.substring(idCard.indexOf(personType)+1,idCard.length());
			Long sno = getSno(s);
			entity.setSNo(sno);
			if ("护照".equals(entity.getCardType())){
				entity.setCardType("3");
			}else if ("身份证".equals(entity.getCardType())){
                entity.setCardType("1");
			}
            entity.setModes("1");

			if ("汉族".equals(entity.getNation())){
				entity.setNation("1");
			}

			// 获取系统组织
			R<SysDeptEntity>  r = remoteDeptService.infoByName(entity.getUnit());
			SysDeptEntity sysDeptEntity = r.getData();
			if (sysDeptEntity != null ){
				Long deptId = sysDeptEntity.getDeptId();
				entity.setUnitId(deptId+"");
			}
			entity.setYrUnit(entity.getUnit());
			entity.setYrName(entity.getUnitName());
			entity.setYrPhone(entity.getUnitPhone());
			passBasicInfoService.create(entity);

		});
		return R.ok();
	}

	public Long getSno(String s){
		try {
			Long sno = Long.parseLong(s);
			return sno;
		}catch (Exception e){
			return 0L;
		}

	}


}