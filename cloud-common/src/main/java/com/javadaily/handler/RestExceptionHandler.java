package com.javadaily.handler;

import com.javadaily.base.ResultData;
import com.javadaily.base.ReturnCode;
import com.javadaily.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * <code>RestExceptionHandler</code>
 * </p>
 * Description:
 * 服务层全局响应异常
 * 2021-10-29 去掉所有的ResponseStatus，因为feign只能处理200的响应码
 * @author javadaily
 * @date 2020/12/22 11:33
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * 默认全局异常处理。
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return ResultData.fail(ReturnCode.RC500.getCode(),e.getMessage());
    }

    /**
     * 自定义业务异常处理。
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(BaseException e) {
        log.error("业务异常 ex={}", e.getMessage(), e);
        return ResultData.fail(e.getErrorCode(),e.getMessage());
    }


    /**
     * 解决SpringSecurity访问异常
     * @author jam
     * @date 2021/4/21 16:12
     * @param e  AccessDeniedException
     * @return com.javadaily.base.ResultData<java.lang.String>
     */
    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultData<String> handleAccessDeniedException(AccessDeniedException  e) {
        log.error("访问异常 ex={}", e.getMessage(), e);
        return ResultData.fail(ReturnCode.ACCESS_DENIED.getCode(),ReturnCode.ACCESS_DENIED.getMessage());
    }

}
