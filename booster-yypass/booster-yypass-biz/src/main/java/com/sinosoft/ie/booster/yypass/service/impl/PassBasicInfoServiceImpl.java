package com.sinosoft.ie.booster.yypass.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.common.utils.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.visualdev.util.StringUtil;
import com.sinosoft.ie.booster.yypass.entity.FileEntity;
import com.sinosoft.ie.booster.yypass.entity.PassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.entity.PassFileEntity;
import com.sinosoft.ie.booster.yypass.entity.PersonTransferEntity;
import com.sinosoft.ie.booster.yypass.mapper.*;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.NoPassBasicInfoPagination;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoExcelVO;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoPagination;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoVO;
import com.sinosoft.ie.booster.yypass.service.PassBasicInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
@Service
public class PassBasicInfoServiceImpl extends ServiceImpl<PassBasicInfoMapper, PassBasicInfoEntity> implements PassBasicInfoService {

  @Autowired
  private PassBasicInfoMapper passBasicInfoMapper;
	@Autowired
  private PassFileMapper passFileMapper;
	@Autowired
	private PassLogNoPowerMapper passLogNoPowerMapper;
	@Autowired
	private PassLogMapper passLogMapper;
	@Autowired
	private PersonTransferMapper personTransferMapper;


	@Override
	public List<PassBasicInfoEntity> getList(PassBasicInfoEntity entity) {
		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.setEntity(entity);
		return passBasicInfoMapper.selectList(queryWrapper);
	}

	@Override
	public List<PassBasicInfoEntity> getTypeList(PassBasicInfoPagination apsSystemPagination, String dataType) {
		return null;
	}

