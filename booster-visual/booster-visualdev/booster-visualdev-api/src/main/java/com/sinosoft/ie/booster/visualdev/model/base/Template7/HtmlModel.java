package com.sinosoft.ie.booster.visualdev.model.base.Template7;


import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.common.core.util.treeutil.SumTree;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class HtmlModel extends SumTree {

	//类型 栅格row,卡片card,子表table,主表mast
	private String boosKey;
	//json原始名称
	private String vmodel;
	//主表属性
	private FieLdsModel fieLdsModel;
	//子表list属性
	private List<FieLdsModel> tablFieLdsModel;
	//控件宽度
	private String span;
	//结束
	private String end = "0";

}
