package com.sinosoft.ie.booster.common.database.engine;

import com.sinosoft.ie.booster.common.database.exception.ProduceException;
import com.sinosoft.ie.booster.common.database.metadata.model.DataModel;

import java.io.Serializable;

/**
 * 文件产生接口
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/17 21:47
 */
public interface TemplateEngine extends Serializable {
	/**
	 * 生成文档
	 *
	 * @param info    {@link DataModel}
	 * @param docName {@link String}
	 * @throws ProduceException ProduceException
	 */
	void produce(DataModel info, String docName) throws ProduceException;

}
