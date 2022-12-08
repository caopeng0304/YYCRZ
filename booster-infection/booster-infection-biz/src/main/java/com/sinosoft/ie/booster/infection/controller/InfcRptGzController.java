package com.sinosoft.ie.booster.infection.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinosoft.ie.booster.admin.api.entity.SysRegionEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteRegionService;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.infection.entity.*;
import com.sinosoft.ie.booster.infection.model.infcrptgz.*;
import com.sinosoft.ie.booster.infection.service.*;
import com.sinosoft.ie.booster.visualdev.util.DynDicUtil;
import com.sinosoft.ie.booster.visualdev.util.GeneraterSwapUtil;
import com.sinosoft.ie.booster.workflow.feign.FlowEngineApi;
import com.sinosoft.ie.booster.workflow.feign.FlowTaskApi;
import com.sinosoft.ie.booster.workflow.model.flowengine.FlowEngineInfoVO;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskSaveForm;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskSubmitForm;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 传染病个案报告卡
 *
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-01-06 16:52:51
 */
@Slf4j
@RestController
@Api(tags = "传染病个案报告卡")
@RequestMapping("/InfcRptGz")
public class InfcRptGzController {
	@Autowired
	private DynDicUtil dynDicUtil;
	@Autowired
	private GeneraterSwapUtil generaterSwapUtil;
	@Autowired
	private RemoteRegionService provinceService;
	@Autowired
	private FlowTaskApi flowTaskApi;
	@Autowired
	private FlowEngineApi flowEngineApi;
	@Autowired
	private InfcRptGzService infcrptgzservice;

	@Autowired
	private FlupService flupService;
	@Autowired
	private TestService testService;
	@Autowired
	private TracService tracService;
	@Autowired
	private TrtService trtService;

	/**
	 * 列表
	 *
	 * @param infcRptGzPagination
	 * @return
	 */
	@GetMapping
	public R list(InfcRptGzPagination infcRptGzPagination) throws IOException {
		List<InfcRptGzEntity> list = infcrptgzservice.getList(infcRptGzPagination);
		//处理id字段转名称，若无需转或者为空可删除

		for (InfcRptGzEntity entity : list) {
			entity.setDiagDiseCode(dynDicUtil.getDicName("diag_sta_type_code", entity.getDiagDiseCode()));
			List<String> provList1 = generaterSwapUtil.provinceData(entity.getRptAreaCode());
			if (provList1 != null && provList1.size() > 0) {
				StringBuilder restStr = new StringBuilder();
				List<SysRegionEntity> provinceEntities = provinceService.listByIds(provList1).getData();
				for (SysRegionEntity proventity1 : provinceEntities) {
					restStr.append(proventity1.getName() + "/");
				}
				if (restStr.length() != 0) {
					restStr.deleteCharAt(restStr.length() - 1);
				}
				entity.setRptAreaCode(String.valueOf(restStr));
			}
			entity.setGedCode(dynDicUtil.getDicName("ged_code", entity.getGedCode()));
			entity.setHregTypeCode(dynDicUtil.getDicName("hreg_type_code", entity.getHregTypeCode()));
			List<String> provList2 = generaterSwapUtil.provinceData(entity.getHergAreaCode());
			if (provList2 != null && provList2.size() > 0) {
				StringBuilder restStr = new StringBuilder();
				List<SysRegionEntity> provinceEntities = provinceService.listByIds(provList2).getData();
				for (SysRegionEntity proventity2 : provinceEntities) {
					restStr.append(proventity2.getName() + "/");
				}
				if (restStr.length() != 0) {
					restStr.deleteCharAt(restStr.length() - 1);
				}
				entity.setHergAreaCode(String.valueOf(restStr));
			}
			entity.setPopuTypeCode(dynDicUtil.getDicName("popu_type_code", entity.getPopuTypeCode()));
			entity.setCaseTypeCode(dynDicUtil.getDicName("case_type_code", entity.getCaseTypeCode()));
			entity.setDiagStaTypeCode(dynDicUtil.getDicName("diag_sta_type_code", entity.getDiagStaTypeCode()));
		}
		List<InfcRptGzListVO> listVO = JsonUtil.getJsonToList(list, InfcRptGzListVO.class);
		PageListVO vo = new PageListVO();
		vo.setList(listVO);
		PaginationVO page = JsonUtil.getJsonToBean(infcRptGzPagination, PaginationVO.class);
		vo.setPagination(page);
		return R.ok(vo);
	}

