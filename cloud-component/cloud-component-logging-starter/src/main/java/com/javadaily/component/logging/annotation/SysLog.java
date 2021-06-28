package com.javadaily.component.logging.annotation;

import java.lang.annotation.*;

/**
 * @author Jam
 * @date 日志操作注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	/**
	 * 日志内容
	 * @return {String}
	 */
	String value();

}
