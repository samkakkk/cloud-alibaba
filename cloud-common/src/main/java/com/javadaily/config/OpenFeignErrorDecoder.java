package com.javadaily.config;

import com.alibaba.fastjson.JSON;
import com.javadaily.base.ResultData;
import com.javadaily.exception.BizException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 解决Feign的异常包装，统一返回结果
 * @author jam
 * @date 2021/11/2 2:45 下午
 */
@Slf4j
public class OpenFeignErrorDecoder implements ErrorDecoder {
    /**
     * Feign异常解析
     * @param methodKey 方法名
     * @param response 响应体
     * @return BizException
     */
    @Override
    public Exception decode(String methodKey, Response response) {

        try {

            log.error("feign client error,method is {}:", methodKey);
            //获取数据
//            String errorContent = IOUtils.toString(response.body().asInputStream());
            String body = Util.toString(response.body().asReader(Charset.defaultCharset()));

            ResultData<?> resultData = JSON.parseObject(body,ResultData.class);
            if(!resultData.isSuccess()){
                return new BizException(resultData.getStatus(),resultData.getMessage());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new BizException("Feign client 调用异常");
    }
}