	/**
	 * 创建
	 *
	 * @param infcRptGzCrForm
	 * @return
	 */
	@PostMapping
	@Transactional
	public R create(@RequestBody @Valid InfcRptGzCrForm infcRptGzCrForm) throws DataException {
		String flowId = infcRptGzCrForm.getFlowId();
		R<FlowEngineInfoVO> flowEngineInfo = flowEngineApi.getInfo(flowId);
		if (flowEngineInfo == null) {
			return R.failed(String.format("流程Id：%s不存在", flowId));
		}

		InfcRptGzEntity entity = JsonUtil.getJsonToBean(infcRptGzCrForm, InfcRptGzEntity.class);
		infcrptgzservice.create(entity);

		List<FlupEntity> flupEntityList = infcRptGzCrForm.getFlupEntityList();
		if (flupEntityList != null) {
			for (FlupEntity flupEntity : flupEntityList) {
				flupEntity.setInfcRptId(entity.getId());
			}
			flupService.saveBatch(flupEntityList);
		}

		List<TestEntity> testEntityList = infcRptGzCrForm.getTestEntityList();
		if (testEntityList != null) {
			for (TestEntity testEntity : testEntityList) {
				testEntity.setInfcRptId(entity.getId());
			}
			testService.saveBatch(testEntityList);
		}

		List<TracEntity> tracEntityList = infcRptGzCrForm.getTracEntityList();
		if (tracEntityList != null) {
			for (TracEntity tracEntity : tracEntityList) {
				tracEntity.setInfcRptId(entity.getId());
			}
			tracService.saveBatch(tracEntityList);
		}

		List<TrtEntity> trtEntityList = infcRptGzCrForm.getTrtEntityList();
		if (trtEntityList != null) {
			for (TrtEntity trtEntity : trtEntityList) {
				trtEntity.setInfcRptId(entity.getId());
			}
			trtService.saveBatch(trtEntityList);
		}

		//接入工作流
		BoosterUser userInfo = SecurityUtils.getUser();
		String flowTitle = userInfo.getUsername() + "的" + flowEngineInfo.getData().getFullName();
		FlowTaskSaveForm flowTaskSaveForm = new FlowTaskSaveForm();
		flowTaskSaveForm.setId(null);
		flowTaskSaveForm.setProcessId(String.valueOf(entity.getId()));
		flowTaskSaveForm.setFlowId(flowId);
		flowTaskSaveForm.setFlowTitle(flowTitle);
		flowTaskSaveForm.setFlowUrgent(1);
		flowTaskSaveForm.setBillNo("");
		if (infcRptGzCrForm.getStatus().equals("1")) {
			flowTaskApi.saveFlowTask(flowTaskSaveForm);
			return R.ok(null, "保存成功");
		}
		FlowTaskSubmitForm flowTaskSubmitForm = BeanUtil.copyProperties(flowTaskSaveForm, FlowTaskSubmitForm.class);
		flowTaskSubmitForm.setFreeApprover(infcRptGzCrForm.getFreeApprover());
		flowTaskSubmitForm.setFormEntity(entity);
		flowTaskApi.submitFlowTask(flowTaskSubmitForm);
		return R.ok(null, "提交成功，请耐心等待");
	}