	@Override
	public PassBasicInfoEntity getInfo(String id) {
		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(PassBasicInfoEntity::getId,id);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(PassBasicInfoEntity entity) {
		if (entity!=null){
			this.removeById(entity.getId());
		}
	}

	@Override
	public void create(PassBasicInfoEntity entity) {
		this.save(entity);
	}

	@Override
	public boolean update(String id, PassBasicInfoEntity entity) {
		entity.setId(id);
		return this.updateById(entity);
	}

	@Override
	public IPage<PassBasicInfoEntity> getEjglyjsToDo(PassBasicInfoPagination passBasicInfoPagination) throws Exception {
		BoosterUser userInfo = SecurityUtils.getUser();
		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();

		if (passBasicInfoPagination.getStartTime1() != null && passBasicInfoPagination.getEndTime1() != null){
			queryWrapper.lambda().between(PassBasicInfoEntity::getStartTime,passBasicInfoPagination.getStartTime1(),
					passBasicInfoPagination.getEndTime1());
		}

		if (passBasicInfoPagination.getStartTime2() != null && passBasicInfoPagination.getEndTime2() != null){
			queryWrapper.lambda().between(PassBasicInfoEntity::getEndTime,passBasicInfoPagination.getStartTime2(),
					passBasicInfoPagination.getEndTime2());
		}

		if (StringUtils.isNotEmpty(passBasicInfoPagination.getPersonType())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getPersonType,passBasicInfoPagination.getPersonType()));
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

		/*if ("-1".equals(passBasicInfoPagination.getPersonState()) || "1".equals(passBasicInfoPagination.getPersonState())){ // 自己创建的
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getAddPersonId,passBasicInfoPagination.getAddPersonId()));
			queryWrapper.inSql(true,"person_state",passBasicInfoPagination.getPersonState());

		}else if ("140".equals(passBasicInfoPagination.getPersonState()) || "160".equals(passBasicInfoPagination.getPersonState())){  // 自己审批的
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr()));
			queryWrapper.inSql(true,"person_state",passBasicInfoPagination.getPersonState());
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

		Page<PassBasicInfoEntity> page=new Page<>(passBasicInfoPagination.getCurrentPage(), passBasicInfoPagination.getPageSize());
		IPage<PassBasicInfoEntity> userIPage=this.page(page,queryWrapper);
		return userIPage;
	}

	@Override
	public IPage<PassBasicInfoEntity> getToDo(PassBasicInfoPagination passBasicInfoPagination) throws Exception {
		BoosterUser userInfo = SecurityUtils.getUser();
		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
		if (passBasicInfoPagination.getStartTime1() != null && passBasicInfoPagination.getEndTime1() != null){
			queryWrapper.lambda().between(PassBasicInfoEntity::getStartTime,passBasicInfoPagination.getStartTime1(),
					passBasicInfoPagination.getEndTime1());
		}

		if (passBasicInfoPagination.getStartTime2() != null && passBasicInfoPagination.getEndTime2() != null){
			queryWrapper.lambda().between(PassBasicInfoEntity::getEndTime,passBasicInfoPagination.getStartTime2(),
					passBasicInfoPagination.getEndTime2());
		}

		if (StringUtils.isNotEmpty(passBasicInfoPagination.getPersonType())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getPersonType,passBasicInfoPagination.getPersonType()));
		}
		if (StringUtils.isNotEmpty(passBasicInfoPagination.getYrUnit())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnit,passBasicInfoPagination.getYrUnit()));
		}

		if (StringUtils.isNotEmpty(passBasicInfoPagination.getYrName())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrName,passBasicInfoPagination.getYrName()));
		}
		if (StringUtils.isNotEmpty(passBasicInfoPagination.getUnitName())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getUnitName,passBasicInfoPagination.getUnitName()));
		}


		if(StringUtils.isNotEmpty(passBasicInfoPagination.getPersonState())){
			queryWrapper.inSql(true,"person_state",passBasicInfoPagination.getPersonState());
		}

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getNames())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getNames,passBasicInfoPagination.getNames()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getPhone())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getPhone,passBasicInfoPagination.getPhone()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getUnitId())){  // 主管领导审批--申请信息 ，主管领导审批--延期信息
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

		}else if (StringUtils.isNotEmpty(passBasicInfoPagination.getYrUnitArr())){  //出入证管理--延期申请
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr()));
		}else if (StringUtils.isNotEmpty(passBasicInfoPagination.getAddPersonId())){
			System.out.println(222);
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getAddPersonId,passBasicInfoPagination.getAddPersonId()));
		}

		queryWrapper.eq("is_delete","1");
		queryWrapper.orderByDesc(true,"add_time");
		queryWrapper.orderByDesc(true,"person_state");
		System.out.println("1:"+queryWrapper.getExpression().getSqlSegment());

		Page<PassBasicInfoEntity> page=new Page<>(passBasicInfoPagination.getCurrentPage(), passBasicInfoPagination.getPageSize());
		IPage<PassBasicInfoEntity> userIPage=this.page(page,queryWrapper);
		return userIPage;
	}


	@Override
	public IPage<PassBasicInfoEntity> getYrffzrList(PassBasicInfoPagination passBasicInfoPagination) throws Exception {
		BoosterUser userInfo = SecurityUtils.getUser();
		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
		List<String> list = passLogMapper.getYrffzrApproval(passBasicInfoPagination);

		if (passBasicInfoPagination.getStartTime1() != null && passBasicInfoPagination.getEndTime1() != null){
			queryWrapper.lambda().between(PassBasicInfoEntity::getStartTime,passBasicInfoPagination.getStartTime1(),
					passBasicInfoPagination.getEndTime1());
		}

		if (passBasicInfoPagination.getStartTime2() != null && passBasicInfoPagination.getEndTime2() != null){
			queryWrapper.lambda().between(PassBasicInfoEntity::getEndTime,passBasicInfoPagination.getStartTime2(),
					passBasicInfoPagination.getEndTime2());
		}

		if (StringUtils.isNotEmpty(passBasicInfoPagination.getPersonType())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getPersonType,passBasicInfoPagination.getPersonType()));
		}
		if (StringUtils.isNotEmpty(passBasicInfoPagination.getYrUnit())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnit,passBasicInfoPagination.getYrUnit()));
		}

		if (StringUtils.isNotEmpty(passBasicInfoPagination.getYrName())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrName,passBasicInfoPagination.getYrName()));
		}
		if (StringUtils.isNotEmpty(passBasicInfoPagination.getUnitName())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getUnitName,passBasicInfoPagination.getUnitName()));
		}

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getPersonState())){
			//queryWrapper.inSql(true,"person_state",passBasicInfoPagination.getPersonState());
			queryWrapper.lambda().and(a->a.inSql(true,PassBasicInfoEntity::getPersonState,passBasicInfoPagination.getPersonState()).
					eq(PassBasicInfoEntity::getIsDelete,"1"));
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

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getIsGrantFace())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getIsGrantFace,passBasicInfoPagination.getIsGrantFace()));
		}

		if (StringUtils.isNotEmpty(passBasicInfoPagination.getAddPersonId()) && StringUtils.isNotEmpty(passBasicInfoPagination.getYrUnitArr())){
			if (list != null && list.size() > 0){
				queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getAddPersonId,passBasicInfoPagination.getAddPersonId()).
						or(a->a.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr())));
				queryWrapper.lambda().or(b->b.in(PassBasicInfoEntity::getId,list));
			}else{
				queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getAddPersonId,passBasicInfoPagination.getAddPersonId()).
						or(a->a.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr())));
			}
		}
		queryWrapper.eq("is_delete","1");
		queryWrapper.orderByDesc(true,"add_time");
		queryWrapper.orderByDesc(true,"person_state");
		System.out.println("1:"+queryWrapper.getExpression().getSqlSegment());


		Page<PassBasicInfoEntity> page=new Page<>(passBasicInfoPagination.getCurrentPage(), passBasicInfoPagination.getPageSize());
		IPage<PassBasicInfoEntity> userIPage=this.page(page,queryWrapper);
		return userIPage;
	}


	@Override
	public IPage<PassBasicInfoEntity> getYrffzrDelay(PassBasicInfoPagination passBasicInfoPagination) throws Exception {
		BoosterUser userInfo = SecurityUtils.getUser();
		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();

		if (passBasicInfoPagination.getStartTime1() != null && passBasicInfoPagination.getEndTime1() != null){
			queryWrapper.lambda().between(PassBasicInfoEntity::getStartTime,passBasicInfoPagination.getStartTime1(),
					passBasicInfoPagination.getEndTime1());
		}

		if (passBasicInfoPagination.getStartTime2() != null && passBasicInfoPagination.getEndTime2() != null){
			queryWrapper.lambda().between(PassBasicInfoEntity::getEndTime,passBasicInfoPagination.getStartTime2(),
					passBasicInfoPagination.getEndTime2());
		}

		if (StringUtils.isNotEmpty(passBasicInfoPagination.getPersonType())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getPersonType,passBasicInfoPagination.getPersonType()));
		}
		if (StringUtils.isNotEmpty(passBasicInfoPagination.getYrUnit())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnit,passBasicInfoPagination.getYrUnit()));
		}

		if (StringUtils.isNotEmpty(passBasicInfoPagination.getYrName())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrName,passBasicInfoPagination.getYrName()));
		}
		if (StringUtils.isNotEmpty(passBasicInfoPagination.getUnitName())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getUnitName,passBasicInfoPagination.getUnitName()));
		}

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

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getIsGrantFace())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getIsGrantFace,passBasicInfoPagination.getIsGrantFace()));
		}

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getYrUnitArr())){  //用人方负责人--延期申请
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr()));
		}

		queryWrapper.eq("is_delete","1");
		queryWrapper.orderByDesc(true,"add_time");
		queryWrapper.orderByDesc(true,"person_state");
		System.out.println("1:"+queryWrapper.getExpression().getSqlSegment());


		Page<PassBasicInfoEntity> page=new Page<>(passBasicInfoPagination.getCurrentPage(), passBasicInfoPagination.getPageSize());
		IPage<PassBasicInfoEntity> userIPage=this.page(page,queryWrapper);
		return userIPage;
	}


	@Override
	public IPage<PassBasicInfoEntity> selectParmer(PassBasicInfoPagination passBasicInfoPagination) throws Exception {
		BoosterUser userInfo = SecurityUtils.getUser();

		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();

		if (passBasicInfoPagination.getStartTime1() != null && passBasicInfoPagination.getEndTime1() != null){
			queryWrapper.lambda().between(PassBasicInfoEntity::getStartTime,passBasicInfoPagination.getStartTime1(),
					passBasicInfoPagination.getEndTime1());
		}

		if (passBasicInfoPagination.getStartTime2() != null && passBasicInfoPagination.getEndTime2() != null){
			queryWrapper.lambda().between(PassBasicInfoEntity::getEndTime,passBasicInfoPagination.getStartTime2(),
					passBasicInfoPagination.getEndTime2());
		}

		if (StringUtils.isNotEmpty(passBasicInfoPagination.getPersonType())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getPersonType,passBasicInfoPagination.getPersonType()));
		}
		if (StringUtils.isNotEmpty(passBasicInfoPagination.getYrUnit())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnit,passBasicInfoPagination.getYrUnit()));
		}

		if (StringUtils.isNotEmpty(passBasicInfoPagination.getYrName())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrName,passBasicInfoPagination.getYrName()));
		}
		if (StringUtils.isNotEmpty(passBasicInfoPagination.getUnitName())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getUnitName,passBasicInfoPagination.getUnitName()));
		}

		if(StringUtils.isNotEmpty(passBasicInfoPagination.getPersonState())){
			queryWrapper.lambda().and(a->a.inSql(true,PassBasicInfoEntity::getPersonState,passBasicInfoPagination.getPersonState()).
					and(b->b.eq(PassBasicInfoEntity::getIsDelete,"1")));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getNames())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getNames,passBasicInfoPagination.getNames()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getPhone())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getPhone,passBasicInfoPagination.getPhone()));
		}
		if(StringUtils.isNotEmpty(passBasicInfoPagination.getUnitId())){  //主管领导审批--证件管理
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getUnitId,passBasicInfoPagination.getUnitId()));
			// 查询之前的审批记录
			List<String> rjdwzgldList = passLogMapper.getEjdwzgldApproval(passBasicInfoPagination);
			if (rjdwzgldList != null && rjdwzgldList.size() > 0){
				queryWrapper.lambda().or(t->t.in(PassBasicInfoEntity::getId,rjdwzgldList));
			}
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
				StringUtils.isNotEmpty(passBasicInfoPagination.getAddPersonId())){  // 出入证申请-证件管理
			List<String> rjglyList = passLogMapper.getRjglyApproval(passBasicInfoPagination);
			if (rjglyList != null && rjglyList.size() > 0){
				queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr()).
						or(a->a.eq(PassBasicInfoEntity::getAddPersonId,passBasicInfoPagination.getAddPersonId())).
						or(b->b.in(PassBasicInfoEntity::getId,rjglyList)));
			}else{
				queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr()).
						or(a->a.eq(PassBasicInfoEntity::getAddPersonId,passBasicInfoPagination.getAddPersonId())));
			}
		}else if (StringUtils.isNotEmpty(passBasicInfoPagination.getYrUnitArr())){ // 用人方负责人--申请信息
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnitArr,passBasicInfoPagination.getYrUnitArr()));
		}else if (StringUtils.isNotEmpty(passBasicInfoPagination.getAddPersonId())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getAddPersonId,passBasicInfoPagination.getAddPersonId()));
		}

		if (StringUtils.isEmpty(passBasicInfoPagination.getAddPersonId()) && StringUtils.isEmpty(passBasicInfoPagination.getYrUnitArr())
		  && StringUtils.isEmpty(passBasicInfoPagination.getIdCard()) && StringUtils.isEmpty(passBasicInfoPagination.getUnitId())){
			/// 保卫部备案--证件管理
			List<String> list = passLogMapper.getApproval(passBasicInfoPagination);
			if (list != null && list.size() > 0){
				queryWrapper.lambda().or(t->t.in(PassBasicInfoEntity::getId,list));
			}
		}
		queryWrapper.eq("is_delete","1");
		queryWrapper.orderByDesc(true,"add_time");
		queryWrapper.orderByDesc(true,"person_state");
		System.out.println("1:"+queryWrapper.getExpression().getSqlSegment());


		Page<PassBasicInfoEntity> page=new Page<>(passBasicInfoPagination.getCurrentPage(), passBasicInfoPagination.getPageSize());
		IPage<PassBasicInfoEntity> userIPage=this.page(page,queryWrapper);
		return userIPage;
	}

	public R<List<ErrorMessage>> importPassBasicInfo(List<PassBasicInfoExcelVO> excelVOList){
		excelVOList.forEach(Excel -> {
			PassBasicInfoEntity entity= JsonUtil.getJsonToBean(Excel, PassBasicInfoEntity.class);

			// 设置
			entity.setId(RandomUtil.uuId());
			entity.setPersonState("-1");   // 导入默认为 未提交状态
			entity.setIsDelete("1");
			entity.setAddTime(new Date());

			Long sno = passBasicInfoMapper.getSNo();
			if (sno == null){
				sno = 1L;
			}
			entity.setSNo(sno+1);
			this.save(entity);
		});
		return R.ok();
	}

	public List<PassBasicInfoExcelVO> exportPassBasicInfo(PassBasicInfoPagination entity){
		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("is_delete","1");

		if (entity.getStartTime1() != null && entity.getEndTime1() != null){
			queryWrapper.lambda().between(PassBasicInfoEntity::getStartTime,entity.getStartTime1(),
					entity.getEndTime1());
		}

		if (entity.getStartTime2() != null && entity.getEndTime2() != null){
			queryWrapper.lambda().between(PassBasicInfoEntity::getEndTime,entity.getStartTime2(),
					entity.getEndTime2());
		}

		if (StringUtils.isNotEmpty(entity.getPersonType())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getPersonType,entity.getPersonType()));
		}
		if (StringUtils.isNotEmpty(entity.getYrUnit())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnit,entity.getYrUnit()));
		}

		if (StringUtils.isNotEmpty(entity.getYrName())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrName,entity.getYrName()));
		}
		if (StringUtils.isNotEmpty(entity.getUnitName())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getUnitName,entity.getUnitName()));
		}

		if(StringUtils.isNotEmpty(entity.getPersonState())){
			queryWrapper.lambda().and(a->a.inSql(true,PassBasicInfoEntity::getPersonState,entity.getPersonState()).
					and(b->b.eq(PassBasicInfoEntity::getIsDelete,"1")));
		}
		if(StringUtils.isNotEmpty(entity.getNames())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getNames,entity.getNames()));
		}
		if(StringUtils.isNotEmpty(entity.getPhone())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getPhone,entity.getPhone()));
		}

		if(StringUtils.isNotEmpty(entity.getUnitPhone())){
			queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getUnitPhone,entity.getUnitPhone()));
		}

		if(StringUtils.isNotEmpty(entity.getYrPhone())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getYrPhone,entity.getYrPhone()));
		}
		if(StringUtils.isNotEmpty(entity.getIdCard())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getIdCard,entity.getIdCard()));
		}

		if(StringUtils.isNotEmpty(entity.getIsInout())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getIsInout,entity.getIsInout()));
		}

		if(StringUtils.isNotEmpty(entity.getIsGrantFace())){
			queryWrapper.lambda().and(t->t.eq(PassBasicInfoEntity::getIsGrantFace,entity.getIsGrantFace()));
		}

		if(StringUtils.isNotEmpty(entity.getYrUnitArr()) &&
				StringUtils.isNotEmpty(entity.getAddPersonId())){  // 出入证申请-证件管理
			List<String> rjglyList = passLogMapper.getRjglyApproval(entity);
			if (rjglyList != null && rjglyList.size() > 0){
				queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnitArr,entity.getYrUnitArr()).
						or(a->a.eq(PassBasicInfoEntity::getAddPersonId,entity.getAddPersonId())).
						or(b->b.in(PassBasicInfoEntity::getId,rjglyList)));
			}else{
				queryWrapper.lambda().and(t->t.like(PassBasicInfoEntity::getYrUnitArr,entity.getYrUnitArr()).
						or(a->a.eq(PassBasicInfoEntity::getAddPersonId,entity.getAddPersonId())));
			}
		}

		queryWrapper.orderByDesc(true,"add_time");
		queryWrapper.orderByDesc(true,"person_state");
		List<PassBasicInfoEntity> list = passBasicInfoMapper.selectList(queryWrapper);
		List<PassBasicInfoExcelVO> listvo = JsonUtil.getJsonToList(list,PassBasicInfoExcelVO.class);
		if (listvo != null && listvo.size() > 0){
			for (PassBasicInfoExcelVO vo:listvo){
				PassFileEntity passFileEntity = new PassFileEntity();
				passFileEntity.setPassBasicInfoId(vo.getId());
				passFileEntity.setFileType("A");
				List<FileEntity> file1 = passFileMapper.getFileList(passFileEntity);
				if (file1 != null && file1.size() > 0){
					vo.setFile(file1.get(0).getFileName());
				}
			}
		}

/*		listvo.forEach(Excel -> {
			PassBasicInfoEntity entity1= JsonUtil.getJsonToBean(Excel, PassBasicInfoEntity.class);
			PassFileEntity passFileEntity = new PassFileEntity();
			passFileEntity.setPassBasicInfoId(entity1.getId());
			passFileEntity.setFileType("A");
			List<FileEntity> file1 = passFileMapper.getFileList(passFileEntity);
			if (file1 != null && file1.size() > 0){
				Excel.setFile(file1.get(0).getFileName());
			}

		});*/

		return listvo;
	}

	public void insertExcelPassBasicInfo(PassBasicInfoExcelVO excelVO){
		PassBasicInfoEntity entity= JsonUtil.getJsonToBean(excelVO, PassBasicInfoEntity.class);
		this.save(entity);
	}

	public Long getSNo(){
		return passBasicInfoMapper.getSNo();
	}

	public List<Map<String,Object>> groupPersonState(PassBasicInfoEntity entity){
		return passBasicInfoMapper.groupPersonState(entity);
	}

	public List<Map<String,Object>> groupPersonDelayState(PassBasicInfoEntity entity){
		return passBasicInfoMapper.groupPersonDelayState(entity);
	}
}