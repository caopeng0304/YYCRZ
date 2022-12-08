package com.bstek.ureport.font.kaiti;

import com.bstek.ureport.export.pdf.font.FontRegister;

/**
 * @author
 * @since 2014年5月7日
 */
public class KaiTiFontRegister implements FontRegister {

	@Override
    public String getFontName() {
		return "楷体";
	}

	@Override
    public String getFontPath() {
		return "com/bstek/ureport/font/kaiti/SIMKAI.TTF";
	}
}
