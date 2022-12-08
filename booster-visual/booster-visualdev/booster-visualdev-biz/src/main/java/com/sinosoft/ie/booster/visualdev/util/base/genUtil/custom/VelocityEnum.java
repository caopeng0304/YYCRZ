package com.sinosoft.ie.booster.visualdev.util.base.genUtil.custom;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

public enum VelocityEnum {
	init;

	public void initVelocity() {
		Properties p = new Properties();
		p.setProperty(ConstVal.VM_LOAD_PATH_KEY, ConstVal.VM_LOAD_PATH_VALUE);
		p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, StringPool.EMPTY);
		p.setProperty("ISO-8859-1", Constants.UTF_8);
		p.setProperty("output.encoding", Constants.UTF_8);
		Velocity.init(p);
	}

}
