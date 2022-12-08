package com.sinosoft.ie.booster.sensitive.demo.entity;

import com.sinosoft.ie.booster.common.encrypt.annotation.Algorithm;
import com.sinosoft.ie.booster.common.encrypt.annotation.FieldEncrypt;
import com.sinosoft.ie.booster.common.encrypt.annotation.FieldSensitive;
import com.sinosoft.ie.booster.common.encrypt.annotation.SensitiveType;
import lombok.*;

import java.io.Serializable;


/**
 * @author 段鹏宇
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

    private Long id;

    @FieldSensitive()
	@FieldEncrypt(algorithm = Algorithm.SM2)
    private String username;

	@FieldSensitive(sensitiveType = SensitiveType.Password)
	private String password;

    @FieldSensitive(sensitiveType = SensitiveType.Email)
	@FieldEncrypt(algorithm = Algorithm.SM2)
    private String email;

	@FieldSensitive(sensitiveType = SensitiveType.Mobile)
	@FieldEncrypt(algorithm = Algorithm.SM2)
	private String sm2;

	@FieldSensitive(sensitiveType = SensitiveType.IdCard)
	private String sm3;

}
