package com.sinosoft.ie.booster.taskflow.demo.controller;

import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import org.springframework.web.bind.annotation.RestController;
import com.sinosoft.ie.booster.taskflow.demo.model.taskfrom.TaskFromPagination;
import com.sinosoft.ie.booster.taskflow.demo.model.taskfrom.TaskFromForm;
import com.sinosoft.ie.booster.taskflow.demo.model.taskfrom.TaskFromListVO;
import com.sinosoft.ie.booster.taskflow.demo.model.taskfrom.TaskFromInfoVO;
import com.sinosoft.ie.booster.taskflow.demo.entity.TaskFromEntity;
import com.sinosoft.ie.booster.taskflow.demo.service.TaskFromService;
import com.sinosoft.ie.booster.visualdev.util.*;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.admin.api.feign.RemoteBillRuleService;
import com.sinosoft.ie.booster.admin.api.feign.RemoteUserService;
import com.sinosoft.ie.booster.admin.api.feign.RemotePositionService;
import com.sinosoft.ie.booster.admin.api.entity.SysPositionEntity;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import javax.validation.Valid;

/**
 *
 * task_from
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-11-07
 */
@RestController
@RequestMapping("/TaskFrom")
public class TaskFromController {

    @Autowired
    private TaskFromService taskFromService;
    @Autowired
    private RemoteUserService userService;
    @Autowired
    private RemoteBillRuleService billRuleService;
    @Autowired
    private RemotePositionService positionService;

    @GetMapping("/List")
    public R<PageListVO<TaskFromListVO>> list(TaskFromPagination pagination) {
        List<TaskFromEntity> entity = taskFromService.getlist(pagination);
        List<TaskFromListVO> listVo = JsonUtil.getJsonToList(entity,TaskFromListVO.class );
        PageListVO<TaskFromListVO> vo=new PageListVO<>();
        vo.setList(listVo);
        PaginationVO page=JsonUtil.getJsonToBean(pagination,PaginationVO.class);
        vo.setPagination(page);
        return R.ok(vo);
    }

    @GetMapping("/{id}")
    public R<TaskFromInfoVO> info(@PathVariable("id") String id) {
        TaskFromEntity entity = taskFromService.getInfo(id);
        TaskFromInfoVO vo = JsonUtil.getJsonToBean(entity, TaskFromInfoVO.class);
        return R.ok(vo);
    }

    @PostMapping
    public R<String> create(@RequestBody @Valid TaskFromForm taskFromForm) throws DataException {
        BoosterUser userInfo = SecurityUtils.getUser();
        TaskFromEntity entity = JsonUtil.getJsonToBean(taskFromForm, TaskFromEntity.class);
        taskFromService.create(entity);
        return R.ok("保存成功");
    }

    @PutMapping("/{id}")
    public R<String> update(@PathVariable("id") String id,@RequestBody @Valid TaskFromForm taskFromForm) {
        BoosterUser userInfo = SecurityUtils.getUser();
        TaskFromEntity entity = JsonUtil.getJsonToBean(taskFromForm, TaskFromEntity.class);
        taskFromService.update(id,entity);
        return R.ok("修改成功");
    }

    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable("id") String id) {
        TaskFromEntity entity = taskFromService.getInfo(id);
        taskFromService.delete(entity);
        return R.ok("删除成功");
    }

}
