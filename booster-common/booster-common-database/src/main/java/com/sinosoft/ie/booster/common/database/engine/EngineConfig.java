package com.sinosoft.ie.booster.common.database.engine;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件生成配置
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/17 18:19
 */
@Data
@Builder
public class EngineConfig implements Serializable {
	/**
	 * 是否打开输出目录
	 */
	private boolean openOutputDir;
	/**
	 * 文件产生位置
	 */
	private String fileOutputDir;
	/**
	 * 生成文件类型
	 */
	private EngineFileType fileType;
	/**
	 * 生成实现
	 */
	private EngineTemplateType produceType;
	/**
	 * 自定义模板，模板需要和文件类型和使用模板的语法进行编写和处理，否则将会生成错误
	 */
	private String customTemplate;
	/**
	 * 文件名称
	 */
	private String fileName;
}
