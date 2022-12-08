package com.bstek.ureport.console.ureport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bstek.ureport.console.ureport.entity.ReportEntity;
import com.bstek.ureport.console.ureport.mapper.ReportMapper;
import com.bstek.ureport.console.ureport.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, ReportEntity> implements ReportService {

    @Override
    public List<ReportEntity> GetList() {
        QueryWrapper<ReportEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(ReportEntity::getCreateTime);
        return this.list(queryWrapper);
    }

    @Override
    public List<ReportEntity> Selector(String categoryId) {
        QueryWrapper<ReportEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ReportEntity::getCategoryId, categoryId);
        List<ReportEntity> list = this.list(queryWrapper);
        return list;
    }

    @Override
    public ReportEntity GetInfo(Long id) {
        QueryWrapper<ReportEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ReportEntity::getId,id);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean IsExistByFullName(String fullName, Long id) {
        QueryWrapper<ReportEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ReportEntity::getFullName, fullName);
        if (id != null) {
            queryWrapper.lambda().ne(ReportEntity::getId, id);
        }
        return this.count(queryWrapper) > 0;
    }

    @Override
    public boolean Delete(ReportEntity entity) {
        return this.removeById(entity.getId());
    }

    @Override
    public void Create(ReportEntity entity) {
        this.save(entity);
    }

    @Override
    public boolean Update(Long id, ReportEntity entity) {
        entity.setId(id);
        return this.updateById(entity);
    }

}
