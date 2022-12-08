package com.sinosoft.ie.booster.yypass.controller;


import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoPagination;
import com.sinosoft.ie.booster.yypass.service.PassBasicInfoStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(tags = "pass_basic_info")
@RequestMapping("/PassBasicInfo")
public class StatisticsPassBasicInfoController {

	@Autowired
	PassBasicInfoStatisticsService passBasicInfoStatisticsService;


	@GetMapping("/groupPass")
	@ApiOperation(value = "出入证申请", notes = "出入证申请")
	public R<Object> groupPass(){
		try {
			BoosterUser userInfo= SecurityUtils.getUser();
			PassBasicInfoPagination p1 = new PassBasicInfoPagination();
			p1.setPersonState("-1,1,140,160");
			p1.setYrUnitArr(userInfo.getDeptId()+"");
			int a =  passBasicInfoStatisticsService.getEjglyjsToDo(p1);

			PassBasicInfoPagination p2 = new PassBasicInfoPagination();
			p2.setPersonState("14,16,240,260");
			p2.setYrUnitArr(userInfo.getDeptId()+"");
			int b =  passBasicInfoStatisticsService.getToDo(p2);
			return R.ok(a+b);
		}catch (Exception e){
			return R.failed("查询失败");
		}
	}


	@GetMapping("/groupRjgly")
	@ApiOperation(value = "主管领导审批", notes = "主管领导审批")
	public R<Object> groupRjgly(){
		try {
			BoosterUser userInfo= SecurityUtils.getUser();
			PassBasicInfoPagination p1 = new PassBasicInfoPagination();
			p1.setUnitId(userInfo.getDeptId()+"");
			p1.setPersonState("0,3,150,180");
			int a =  passBasicInfoStatisticsService.getToDo(p1);

			PassBasicInfoPagination p2 = new PassBasicInfoPagination();
			p2.setPersonState("6,9,15,18,250,280");
			p2.setUnitId(userInfo.getDeptId()+"");
			int b =  passBasicInfoStatisticsService.getToDo(p2);
			return R.ok(a+b);
		}catch (Exception e){
			return R.failed("查询失败");
		}
	}

	@GetMapping("/groupBwbba")
	@ApiOperation(value = "保卫部备案", notes = "保卫部备案")
	public R<Object> groupBwbba(){
		try {
			BoosterUser userInfo= SecurityUtils.getUser();
			PassBasicInfoPagination p1 = new PassBasicInfoPagination();
			p1.setPersonState("2,170");
			int a =  passBasicInfoStatisticsService.getToDo(p1);

			PassBasicInfoPagination p2 = new PassBasicInfoPagination();
			p2.setPersonState("8,17,270");
			int b =  passBasicInfoStatisticsService.getToDo(p2);
			return R.ok(a+b);
		}catch (Exception e){
			return R.failed("查询失败");
		}
	}

	@GetMapping("/groupYrffzr")
	@ApiOperation(value = "用人方负责人", notes = "用人方负责人")
	public R<Object> groupYrffzr(){
		try {
			BoosterUser userInfo= SecurityUtils.getUser();
			PassBasicInfoPagination p1 = new PassBasicInfoPagination();
			p1.setYrUnitArr(userInfo.getDeptId()+"");
			p1.setPersonState("-11,130");
			int a =  passBasicInfoStatisticsService.getToDo(p1);

			PassBasicInfoPagination p2 = new PassBasicInfoPagination();
			p2.setPersonState("12,13,240");
			p2.setYrUnitArr(userInfo.getDeptId()+"");
			int b =  passBasicInfoStatisticsService.getToDo(p2);
			return R.ok(a+b);
		}catch (Exception e){
			return R.failed("查询失败");
		}
	}



	@PostMapping("/groupEjglyjsPersonState")
	@ApiOperation(value = "出入证申请-申请信息，获取出入证状态统计信息", notes = "出入证申请-申请信息，获取出入证状态统计信息")
	public R<Object> groupEjglyjsPersonState(@RequestBody @Valid PassBasicInfoPagination passBasicInfoPagination){
		try {
			int i =  passBasicInfoStatisticsService.getEjglyjsToDo(passBasicInfoPagination);
			return R.ok(i);
		}catch (Exception e){
			System.out.println(e);
			return R.failed("查询失败");
		}
	}

	@PostMapping("/groupToDoPersonDelayState")
	@ApiOperation(value = "获取出入证延期状态统计信息", notes = "获取出入证延期状态统计信息")
	public R<Object> groupPersonDelayState(@RequestBody @Valid PassBasicInfoPagination passBasicInfoPagination){
		try {
			int list =  passBasicInfoStatisticsService.getToDo(passBasicInfoPagination);
			return R.ok(list);
		}catch (Exception e){
			System.out.println(e);
			return R.failed("查询失败");
		}
	}

	@PostMapping("/groupYrffzrDelayState")
	@ApiOperation(value = "获取出入证延期状态统计信息", notes = "获取出入证延期状态统计信息")
	public R<Object> groupYrffzrDelayState(@RequestBody @Valid PassBasicInfoPagination passBasicInfoPagination){
		try {
			int list =  passBasicInfoStatisticsService.getYrffzrDelay(passBasicInfoPagination);
			return R.ok(list);
		}catch (Exception e){
			System.out.println(e);
			return R.failed("查询失败");
		}
	}

	@PostMapping("/groupPersonState")
	@ApiOperation(value = "获取出入证申请状态统计信息", notes = "获取出入证申请状态统计信息")
	public R<Object> groupPersonState(@RequestBody @Valid PassBasicInfoPagination passBasicInfoPagination){
		try {
			int list =  passBasicInfoStatisticsService.getToDo(passBasicInfoPagination);
			return R.ok(list);
		}catch (Exception e){
			System.out.println(e);
			return R.failed("查询失败");
		}
	}



}
