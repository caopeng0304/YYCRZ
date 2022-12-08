package com.sinosoft.ie.booster.yypass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.yypass.entity.FileEntity;
import com.sinosoft.ie.booster.yypass.entity.PassFileEntity;

import java.util.List;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
public interface PassFileService extends IService<PassFileEntity> {

	List<PassFileEntity> getList(PassFileEntity passFileEntity);

	List<FileEntity> getFileList(PassFileEntity passFileEntity);

	PassFileEntity getInfo(String id);

	void delete(PassFileEntity entity);

	void create(PassFileEntity entity);

	boolean update(String id, PassFileEntity entity);

	void logicDelete(String passBasicInfoId,String fileType);

}
