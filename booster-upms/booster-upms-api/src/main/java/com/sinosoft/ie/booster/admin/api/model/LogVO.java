package com.sinosoft.ie.booster.admin.api.model;

import com.sinosoft.ie.booster.admin.api.entity.SysLogEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@Data
public class LogVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private SysLogEntity sysLog;

	private String username;

}
