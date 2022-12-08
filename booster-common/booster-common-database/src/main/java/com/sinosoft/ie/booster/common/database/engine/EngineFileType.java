package com.sinosoft.ie.booster.common.database.engine;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 文件类型
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/21 21:08
 */

public enum EngineFileType implements Serializable {
	/**
	 * HTML
	 */
	HTML(".html", "documentation_html", "HTML文件"),
	/**
	 * WORD
	 */
	WORD(".doc", "documentation_word", "WORD文件"),
	/**
	 * MD
	 */
	MD(".md", "documentation_md", "Markdown文件");

	/**
	 * 文件后缀
	 */
	@Getter
	@Setter
	private String fileSuffix;
	/**
	 * 模板文件
	 */
	@Getter
	@Setter
	private String templateNamePrefix;
	/**
	 * 描述
	 */
	@Getter
	@Setter
	private String desc;

	EngineFileType(String type, String templateFile, String desc) {
		this.fileSuffix = type;
		this.templateNamePrefix = templateFile;
		this.desc = desc;
	}
}
