package com.wp.modules.catalogue.annoation;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 重复项校验
 * @author: zhanjian
 * @date: 2019年6月5日 上午11:31:49
 * @version: v1.0
 */
@Target({ FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatVerify {
	
	String databaseMessage() default ""; //数据库错误信息
	
	String excelMessage() default ""; // excel错误信息

	String value() default ""; //默认值
}
