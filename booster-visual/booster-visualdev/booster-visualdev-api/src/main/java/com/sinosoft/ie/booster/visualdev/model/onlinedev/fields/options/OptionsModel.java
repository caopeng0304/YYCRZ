package com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.options;

import lombok.Data;

import java.util.List;

@Data
public class OptionsModel {
	private Integer id;
	private Integer value;
	private String label;
	private List<OptionsModel> children;
}
