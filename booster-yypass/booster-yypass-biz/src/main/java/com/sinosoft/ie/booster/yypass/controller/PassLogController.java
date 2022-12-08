package com.sinosoft.ie.booster.yypass.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.yypass.entity.*;
import com.sinosoft.ie.booster.yypass.mapper.PassLogMapper;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoUpForm;
import com.sinosoft.ie.booster.yypass.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * aps_system
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:39
 */
@Slf4j
@RestController
@Api(tags = "pass_log")
@RequestMapping("/PassLog")
public class PassLogController {

	@Autowired
	private PassLogService passLogService;
	@Autowired
	private PassLogNoPowerService passLogNoPowerService;
	@Autowired
	private PersonTransferService personTransferService;
	@Autowired
	private PassBasicInfoService passBasicInfoService;
	@Autowired
	private TransferCPassService transferCPassService;
	@Autowired
	private TransferPassLogService transferPassLogService;
	@Autowired
	private TransferPassLogNoPowerService transferPassLogNoPowerService;

	@PostMapping("/transfer")
	@Transactional
	@ApiOperation(value = "信息移交", notes = "信息移交")
	public R transfer(@RequestBody @Valid PersonTransferEntity personTransferEntity){
         try{

			 QueryWrapper<PersonTransferEntity> queryWrapper=new QueryWrapper<>();
			 queryWrapper.lambda().eq(PersonTransferEntity::getPersonId,personTransferEntity.getPersonId());
			 List<PersonTransferEntity> list = personTransferService.list(queryWrapper);
			 if (list != null && list.size() > 0){
				 String transferPersonName = list.get(0).getTransferPersonName();
				 return R.failed(null, "移交失败，此用户已经移交给："+transferPersonName);
			 }

			 String uuid = RandomUtil.uuId();
			 personTransferEntity.setId(uuid);
			 personTransferEntity.setAddTime(new Date());
			 personTransferService.create(personTransferEntity);
			 //创建出入证信息
             addTransferCPass(personTransferEntity);
			 // 审批出入证信息
             addTransferPassLog(personTransferEntity);
			 // 不能审批出入证信息
			 addTransferPassLogNoPower(personTransferEntity);



			 return R.ok(null, "操作成功");
		 }catch (Exception e){
			 return R.failed(null, "操作失败");
		 }
	}

	// 不能审批出入证信息
	public void addTransferPassLogNoPower(PersonTransferEntity personTransferEntity){
		// a、查交接人的数据 pass_log_no_power
		QueryWrapper<PassLogNoPowerEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(PassLogNoPowerEntity::getPersonId,personTransferEntity.getPersonId());
        List<PassLogNoPowerEntity> list = passLogNoPowerService.list(queryWrapper);
        if (list != null && list.size() > 0){
			List<TransferPassLogNoPowerEntity> batch = new ArrayList<>();
        	for (PassLogNoPowerEntity passLogNoPowerEntity : list){
				TransferPassLogNoPowerEntity transferPassLogNoPowerEntity = new TransferPassLogNoPowerEntity();
				transferPassLogNoPowerEntity.setId(RandomUtil.uuId());
				transferPassLogNoPowerEntity.setAddTime(new Date());
				transferPassLogNoPowerEntity.setPassBasicInfoId(passLogNoPowerEntity.getPassBasicInfoId());
				transferPassLogNoPowerEntity.setState(passLogNoPowerEntity.getState());
				transferPassLogNoPowerEntity.setPersonId(passLogNoPowerEntity.getPersonId());
				transferPassLogNoPowerEntity.setTransferPersonId(personTransferEntity.getTransferPersonId());
				batch.add(transferPassLogNoPowerEntity);
			}
        	transferPassLogNoPowerService.saveBatch(batch);
		}

		// b、承接之前的数据
		QueryWrapper<TransferPassLogNoPowerEntity> queryWrapper2=new QueryWrapper<>();
		queryWrapper2.lambda().eq(TransferPassLogNoPowerEntity::getTransferPersonId,personTransferEntity.getPersonId());
		List<TransferPassLogNoPowerEntity> list2 = transferPassLogNoPowerService.list(queryWrapper2);
		if (list2 != null && list2.size() > 0){
			List<TransferPassLogNoPowerEntity> batch2 = new ArrayList<>();
			for (TransferPassLogNoPowerEntity transferPassLogNoPowerEntity : list2){
				TransferPassLogNoPowerEntity transferPassLogEntity2 = new TransferPassLogNoPowerEntity();
				transferPassLogEntity2.setId(RandomUtil.uuId());
				transferPassLogEntity2.setAddTime(new Date());
				transferPassLogEntity2.setPassBasicInfoId(transferPassLogNoPowerEntity.getPassBasicInfoId());
				transferPassLogEntity2.setState(transferPassLogNoPowerEntity.getState());
				transferPassLogEntity2.setPersonId(transferPassLogNoPowerEntity.getPersonId());
				transferPassLogEntity2.setTransferPersonId(personTransferEntity.getTransferPersonId());
				batch2.add(transferPassLogEntity2);
			}
			transferPassLogNoPowerService.saveBatch(batch2);
		}

	}

