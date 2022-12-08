package com.bstek.ureport.console.ureport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bstek.ureport.console.ureport.entity.ReportEntity;

import java.util.List;

public interface ReportService extends IService<ReportEntity> {

    /**
     * 报表列表
     * @return
     */
    List<ReportEntity> GetList();

    /**
     * 分类
     * @param categoryId
     * @return
     */
    List<ReportEntity> Selector(String categoryId);

    /**
     * 预览/打开 报表
     * @param id
     * @return
     */
    ReportEntity GetInfo(Long id);

    /**
     * 验证名称重复
     *
	 * @param fullName 文件夹称
	 * @param id   主键值
	 */
    boolean IsExistByFullName(String fullName, Long id);

    /**
     *  删除报表
     */
    boolean Delete(ReportEntity entity);

    /**
     *  保存报表
     */
    void Create(ReportEntity entity);

    /**
     *  修改报表
     *
	 * @param id
	 * @param entity
	 * @return
     */
    boolean Update(Long id, ReportEntity entity);

}
