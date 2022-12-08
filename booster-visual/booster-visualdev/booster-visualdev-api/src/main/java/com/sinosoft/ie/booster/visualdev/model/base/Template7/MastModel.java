package com.sinosoft.ie.booster.visualdev.model.base.Template7;


import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import lombok.Data;

import java.util.List;

@Data
public class MastModel {

	//主表的属性
	private List<FieLdsModel> mastList;
	//系统自带的赋值
	private List<KeyModel> keyMastList;
}