	// 审批出入证信息
	public void addTransferPassLog(PersonTransferEntity personTransferEntity){
		// a、查交接人的数据  pass_log
		QueryWrapper<PassLogEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(PassLogEntity::getPersonId,personTransferEntity.getPersonId());
		List<PassLogEntity> list = passLogService.list(queryWrapper);
		if (list != null && list.size() > 0){
			List<TransferPassLogEntity> batch = new ArrayList<>();
			for (PassLogEntity entity : list){
				TransferPassLogEntity transferPassLogEntity = new TransferPassLogEntity();
				transferPassLogEntity.setId(RandomUtil.uuId());
				transferPassLogEntity.setAddTime(new Date());
				transferPassLogEntity.setPassBasicInfoId(entity.getPassBasicInfoId());
				transferPassLogEntity.setState(entity.getState());
				transferPassLogEntity.setPersonId(entity.getPersonId());
				transferPassLogEntity.setTransferPersonId(personTransferEntity.getTransferPersonId());
				batch.add(transferPassLogEntity);
			}
			transferPassLogService.saveBatch(batch);
		}
		// b、承接之前人的数据
		QueryWrapper<TransferPassLogEntity> queryWrapper2=new QueryWrapper<>();
		queryWrapper2.lambda().eq(TransferPassLogEntity::getTransferPersonId,personTransferEntity.getPersonId());
		List<TransferPassLogEntity> list2 = transferPassLogService.list(queryWrapper2);
		if (list2 != null && list2.size() > 0){
			List<TransferPassLogEntity> batch2 = new ArrayList();
			for (TransferPassLogEntity transferPassLogEntity : list2){
				TransferPassLogEntity transferPassLogEntity2 = new TransferPassLogEntity();
				transferPassLogEntity2.setId(RandomUtil.uuId());
				transferPassLogEntity2.setAddTime(new Date());
				transferPassLogEntity2.setPassBasicInfoId(transferPassLogEntity.getPassBasicInfoId());
				transferPassLogEntity2.setState(transferPassLogEntity.getState());
				transferPassLogEntity2.setPersonId(transferPassLogEntity.getPersonId());
				transferPassLogEntity2.setTransferPersonId(personTransferEntity.getTransferPersonId());
				batch2.add(transferPassLogEntity2);
			}
			transferPassLogService.saveBatch(batch2);
		}

	}

	//创建出入证信息
	public void addTransferCPass(PersonTransferEntity personTransferEntity){
		// a、查交接人创建的 出入证
		QueryWrapper<PassBasicInfoEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().eq(PassBasicInfoEntity::getAddPersonId,personTransferEntity.getPersonId());
		List<PassBasicInfoEntity> list = passBasicInfoService.list(queryWrapper);
		if (list != null && list.size() > 0){
			List<TransferCPassEntity> batch = new ArrayList<>();
			for (PassBasicInfoEntity entity : list){
				TransferCPassEntity transferCPassEntity = new TransferCPassEntity();
				transferCPassEntity.setId(RandomUtil.uuId());
				transferCPassEntity.setPassBasicInfoId(entity.getId());
				transferCPassEntity.setPersonId(personTransferEntity.getTransferPersonId());
				transferCPassEntity.setAddTime(new Date());
				batch.add(transferCPassEntity);
			}
			transferCPassService.saveBatch(batch);
		}
		// b.查交接人佣人权限的出入证
		QueryWrapper<TransferCPassEntity> queryWrapper2=new QueryWrapper<>();
		queryWrapper2.lambda().eq(TransferCPassEntity::getPersonId,personTransferEntity.getPersonId());
		List<TransferCPassEntity> list2 = transferCPassService.list(queryWrapper2);
		if (list2 != null && list2.size() > 0){
			List<TransferCPassEntity> batch2 = new ArrayList<>();
			for (TransferCPassEntity transferCPassEntity:list2){
				TransferCPassEntity transferCPassEntity2 = new TransferCPassEntity();
				transferCPassEntity2.setId(RandomUtil.uuId());
				transferCPassEntity2.setPassBasicInfoId(transferCPassEntity.getPassBasicInfoId());
				transferCPassEntity2.setPersonId(personTransferEntity.getTransferPersonId());
				transferCPassEntity2.setAddTime(new Date());
				batch2.add(transferCPassEntity2);
			}
			transferCPassService.saveBatch(batch2);
		}



	}




}
