package com.sinosoft.ie.booster.common.database.process;

import com.sinosoft.ie.booster.common.database.metadata.model.DataModel;

import java.io.Serializable;

/**
 * 构建
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/22 21:08
 */
public interface Process extends Serializable {
	/**
	 * 处理
	 *
	 * @return {@link DataModel}
	 * @throws Exception Exception
	 */
	DataModel process() throws Exception;
}
