package com.bstek.ureport.font.heiti;

import com.bstek.ureport.export.pdf.font.FontRegister;

/**
 * @author
 * @since 2014年5月7日
 */
public class HeiTiFontRegister implements FontRegister {

	@Override
    public String getFontName() {
		return "黑体";
	}

	@Override
    public String getFontPath() {
		return "com/bstek/ureport/font/heiti/SIMHEI.TTF";
	}
}
