package com.sinosoft.ie.booster.infection.service.impl;

import com.sinosoft.ie.booster.infection.entity.FlupEntity;
import com.sinosoft.ie.booster.infection.mapper.FlupMapper;
import com.sinosoft.ie.booster.infection.service.FlupService;
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
 * flup
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-01-06 16:52:52
 */
@Service
public class FlupServiceImpl extends ServiceImpl<FlupMapper, FlupEntity> implements FlupService {
}