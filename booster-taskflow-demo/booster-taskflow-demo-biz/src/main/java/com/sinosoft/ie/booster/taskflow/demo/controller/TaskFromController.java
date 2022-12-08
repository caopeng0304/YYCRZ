package com.sinosoft.ie.booster.taskflow.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinosoft.ie.booster.admin.api.feign.RemoteBillRuleService;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.taskflow.demo.entity.TaskFromDetailEntity;
import com.sinosoft.ie.booster.taskflow.demo.entity.TaskFromEntity;
import com.sinosoft.ie.booster.taskflow.demo.model.taskfrom.*;
import com.sinosoft.ie.booster.taskflow.demo.service.TaskFromDetailService;
import com.sinosoft.ie.booster.taskflow.demo.service.TaskFromService;
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
 *
 * task_from
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-09-10 14:47:39
 */
@Slf4j
@RestController
@Api(tags = "task_from")
@RequestMapping("/TaskFrom")
public class TaskFromController {
    @Autowired
    private GeneraterSwapUtil generaterSwapUtil;
    @Autowired
    private RemoteBillRuleService billRuleService;
    @Autowired
    private TaskFromService taskFromService;
	@Autowired
	private FlowTaskApi flowTaskApi;
	@Autowired
	private FlowEngineApi flowEngineApi;
    @Autowired
    private TaskFromDetailService taskFromDetailService;

    /**
     * 列表
     *
     * @param taskFromPagination
     * @return
     */
    @GetMapping
    public R<PageListVO<TaskFromListVO>> list(TaskFromPagination taskFromPagination)throws IOException{
        List<TaskFromEntity> list= taskFromService.getList(taskFromPagination);
        //处理id字段转名称，若无需转或者为空可删除

    for(TaskFromEntity entity:list){
            }
        List<TaskFromListVO> listVO=JsonUtil.getJsonToList(list,TaskFromListVO.class);
        PageListVO<TaskFromListVO> vo=new PageListVO<>();
        vo.setList(listVO);
        PaginationVO page=JsonUtil.getJsonToBean(taskFromPagination,PaginationVO.class);
        vo.setPagination(page);
        return R.ok(vo);
    }

    /**
     * 创建
     *
     * @param taskFromCrForm
     * @return
     */
    @PostMapping
    @Transactional
    public R<String> create(@RequestBody @Valid TaskFromCrForm taskFromCrForm) throws DataException {
        BoosterUser userInfo=SecurityUtils.getUser();
        String billNumber = billRuleService.getBillNumber("rwdbh").getData();
        taskFromCrForm.setTaskNumber(billNumber);
        TaskFromEntity entity=JsonUtil.getJsonToBean(taskFromCrForm, TaskFromEntity.class);
        entity.setTaskStatus(-1);
        taskFromService.create(entity);

        List<TaskFromDetailEntity> taskFromDetailEntityList= taskFromCrForm.getTaskFromDetailEntityList();
        if(taskFromDetailEntityList!=null){
            for(TaskFromDetailEntity taskFromDetailEntity:taskFromDetailEntityList) {
                taskFromDetailEntity.setParentId(entity.getId());
            }
            taskFromDetailService.saveBatch(taskFromDetailEntityList);
        }
        return R.ok(null, "新建成功");
}


