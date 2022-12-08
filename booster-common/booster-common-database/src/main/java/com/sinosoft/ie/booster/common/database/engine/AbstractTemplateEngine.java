package com.sinosoft.ie.booster.common.database.engine;

import com.sinosoft.ie.booster.common.database.util.Assert;
import com.sinosoft.ie.booster.common.database.util.ExceptionUtils;
import com.sinosoft.ie.booster.common.database.util.StringUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static com.sinosoft.ie.booster.common.database.constant.DefaultConstants.MAC;
import static com.sinosoft.ie.booster.common.database.constant.DefaultConstants.WINDOWS;

/**
 * AbstractProduce
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/17 21:47
 */
@Data
public abstract class AbstractTemplateEngine implements TemplateEngine {
	/**
	 * 日志
	 */
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 模板配置
	 */
	private EngineConfig engineConfig;

	private AbstractTemplateEngine() {
	}

	public AbstractTemplateEngine(EngineConfig engineConfig) {
		Assert.notNull(engineConfig, "EngineConfig can not be empty!");
		this.engineConfig = engineConfig;
	}

	/**
	 * 获取文件，文件名格式为，数据库名_版本号.文件类型
	 *
	 * @param docName 文档名称
	 * @return {@link String}
	 */
	protected File getFile(String docName) {
		File file;
		//如果没有填写输出路径，默认当前项目路径下的doc目录
		if (StringUtils.isBlank(getEngineConfig().getFileOutputDir())) {
			String dir = System.getProperty("user.dir");
			file = new File(dir + "/doc");
		} else {
			file = new File(getEngineConfig().getFileOutputDir());
		}
		//不存在创建
		if (!file.exists()) {
			//创建文件夹
			boolean mkdir = file.mkdirs();
		}
		//文件后缀
		String suffix = getEngineConfig().getFileType().getFileSuffix();
		file = new File(file, docName + suffix);
		//设置文件产生位置
		getEngineConfig().setFileOutputDir(file.getParent());
		return file;
	}

	/**
	 * 打开文档生成的输出目录
	 */
	protected void openOutputDir() {
		//是否打开，如果是就打开输出路径
		if (getEngineConfig().isOpenOutputDir()
				&& StringUtils.isNotBlank(getEngineConfig().getFileOutputDir())) {
			try {
				//获取系统信息
				String osName = System.getProperty("os.name");
				if (osName != null) {
					if (osName.contains(MAC)) {
						Runtime.getRuntime().exec("open " + getEngineConfig().getFileOutputDir());
					} else if (osName.contains(WINDOWS)) {
						Runtime.getRuntime()
								.exec("cmd /c start " + getEngineConfig().getFileOutputDir());
					}
				}
			} catch (IOException e) {
				throw ExceptionUtils.mpe(e);
			}
		}
	}
}
