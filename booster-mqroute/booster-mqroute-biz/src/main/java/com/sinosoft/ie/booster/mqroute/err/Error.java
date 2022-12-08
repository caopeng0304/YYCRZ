package com.sinosoft.ie.booster.mqroute.err;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Error extends RuntimeException{
	
	protected String msg;

}