    /**
    * 信息
    *
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public R<TaskFromInfoVO> info(@PathVariable("id") Long id){
        TaskFromEntity entity= taskFromService.getInfo(id);
        TaskFromInfoVO vo=JsonUtil.getJsonToBean(entity, TaskFromInfoVO.class);
        List<TaskFromDetailEntity> taskFromDetailEntityList=taskFromService.getTaskFromDetailEntityList();
        if(taskFromDetailEntityList!=null&&taskFromDetailEntityList.size()>0){
            QueryWrapper<TaskFromDetailEntity> queryWrapperTaskFromDetail=new QueryWrapper<>();
            queryWrapperTaskFromDetail.lambda().eq(TaskFromDetailEntity::getParentId,entity.getId());
            List<TaskFromDetailEntity> list=taskFromDetailService.list(queryWrapperTaskFromDetail);
            list=JsonUtil.getJsonToList(list,TaskFromDetailEntity.class);
            vo.setTaskFromDetailEntityList(list);
        }else{
            List<TaskFromDetailEntity> list=new ArrayList<>();
            vo.setTaskFromDetailEntityList(list);
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
    public R<String> update(@PathVariable("id") Long id,@RequestBody @Valid TaskFromUpForm taskFromUpForm) throws DataException {
        TaskFromEntity entity= taskFromService.getInfo(id);
        if(entity!=null){
            QueryWrapper<TaskFromDetailEntity> queryWrapperTaskFromDetailEntity=new QueryWrapper<>();
            queryWrapperTaskFromDetailEntity.lambda().eq(TaskFromDetailEntity::getParentId,entity.getId());
            taskFromDetailService.remove(queryWrapperTaskFromDetailEntity);
            BoosterUser userInfo = SecurityUtils.getUser();
            String billNumber = billRuleService.getBillNumber("rwdbh").getData();
            taskFromUpForm.setTaskNumber(billNumber);
            entity=JsonUtil.getJsonToBean(taskFromUpForm, TaskFromEntity.class);
            taskFromService.update(id, entity);
            List<TaskFromDetailEntity> taskFromDetailEntityList= taskFromUpForm.getTaskFromDetailEntityList();
            if(taskFromDetailEntityList!=null){
                for(TaskFromDetailEntity taskFromDetailEntity:taskFromDetailEntityList) {
                    taskFromDetailEntity.setParentId(entity.getId());
                }
                taskFromDetailService.saveBatch(taskFromDetailEntityList);
            }
        return R.ok(null, "更新成功");
        }else{
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
    public R<String> delete(@PathVariable("id") Long id){
        TaskFromEntity entity= taskFromService.getInfo(id);
        if(entity!=null){
            taskFromService.delete(entity);
            QueryWrapper<TaskFromDetailEntity> queryWrapperTaskFromDetail=new QueryWrapper<>();
            queryWrapperTaskFromDetail.lambda().eq(TaskFromDetailEntity::getParentId,entity.getId());
            taskFromDetailService.remove(queryWrapperTaskFromDetail);
        }
        return R.ok(null, "删除成功");
    }


	/**
	 * 操作：创建（生成流程）
	 *operator 1创建
	 * @param id
	 * @return
	 */
	@PutMapping("/state/{id}")
	@Transactional
	public R<String> start(@PathVariable("id") Long id,@RequestBody @Valid TaskFromUpForm taskFromUpForm) throws DataException {
		BoosterUser userInfo=SecurityUtils.getUser();
		String operator = taskFromUpForm.getOperator();
		String flowId = taskFromUpForm.getFlowId();
		R<FlowEngineInfoVO> flowEngineInfo = flowEngineApi.getInfo(flowId);
		if (flowEngineInfo == null) {
			return R.failed(String.format("流程Id：%s不存在", flowId));
		}
		TaskFromEntity entity= taskFromService.getInfo(id);
		//流程操作
		String flowTitle = userInfo.getUsername() + "的" + flowEngineInfo.getData().getFullName();
		FlowTaskSaveForm flowTaskSaveForm = new FlowTaskSaveForm();
		flowTaskSaveForm.setId(null);
		flowTaskSaveForm.setProcessId(String.valueOf(entity.getId()));
		flowTaskSaveForm.setFlowId(flowId);
		flowTaskSaveForm.setFlowTitle(flowTitle);
		flowTaskSaveForm.setFlowUrgent(1);
		flowTaskSaveForm.setBillNo("");
		FlowTaskSubmitForm flowTaskSubmitForm = BeanUtil.copyProperties(flowTaskSaveForm, FlowTaskSubmitForm.class);
		flowTaskSubmitForm.setFormEntity(entity);
		flowTaskApi.submitFlowTask(flowTaskSubmitForm);
		//业务表中状态修改
		if(entity!=null){
			if (operator.equals("1")){
				entity.setTaskStatus(2);//审核中
			}else {
				entity.setTaskStatus(1);//已通过
			}
			taskFromService.update(id, entity);
			return R.ok(null, "操作成功");
		}else{
			return R.failed("操作失败，数据不存在");
		}
	}
	/**
	 * 操作：流程回调操作
	 *
	 * @param
	 * @return
	 */
	@GetMapping("/flowcall")
	@Transactional
	public R<String> flowcall(String taskId,String taskNodeId,String handleStatus) throws DataException {
		BoosterUser userInfo=SecurityUtils.getUser();
		TaskFromEntity entity= taskFromService.getInfo(Long.valueOf(taskId));
		//业务表中状态修改
		if(entity!=null){
			entity.setTaskStatus(Integer.valueOf(handleStatus));//已通过
			taskFromService.update(Long.valueOf(taskId), entity);
			return R.ok(null, "操作成功");
		}else{
			return R.failed("操作失败，数据不存在");
		}
	}
}
