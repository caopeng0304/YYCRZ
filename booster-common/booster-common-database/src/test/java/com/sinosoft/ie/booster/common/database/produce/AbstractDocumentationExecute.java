package com.sinosoft.ie.booster.common.database.produce;

import com.sinosoft.ie.booster.common.database.Configuration;
import com.sinosoft.ie.booster.common.database.common.Properties;
import com.sinosoft.ie.booster.common.database.execute.DocumentationExecute;

/**
 * 抽象的数据库文档生成
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/30 18:28
 */
public abstract class AbstractDocumentationExecute implements Properties {

	/**
	 * 构建
	 *
	 * @param configuration {@link Configuration}
	 */
	void execute(Configuration configuration) {
		//生成
		new DocumentationExecute(configuration).execute();
	}

}
