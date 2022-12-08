package com.sinosoft.ie.booster.common.database.engine;

import com.sinosoft.ie.booster.common.database.exception.ProduceException;
import com.sinosoft.ie.booster.common.database.util.Assert;
import lombok.Getter;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 生成构造工厂
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/21 21:20
 */
public class EngineFactory implements Serializable {
	/**
	 * EngineConfig
	 */
	@Getter
	private EngineConfig engineConfig;

	public EngineFactory(EngineConfig configuration) {
		Assert.notNull(configuration, "EngineConfig can not be empty!");
		this.engineConfig = configuration;
	}

	private EngineFactory() {
	}

	/**
	 * 获取配置的数据库类型实例
	 *
	 * @return {@link TemplateEngine} 数据库查询对象
	 */
	public TemplateEngine newInstance() {
		try {
			//获取实现类
			Class<? extends TemplateEngine> query = this.engineConfig.getProduceType()
					.getImplClass();
			//获取有参构造
			Constructor<? extends TemplateEngine> constructor = query
					.getConstructor(EngineConfig.class);
			//实例化
			return (TemplateEngine) constructor.newInstance(engineConfig);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException
				| InvocationTargetException e) {
			throw new ProduceException(e);
		}
	}
}
