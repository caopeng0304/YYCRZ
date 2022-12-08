package com.sinosoft.ie.booster.mqroute.service.impl;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.mqroute.cache.MqCache;
import com.sinosoft.ie.booster.mqroute.entity.ConsumerEntity;
import com.sinosoft.ie.booster.mqroute.entity.TopicEntity;
import com.sinosoft.ie.booster.mqroute.init.ConsumerInit;
import com.sinosoft.ie.booster.mqroute.mapper.ConsumerMapper;
import com.sinosoft.ie.booster.mqroute.service.ConsumerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.visualdev.util.*;
import com.sinosoft.ie.booster.mqroute.model.consumer.ConsumerPagination;
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
 * consumer
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-03-16 15:50:58
 */
@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, ConsumerEntity> implements ConsumerService {

	@Autowired
	private ConsumerInit consumerInit;
	@Autowired
	private ConsumerMapper consumerMapper;
	
    @Override
    public List<ConsumerEntity> getList(ConsumerPagination consumerPagination){
        QueryWrapper<ConsumerEntity> queryWrapper=new QueryWrapper<>();
        //关键字（账户、姓名、手机）
        if(!"null".equals(String.valueOf(consumerPagination.getId()))){
            queryWrapper.lambda().and(t->t.like(ConsumerEntity::getId,consumerPagination.getId()));
        }

        if(!"null".equals(String.valueOf(consumerPagination.getConsumename()))){
            queryWrapper.lambda().and(t->t.like(ConsumerEntity::getConsumename,consumerPagination.getConsumename()));
        }

        //排序
        if(StrUtil.isEmpty(consumerPagination.getSidx())){
            queryWrapper.lambda().orderByDesc(ConsumerEntity::getId);
        }else{
            queryWrapper="asc".equals(consumerPagination.getSort().toLowerCase())?queryWrapper.orderByAsc(consumerPagination.getSidx()):queryWrapper.orderByDesc(consumerPagination.getSidx());
        }
        Page<ConsumerEntity> page=new Page<>(consumerPagination.getCurrentPage(), consumerPagination.getPageSize());
        List<ConsumerEntity>  list = consumerMapper.selectJoinTopic(page, queryWrapper);
        
        return list ;
       
    }
    @Override
    public List<ConsumerEntity> getTypeList(ConsumerPagination consumerPagination,String dataType){
        QueryWrapper<ConsumerEntity> queryWrapper=new QueryWrapper<>();
        //关键字（账户、姓名、手机）
        if(!"null".equals(String.valueOf(consumerPagination.getId()))){
            queryWrapper.lambda().and(t->t.like(ConsumerEntity::getId,consumerPagination.getId()));
        }

        if(!"null".equals(String.valueOf(consumerPagination.getConsumename()))){
            queryWrapper.lambda().and(t->t.like(ConsumerEntity::getConsumename,consumerPagination.getConsumename()));
        }

        //排序
        if(StrUtil.isEmpty(consumerPagination.getSidx())){
            queryWrapper.lambda().orderByDesc(ConsumerEntity::getId);
        }else{
            queryWrapper="asc".equals(consumerPagination.getSort().toLowerCase())?queryWrapper.orderByAsc(consumerPagination.getSidx()):queryWrapper.orderByDesc(consumerPagination.getSidx());
        }
        if("0".equals(dataType)){
            Page<ConsumerEntity> page=new Page<>(consumerPagination.getCurrentPage(), consumerPagination.getPageSize());
            IPage<ConsumerEntity> userIPage=this.page(page,queryWrapper);
            return consumerPagination.setData(userIPage.getRecords(),userIPage.getTotal());
        }else{
            return this.list(queryWrapper);
        }
    }

    @Override
    public ConsumerEntity getInfo(Long id){
        QueryWrapper<ConsumerEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(ConsumerEntity::getId,id);
        return this.getOne(queryWrapper);
    }

    @Override
    public R create(ConsumerEntity entity){
    	QueryWrapper<ConsumerEntity> queryWrapper=new QueryWrapper<>();
    	queryWrapper.eq("consumeName", entity.getConsumename());
    	queryWrapper.eq("topId", entity.getTopid());
    	queryWrapper.last("LIMIT 1");
    	ConsumerEntity  ent = this.getOne(queryWrapper);
        if(ent!=null) {
        	return R.failed("服务已注册过当前主题，请重新注册！");
        }
        this.save(entity);
        consumerInit.resetConsumer();
        return R.ok();
    }

    @Override
    public boolean update(Long id, ConsumerEntity entity){
        entity.setId(id);
        return this.updateById(entity);
    }
    @Override
    public void delete(ConsumerEntity entity){
        if(entity!=null){
            this.removeById(entity.getId());
        }
    }

    
	@Override
	public List<ConsumerEntity> getListByQuery(QueryWrapper<ConsumerEntity> queryWrapper) {
		
		return this.list(queryWrapper);
	}
}