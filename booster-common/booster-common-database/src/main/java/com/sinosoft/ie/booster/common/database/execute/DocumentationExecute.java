package com.sinosoft.ie.booster.common.database.execute;

import com.sinosoft.ie.booster.common.database.Configuration;
import com.sinosoft.ie.booster.common.database.engine.EngineFactory;
import com.sinosoft.ie.booster.common.database.engine.TemplateEngine;
import com.sinosoft.ie.booster.common.database.exception.BuilderException;
import com.sinosoft.ie.booster.common.database.metadata.model.DataModel;
import com.sinosoft.ie.booster.common.database.process.DataModelProcess;
import com.sinosoft.ie.booster.common.database.util.ExceptionUtils;

/**
 * 文档生成
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/4/1 22:51
 */
public class DocumentationExecute extends AbstractExecute {

	public DocumentationExecute(Configuration config) {
		super(config);
	}

	/**
	 * 执行
	 *
	 * @throws BuilderException BuilderException
	 */
	@Override
	public void execute() throws BuilderException {
		try {
			long start = System.currentTimeMillis();
			//处理数据
			DataModel dataModel = new DataModelProcess(config).process();
			//产生文档
			TemplateEngine produce = new EngineFactory(config.getEngineConfig()).newInstance();
			produce.produce(dataModel, getDocName(dataModel.getDatabase()));
			logger.debug("database document generation complete time consuming:{}ms",
					System.currentTimeMillis() - start);
		} catch (Exception e) {
			throw ExceptionUtils.mpe(e);
		}
	}
}
