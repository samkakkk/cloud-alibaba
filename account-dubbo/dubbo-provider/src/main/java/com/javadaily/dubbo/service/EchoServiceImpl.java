package com.javadaily.dubbo.service;

import com.javadaily.dubbo.api.EchoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 * <code>EchoServiceImpl</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/11/30 16:52
 */
@Service/*(interfaceClass = EchoService.class,timeout = 15000)*/
public class EchoServiceImpl implements EchoService {

    @Override
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
//    @Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8,ContentType.TEXT_PLAIN_UTF_8})
    public String echo(String message) {
        return "hello:" + message;
    }

}