	/**
	 * 信息
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public R<InfcRptGzInfoVO> info(@PathVariable("id") Long id) {
		InfcRptGzEntity entity = infcrptgzservice.getInfo(id);
		InfcRptGzInfoVO vo = JsonUtil.getJsonToBean(entity, InfcRptGzInfoVO.class);
		List<FlupEntity> flupEntityList = infcrptgzservice.getFlupEntityList();
		if (flupEntityList != null && flupEntityList.size() > 0) {
			QueryWrapper<FlupEntity> queryWrapperFlup = new QueryWrapper();
			queryWrapperFlup.lambda().eq(FlupEntity::getInfcRptId, entity.getId());
			List<FlupEntity> list = flupService.list(queryWrapperFlup);
			list = JsonUtil.getJsonToList(list, FlupEntity.class);
			vo.setFlupEntityList(list);
		} else {
			List<FlupEntity> list = new ArrayList<>();
			vo.setFlupEntityList(list);
		}
		List<TestEntity> testEntityList = infcrptgzservice.getTestEntityList();
		if (testEntityList != null && testEntityList.size() > 0) {
			QueryWrapper<TestEntity> queryWrapperTest = new QueryWrapper();
			queryWrapperTest.lambda().eq(TestEntity::getInfcRptId, entity.getId());
			List<TestEntity> list = testService.list(queryWrapperTest);
			list = JsonUtil.getJsonToList(list, TestEntity.class);
			vo.setTestEntityList(list);
		} else {
			List<TestEntity> list = new ArrayList<>();
			vo.setTestEntityList(list);
		}
		List<TracEntity> tracEntityList = infcrptgzservice.getTracEntityList();
		if (tracEntityList != null && tracEntityList.size() > 0) {
			QueryWrapper<TracEntity> queryWrapperTrac = new QueryWrapper();
			queryWrapperTrac.lambda().eq(TracEntity::getInfcRptId, entity.getId());
			List<TracEntity> list = tracService.list(queryWrapperTrac);
			list = JsonUtil.getJsonToList(list, TracEntity.class);
			vo.setTracEntityList(list);
		} else {
			List<TracEntity> list = new ArrayList<>();
			vo.setTracEntityList(list);
		}
		List<TrtEntity> trtEntityList = infcrptgzservice.getTrtEntityList();
		if (trtEntityList != null && trtEntityList.size() > 0) {
			QueryWrapper<TrtEntity> queryWrapperTrt = new QueryWrapper();
			queryWrapperTrt.lambda().eq(TrtEntity::getInfcRptId, entity.getId());
			List<TrtEntity> list = trtService.list(queryWrapperTrt);
			list = JsonUtil.getJsonToList(list, TrtEntity.class);
			vo.setTrtEntityList(list);
		} else {
			List<TrtEntity> list = new ArrayList<>();
			vo.setTrtEntityList(list);
		}
		return R.ok(vo);
	}


	/**
	 * 更新
	 *
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	@Transactional
	public R update(@PathVariable("id") Long id, @RequestBody @Valid InfcRptGzUpForm infcRptGzUpForm) throws DataException {
		String flowId = infcRptGzUpForm.getFlowId();
		R<FlowEngineInfoVO> flowEngineInfo = flowEngineApi.getInfo(flowId);
		if (flowEngineInfo == null) {
			return R.failed(String.format("流程Id：%s不存在", flowId));
		}

		InfcRptGzEntity entity = infcrptgzservice.getInfo(id);
		if (entity != null) {
			infcrptgzservice.delete(entity);
			QueryWrapper<FlupEntity> queryWrapperFlupEntity = new QueryWrapper<>();
			queryWrapperFlupEntity.lambda().eq(FlupEntity::getInfcRptId, entity.getId());
			flupService.remove(queryWrapperFlupEntity);
			QueryWrapper<TestEntity> queryWrapperTestEntity = new QueryWrapper<>();
			queryWrapperTestEntity.lambda().eq(TestEntity::getInfcRptId, entity.getId());
			testService.remove(queryWrapperTestEntity);
			QueryWrapper<TracEntity> queryWrapperTracEntity = new QueryWrapper<>();
			queryWrapperTracEntity.lambda().eq(TracEntity::getInfcRptId, entity.getId());
			tracService.remove(queryWrapperTracEntity);
			QueryWrapper<TrtEntity> queryWrapperTrtEntity = new QueryWrapper<>();
			queryWrapperTrtEntity.lambda().eq(TrtEntity::getInfcRptId, entity.getId());
			trtService.remove(queryWrapperTrtEntity);
			entity = JsonUtil.getJsonToBean(infcRptGzUpForm, InfcRptGzEntity.class);
			entity.setId(id);
			infcrptgzservice.create(entity);
			List<FlupEntity> flupEntityList = infcRptGzUpForm.getFlupEntityList();
			if (flupEntityList != null) {
				for (FlupEntity flupEntity : flupEntityList) {
					flupEntity.setInfcRptId(entity.getId());
				}
				flupService.saveBatch(flupEntityList);
			}
			List<TestEntity> testEntityList = infcRptGzUpForm.getTestEntityList();
			if (testEntityList != null) {
				for (TestEntity testEntity : testEntityList) {
					testEntity.setInfcRptId(entity.getId());
				}
				testService.saveBatch(testEntityList);
			}
			List<TracEntity> tracEntityList = infcRptGzUpForm.getTracEntityList();
			if (tracEntityList != null) {
				for (TracEntity tracEntity : tracEntityList) {
					tracEntity.setInfcRptId(entity.getId());
				}
				tracService.saveBatch(tracEntityList);
			}
			List<TrtEntity> trtEntityList = infcRptGzUpForm.getTrtEntityList();
			if (trtEntityList != null) {
				for (TrtEntity trtEntity : trtEntityList) {
					trtEntity.setInfcRptId(entity.getId());
				}
				trtService.saveBatch(trtEntityList);
			}

			//接入工作流
			BoosterUser userInfo = SecurityUtils.getUser();
			String flowTitle = userInfo.getUsername() + "的" + flowEngineInfo.getData().getFullName();
			FlowTaskSaveForm flowTaskSaveForm = new FlowTaskSaveForm();
			flowTaskSaveForm.setId(String.valueOf(entity.getId()));
			flowTaskSaveForm.setProcessId(String.valueOf(entity.getId()));
			flowTaskSaveForm.setFlowId(flowId);
			flowTaskSaveForm.setFlowTitle(flowTitle);
			flowTaskSaveForm.setFlowUrgent(1);
			flowTaskSaveForm.setBillNo("");
			if (infcRptGzUpForm.getStatus().equals("1")) {
				flowTaskApi.saveFlowTask(flowTaskSaveForm);
				return R.ok(null, "更新成功");
			}
			FlowTaskSubmitForm flowTaskSubmitForm = BeanUtil.copyProperties(flowTaskSaveForm, FlowTaskSubmitForm.class);
			flowTaskSubmitForm.setFreeApprover(infcRptGzUpForm.getFreeApprover());
			flowTaskSubmitForm.setFormEntity(entity);
			flowTaskApi.submitFlowTask(flowTaskSubmitForm);
			return R.ok(null, "提交成功，请耐心等待");
		} else {
			return R.failed("更新失败，数据不存在");
		}
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@Transactional
	public R delete(@PathVariable("id") Long id) {
		InfcRptGzEntity entity = infcrptgzservice.getInfo(id);
		if (entity != null) {
			infcrptgzservice.delete(entity);
			QueryWrapper<FlupEntity> queryWrapperFlup = new QueryWrapper<>();
			queryWrapperFlup.lambda().eq(FlupEntity::getInfcRptId, entity.getId());
			flupService.remove(queryWrapperFlup);
			QueryWrapper<TestEntity> queryWrapperTest = new QueryWrapper<>();
			queryWrapperTest.lambda().eq(TestEntity::getInfcRptId, entity.getId());
			testService.remove(queryWrapperTest);
			QueryWrapper<TracEntity> queryWrapperTrac = new QueryWrapper<>();
			queryWrapperTrac.lambda().eq(TracEntity::getInfcRptId, entity.getId());
			tracService.remove(queryWrapperTrac);
			QueryWrapper<TrtEntity> queryWrapperTrt = new QueryWrapper<>();
			queryWrapperTrt.lambda().eq(TrtEntity::getInfcRptId, entity.getId());
			trtService.remove(queryWrapperTrt);
		}
		return R.ok(null, "删除成功");
	}

}
