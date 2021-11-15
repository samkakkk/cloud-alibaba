package com.javadaily.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jam
 * Base Exception is the parent of all exceptions
 * @date 2021/10/29 10:24 上午
 */
public abstract class BaseException extends RuntimeException{

    @Getter
    @Setter
    private Integer errorCode;

    public BaseException(String errorMessage){
        super(errorMessage);
    }


    public BaseException(int errorCode,String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public BaseException(String errorMessage,Throwable e){
        super(errorMessage,e);
    }

    public BaseException(int errorCode,String errorMessage,Throwable e){
        super(errorMessage,e);
        this.errorCode = errorCode;
    }

}
