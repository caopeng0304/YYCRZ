package com.bstek.ureport.font.timesnewroman;

import com.bstek.ureport.export.pdf.font.FontRegister;

/**
 * @author
 * @since 2014年5月7日
 */
public class TimesNewRomanFontRegister implements FontRegister {

	@Override
    public String getFontName() {
		return "Times New Roman";
	}

	@Override
    public String getFontPath() {
		return "com/bstek/ureport/font/timesnewroman/TIMES.TTF";
	}
}
