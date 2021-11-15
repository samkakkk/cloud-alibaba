package com.javadaily.exception;

import com.javadaily.base.ReturnCode;

/**
 * 自定义业务异常，有明确的业务语义，不需要记录Error日志
 * @author jam
 * @date 2021/10/29 10:33 上午
 */
public class BizException extends BaseException{

    private static final ReturnCode BUSINESS_EXCEPTION = ReturnCode.BUSINESS_EXCEPTION;

    /**
     * 默认构造函数
     */
    public BizException(){
        super(BUSINESS_EXCEPTION.getCode(),BUSINESS_EXCEPTION.getMessage());
    }

    public BizException(String errorMessage){
        super(BUSINESS_EXCEPTION.getCode(),errorMessage);
    }

    public BizException(int errorCode,String errorMessage){
        super(errorCode,errorMessage);
    }

    public BizException(String errorMessage,Throwable e){
        super(BUSINESS_EXCEPTION.getCode(),errorMessage,e);
    }

    public BizException(int errorCode,String errorMessage,Throwable e){
        super(errorCode,errorMessage,e);
    }


}
