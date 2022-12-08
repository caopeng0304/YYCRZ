package com.sinosoft.ie.booster.common.encrypt.annotation;


import java.lang.annotation.*;

/**
 * @author 段鹏宇
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface FieldEncrypt {
	Algorithm algorithm() default Algorithm.SM2;
	/*Class<? extends IEncryptor> encryptor() default IEncryptor.class;*/
}
