package com.sinosoft.ie.booster.yypass.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.yypass.entity.PassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoExcelVO;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoPagination;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
public interface PassBasicInfoService extends IService<PassBasicInfoEntity> {

    List<PassBasicInfoEntity> getList(PassBasicInfoEntity entity);

    List<PassBasicInfoEntity> getTypeList(PassBasicInfoPagination apsSystemPagination, String dataType);

	PassBasicInfoEntity getInfo(String id);

    void delete(PassBasicInfoEntity entity);

    void create(PassBasicInfoEntity entity);

    boolean update(String id, PassBasicInfoEntity entity);

	IPage<PassBasicInfoEntity> selectParmer(PassBasicInfoPagination passBasicInfoPagination) throws Exception;

	IPage<PassBasicInfoEntity> getToDo(PassBasicInfoPagination passBasicInfoPagination) throws Exception;

	IPage<PassBasicInfoEntity> getYrffzrDelay(PassBasicInfoPagination passBasicInfoPagination) throws Exception;

	IPage<PassBasicInfoEntity> getYrffzrList(PassBasicInfoPagination passBasicInfoPagination) throws Exception;

	IPage<PassBasicInfoEntity> getEjglyjsToDo(PassBasicInfoPagination passBasicInfoPagination) throws Exception;

	R<List<ErrorMessage>> importPassBasicInfo(List<PassBasicInfoExcelVO> list);

	List<PassBasicInfoExcelVO> exportPassBasicInfo(PassBasicInfoPagination entity);

	Long getSNo();

	List<Map<String,Object>> groupPersonState(PassBasicInfoEntity entity);

	List<Map<String,Object>> groupPersonDelayState(PassBasicInfoEntity entity);
}
