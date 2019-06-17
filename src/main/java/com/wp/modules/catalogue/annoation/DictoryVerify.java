package com.wp.modules.catalogue.annoation;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 字典项校验
 * @author: zhanjian
 * @date: 2019年6月5日 上午11:31:49
 * @version: v1.0
 */
@Target({ FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DictoryVerify {
	
	String message() default "";

	String value() default "";
}
