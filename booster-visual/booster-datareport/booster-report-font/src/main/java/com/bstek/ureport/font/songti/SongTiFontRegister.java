package com.bstek.ureport.font.songti;

import com.bstek.ureport.export.pdf.font.FontRegister;

/**
 * @author
 * @since 2014年5月7日
 */
public class SongTiFontRegister implements FontRegister {

	@Override
    public String getFontName() {
		return "宋体";
	}

	@Override
    public String getFontPath() {
		return "com/bstek/ureport/font/songti/SIMSUN.TTC";
	}
}
