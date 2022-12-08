package com.sinosoft.ie.booster.yypass.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.util.R;

import com.sinosoft.ie.booster.yypass.entity.SendMsgLogEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
public interface SendMsgLogService extends IService<SendMsgLogEntity> {

	public void create(SendMsgLogEntity entity);

}
