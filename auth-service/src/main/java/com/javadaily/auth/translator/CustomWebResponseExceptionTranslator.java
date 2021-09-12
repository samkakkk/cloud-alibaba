package com.javadaily.auth.translator;

import com.javadaily.base.ResultData;
import com.javadaily.base.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <code>CustomWebResponseExceptionTranslator</code>
 * </p>
 * Description:
 * Oauth2 异常翻译
 * @author Jam
 * @date 2021/1/29 9:23
 */
@Slf4j
@Component
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {


    @Override
    public ResponseEntity<ResultData<String>> translate(Exception e) throws Exception {
        log.error("认证服务器异常",e);

        ResultData<String> response = resolveException(e);

        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getHttpStatus()));
    }

    /**
     * 构建返回异常
     * @param e exception
     * @return
     */
    private ResultData<String> resolveException(Exception e) {
        // 初始值 500
        ReturnCode returnCode = ReturnCode.RC500;
        int httpStatus = HttpStatus.UNAUTHORIZED.value();
        //不支持的认证方式
        if(e instanceof UnsupportedGrantTypeException){
            returnCode = ReturnCode.UNSUPPORTED_GRANT_TYPE;
        }else if(e instanceof BadClientCredentialsException){
            returnCode = ReturnCode.CLIENT_AUTHENTICATION_FAILED;
        //用户名或密码异常
        }else if(e instanceof InvalidGrantException){
            returnCode = ReturnCode.USERNAME_OR_PASSWORD_ERROR;
        }

        ResultData<String> failResponse = ResultData.fail(returnCode.getCode(), returnCode.getMessage());
        failResponse.setHttpStatus(httpStatus);

        return failResponse;
    }


}
