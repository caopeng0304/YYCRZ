package com.sinosoft.ie.booster.common.database.engine;

import com.sinosoft.ie.booster.common.database.engine.freemark.FreemarkerTemplateEngine;
import com.sinosoft.ie.booster.common.database.engine.velocity.VelocityTemplateEngine;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 模板类型
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/21 20:07
 */
public enum EngineTemplateType implements Serializable {
	/**
	 * velocity 模板
	 */
	velocity("/template/velocity/",
			VelocityTemplateEngine.class,
			".vm"),
	/**
	 * freeMarker 模板
	 */
	freemarker("/template/freemarker/",
			FreemarkerTemplateEngine.class,
			".ftl");

	/**
	 * 模板目录
	 */
	@Getter
	@Setter
	private String templateDir;
	/**
	 * 模板驱动实现类类型
	 */
	@Getter
	@Setter
	private Class<? extends TemplateEngine> implClass;
	/**
	 * 后缀
	 */
	@Getter
	@Setter
	private String suffix;

	/**
	 * 构造
	 *
	 * @param freemarker {@link String}
	 * @param template   {@link Class}
	 */
	EngineTemplateType(String freemarker, Class<? extends TemplateEngine> template, String suffix) {
		this.templateDir = freemarker;
		this.implClass = template;
		this.suffix = suffix;
	}
}
