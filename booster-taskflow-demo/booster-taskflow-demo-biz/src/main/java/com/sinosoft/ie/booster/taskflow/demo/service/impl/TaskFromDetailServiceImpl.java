package com.sinosoft.ie.booster.taskflow.demo.service.impl;

import com.sinosoft.ie.booster.taskflow.demo.entity.TaskFromDetailEntity;
import com.sinosoft.ie.booster.taskflow.demo.mapper.TaskFromDetailMapper;
import com.sinosoft.ie.booster.taskflow.demo.service.TaskFromDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.visualdev.util.*;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.hutool.core.util.StrUtil;
import java.util.*;

/**
 *
 * task_from_detail
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-09-10 14:47:39
 */
@Service
public class TaskFromDetailServiceImpl extends ServiceImpl<TaskFromDetailMapper, TaskFromDetailEntity> implements TaskFromDetailService {
}