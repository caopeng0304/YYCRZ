package com.sinosoft.ie.booster.admin.api.model;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@Data
public class ImageCodeVO implements Serializable {

	private String code;

	private LocalDateTime expireTime;

	private BufferedImage image;

	public ImageCodeVO(BufferedImage image, String sRand, int defaultImageExpire) {
		this.image = image;
		this.code = sRand;
		this.expireTime = LocalDateTime.now().plusSeconds(defaultImageExpire);
	}

}
