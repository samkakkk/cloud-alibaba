package com.javadaily.base;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * @author jam
 * @date 2021/11/1 1:59 下午
 */
@Documented
@ResponseBody
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Body {
}
