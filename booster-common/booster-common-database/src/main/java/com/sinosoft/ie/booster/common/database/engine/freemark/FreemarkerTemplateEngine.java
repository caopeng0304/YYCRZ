package com.sinosoft.ie.booster.common.database.engine.freemark;

import com.sinosoft.ie.booster.common.database.engine.AbstractTemplateEngine;
import com.sinosoft.ie.booster.common.database.engine.EngineConfig;
import com.sinosoft.ie.booster.common.database.exception.ProduceException;
import com.sinosoft.ie.booster.common.database.metadata.model.DataModel;
import com.sinosoft.ie.booster.common.database.util.Assert;
import com.sinosoft.ie.booster.common.database.util.ExceptionUtils;
import com.sinosoft.ie.booster.common.database.util.FileUtils;
import com.sinosoft.ie.booster.common.database.util.StringUtils;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Locale;
import java.util.Objects;

import static com.sinosoft.ie.booster.common.database.constant.DefaultConstants.DEFAULT_ENCODING;
import static com.sinosoft.ie.booster.common.database.constant.DefaultConstants.DEFAULT_LOCALE;
import static com.sinosoft.ie.booster.common.database.engine.EngineTemplateType.freemarker;
import static com.sinosoft.ie.booster.common.database.util.FileUtils.getFileByPath;
import static com.sinosoft.ie.booster.common.database.util.FileUtils.isFileExists;

/**
 * freemarker template produce
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/17 21:40
 */
public class FreemarkerTemplateEngine extends AbstractTemplateEngine {
	/**
	 * freemarker 配置实例化
	 */
	private final Configuration configuration = new Configuration(
			Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

	{
		try {
			String path = getEngineConfig().getCustomTemplate();
			//自定义模板
			if (StringUtils.isNotBlank(path) && FileUtils.isFileExists(path)) {
				//获取父目录
				String parent = Objects.requireNonNull(getFileByPath(path)).getParent();
				//设置模板加载路径
				configuration.setDirectoryForTemplateLoading(new File(parent));
			}
			//加载自带模板
			else {
				//模板存放路径
				configuration.setTemplateLoader(
						new ClassTemplateLoader(this.getClass(), freemarker.getTemplateDir()));
			}
			//编码
			configuration.setDefaultEncoding(DEFAULT_ENCODING);
			//国际化
			configuration.setLocale(new Locale(DEFAULT_LOCALE));
		} catch (Exception e) {
			throw ExceptionUtils.mpe(e);
		}
	}

	public FreemarkerTemplateEngine(EngineConfig templateConfig) {
		super(templateConfig);
	}

	/**
	 * 生成文档
	 *
	 * @param info {@link DataModel}
	 * @throws ProduceException ProduceException
	 */
	@Override
	public void produce(DataModel info, String docName) throws ProduceException {
		Assert.notNull(info, "DataModel can not be empty!");
		String path = getEngineConfig().getCustomTemplate();
		try {
			Template template;
			// freemarker template
			// 如果自定义路径不为空文件也存在
			if (StringUtils.isNotBlank(path) && isFileExists(path)) {
				// 文件名称
				String fileName = new File(path).getName();
				template = configuration.getTemplate(fileName);
			}
			//获取系统默认的模板
			else {
				template = configuration
						.getTemplate(getEngineConfig().getFileType().getTemplateNamePrefix()
								+ freemarker.getSuffix());
			}
			// create file
			File file = getFile(docName);
			// writer freemarker
			try (Writer out = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(file), DEFAULT_ENCODING))) {
				// process
				template.process(info, out);
				// open the output directory
				openOutputDir();
			}
		} catch (IOException | TemplateException e) {
			throw ExceptionUtils.mpe(e);
		}
	}
}
