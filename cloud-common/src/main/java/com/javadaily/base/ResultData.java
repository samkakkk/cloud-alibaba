package com.javadaily.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 抽取公共返回结果
 * @author JAVA日知录
 * @date 2019/12/4 14:56
 */
@Data
//@ApiModel(value = "统一返回结果封装",description = "接口返回统一结果")
public class ResultData<T> {
    /** 结果状态 ,具体状态码参见ResultData.java*/
//    @ApiModelProperty(value = "状态码")
    private int status;
//    @ApiModelProperty(value = "响应信息")
    private String message;
//    @ApiModelProperty(value = "后端返回结果")
    private T data;
//    @ApiModelProperty(value = "后端响应状态")
    private boolean success;
//    @ApiModelProperty(value = "响应时间戳")
    private long timestamp ;

    private int httpStatus;

    public ResultData (){
        this.timestamp = System.currentTimeMillis();
    }


    public static <T> ResultData<T> success(String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC100.getCode());
        resultData.setSuccess(true);
        resultData.setMessage(message);
        return resultData;
    }

    public static <T> ResultData<T> success() {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC100.getCode());
        resultData.setSuccess(true);
        resultData.setMessage(ReturnCode.RC100.getMessage());
        return resultData;
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC100.getCode());
        resultData.setSuccess(true);
        resultData.setMessage(ReturnCode.RC100.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC999.getCode());
        resultData.setSuccess(false);
        resultData.setMessage(message);
        return resultData;
    }

    public static <T> ResultData<T> fail(int code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setSuccess(false);
        resultData.setMessage(message);
        return resultData;
    }

    public static <T>ResultData<T> fail() {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC999.getCode());
        resultData.setSuccess(false);
        resultData.setMessage(ReturnCode.RC999.getMessage());
        return resultData;
    }


    @JSONField(serialize = false, deserialize = false)
    @JsonIgnore
    public int getHttpStatus() {
        return httpStatus;
    }
}
