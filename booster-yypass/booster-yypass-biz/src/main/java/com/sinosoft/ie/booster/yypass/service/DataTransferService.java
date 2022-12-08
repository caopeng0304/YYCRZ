package com.sinosoft.ie.booster.yypass.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.yypass.entity.PassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.DataTransferExcelVO;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoExcelVO;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoPagination;

import java.util.List;
import java.util.Map;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
public interface DataTransferService extends IService<PassBasicInfoEntity> {


	R<List<ErrorMessage>> importPassBasicInfo(List<DataTransferExcelVO> list);

}
