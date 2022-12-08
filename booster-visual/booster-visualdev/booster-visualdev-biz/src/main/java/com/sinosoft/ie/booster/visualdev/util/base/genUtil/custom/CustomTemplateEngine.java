package com.sinosoft.ie.booster.visualdev.util.base.genUtil.custom;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Properties;

public class CustomTemplateEngine extends AbstractTemplateEngine {

	private static final String DOT_VM = ".vm";
	private VelocityEngine velocityEngine;

	private Map<String, Object> customParams;

	public CustomTemplateEngine() {

	}

	public CustomTemplateEngine(Map<String, Object> customParams) {
		this.customParams = customParams;
	}

	@Override
	public CustomTemplateEngine init(ConfigBuilder configBuilder) {
		super.init(configBuilder);
		if (null == this.velocityEngine) {
			Properties p = new Properties();
			p.setProperty(ConstVal.VM_LOAD_PATH_KEY, ConstVal.VM_LOAD_PATH_VALUE);
			p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, StringPool.EMPTY);
			p.setProperty("ISO-8859-1", Constants.UTF_8);
			p.setProperty("output.encoding", Constants.UTF_8);
			this.velocityEngine = new VelocityEngine(p);
		}

		return this;
	}

	@Override
	public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
		if (!StrUtil.isEmpty(templatePath)) {
			Template template = this.velocityEngine.getTemplate(templatePath, ConstVal.UTF8);
			FileOutputStream fos = new FileOutputStream(outputFile);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, ConstVal.UTF8));
			if (customParams != null) {
				objectMap.putAll(customParams);
			}
			template.merge(new VelocityContext(objectMap), writer);
			writer.close();
		}
	}

	@Override
	public String templateFilePath(String filePath) {
		if (null != filePath && !filePath.contains(".vm")) {
			return filePath + ".vm";
		} else {
			return filePath;
		}
	}
}
