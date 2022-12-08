package com.sinosoft.ie.booster.yypass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.yypass.entity.FileEntity;
import com.sinosoft.ie.booster.yypass.entity.PassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.entity.PassFileEntity;
import com.sinosoft.ie.booster.yypass.mapper.PassBasicInfoMapper;
import com.sinosoft.ie.booster.yypass.mapper.PassFileMapper;
import com.sinosoft.ie.booster.yypass.mapper.PassLogNoPowerMapper;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoExcelVO;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoPagination;
import com.sinosoft.ie.booster.yypass.service.PassBasicInfoService;
import com.sinosoft.ie.booster.yypass.service.PassBasicInfoStatisticsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class PassBasicInfoStatisticsServiceImpl extends ServiceImpl<PassBasicInfoMapper, PassBasicInfoEntity> implements PassBasicInfoStatisticsService {

  @Autowired
  private PassBasicInfoMapper passBasicInfoMapper;
	@Autowired
  private PassFileMapper passFileMapper;
	@Autowired
	private PassLogNoPowerMapper passLogNoPowerMapper;


	@Override
	public int getYrffzrDelay(PassBasicInfoPagination passBasicInfoPagination) throws Exception {
		BoosterUser userInfo = SecurityUtils.getUser();
		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getPersonState())){
			queryWrapper.inSql(true,"person_state",passBasicInfoPagination.getPersonState());
		}

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getNames())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getNames,passBasicInfoPagination.getNames()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getPhone())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getPhone,passBasicInfoPagination.getPhone()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getUnitId())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getUnitId,passBasicInfoPagination.getUnitId()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getUnitPhone())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getUnitPhone,passBasicInfoPagination.getUnitPhone()));
		}

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getYrPhone())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getYrPhone,passBasicInfoPagination.getYrPhone()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getYrUnitArr())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr()));
		}

		queryWrapper.eq("is_delete","1");
		queryWrapper.orderByDesc(true,"add_time");
		queryWrapper.orderByDesc(true,"person_state");
		System.out.println("1:"+queryWrapper.getExpression().getSqlSegment());


		List<PassBasicInfoEntity> userIPage=this.passBasicInfoMapper.selectList(queryWrapper);
		if (userIPage != null && userIPage.size() > 0){
			return userIPage.size();
		}else{
			return 0;
		}
	}


	@Override
	public int getEjglyjsToDo(PassBasicInfoPagination passBasicInfoPagination) throws Exception {
		BoosterUser userInfo = SecurityUtils.getUser();
		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();

		/*if ("-1".equals(passBasicInfoPagination.getPersonState()) || "1".equals(passBasicInfoPagination.getPersonState())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getAddPersonId,passBasicInfoPagination.getAddPersonId()));
		}else if ("140".equals(passBasicInfoPagination.getPersonState()) || "160".equals(passBasicInfoPagination.getPersonState())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr()));
		}else{
			List<String> own = new ArrayList<>();
			own.add("-1");
			own.add("1");
			List<String> power = new ArrayList<>();
			power.add("140");
			power.add("160");

			queryWrapper.lambda().and(a->a.eq(PassBasicInfoEntity::getAddPersonId,passBasicInfoPagination.getAddPersonId()).
					and(c->c.in(PassBasicInfoEntity::getPersonState,own))
					.or(b->b.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr()).
							and(d->d.in(PassBasicInfoEntity::getPersonState,power))));


		}*/
		if (StringUtils.isNotEmpty(passBasicInfoPagination.getPersonState())){
			queryWrapper.lambda().and(a->a.inSql(true,PassBasicInfoEntity::getPersonState,
					passBasicInfoPagination.getPersonState()));
		}
		if (StringUtils.isNotEmpty(passBasicInfoPagination.getYrUnitArr())){
			queryWrapper.lambda().and(a->a.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr()));
		}


		queryWrapper.eq("is_delete","1");
		queryWrapper.orderByDesc(true,"add_time");
		queryWrapper.orderByDesc(true,"person_state");
		System.out.println("1:"+queryWrapper.getExpression().getSqlSegment());

		List<PassBasicInfoEntity> userIPage=this.passBasicInfoMapper.selectList(queryWrapper);
		if (userIPage != null && userIPage.size() > 0){
			return userIPage.size();
		}else{
			return 0;
		}
	}

	@Override
	public int getToDo(PassBasicInfoPagination passBasicInfoPagination) throws Exception {
		BoosterUser userInfo = SecurityUtils.getUser();
		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getPersonState())){
			queryWrapper.inSql(true,"person_state",passBasicInfoPagination.getPersonState());
		}

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getNames())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getNames,passBasicInfoPagination.getNames()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getPhone())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getPhone,passBasicInfoPagination.getPhone()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getUnitId())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getUnitId,passBasicInfoPagination.getUnitId()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getUnitPhone())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getUnitPhone,passBasicInfoPagination.getUnitPhone()));
		}

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getYrPhone())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getYrPhone,passBasicInfoPagination.getYrPhone()));
		}

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getIdCard())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getIdCard,passBasicInfoPagination.getIdCard()));
		}

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getIsInout())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getIsInout,passBasicInfoPagination.getIsInout()));
		}

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getIsGrantFace())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getIsGrantFace,passBasicInfoPagination.getIsGrantFace()));
		}

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getYrUnitArr()) &&
				StringUtils.isNotEmpty(passBasicInfoPagination.getAddPersonId())){
			System.out.println(333);
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr()).
					or(a->a.eq(PassBasicInfoEntity::getAddPersonId,passBasicInfoPagination.getAddPersonId())));

		}else if (StringUtils.isNotEmpty(passBasicInfoPagination.getYrUnitArr())){
			System.out.println(111);
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr()));
		}else if (StringUtils.isNotEmpty(passBasicInfoPagination.getAddPersonId())){
			System.out.println(222);
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getAddPersonId,passBasicInfoPagination.getAddPersonId()));
		}

		queryWrapper.eq("is_delete","1");
		queryWrapper.orderByDesc(true,"add_time");
		queryWrapper.orderByDesc(true,"person_state");
		System.out.println("1:"+queryWrapper.getExpression().getSqlSegment());

		List<PassBasicInfoEntity> userIPage=this.passBasicInfoMapper.selectList(queryWrapper);
		if (userIPage != null && userIPage.size() > 0){
			return userIPage.size();
		}else{
			return 0;
		}
	}


}