package com.sinosoft.ie.booster.infection.service.impl;

import com.sinosoft.ie.booster.infection.entity.TrtEntity;
import com.sinosoft.ie.booster.infection.mapper.TrtMapper;
import com.sinosoft.ie.booster.infection.service.TrtService;
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
 * trt
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-01-06 16:52:52
 */
@Service
public class TrtServiceImpl extends ServiceImpl<TrtMapper, TrtEntity> implements TrtService {
}