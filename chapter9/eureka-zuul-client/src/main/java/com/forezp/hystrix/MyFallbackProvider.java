package com.forezp.hystrix;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fangzhipeng on 2017/6/28.
 */
@Component
class MyFallbackProvider  implements ZuulFallbackProvider {//Zuul 实现熔断功能需要实现 ZuulFallbackProvider 接口
    @Override
    public String getRoute() {//指定熔断功能应用于哪些路由的服务
//        return "eureka-client";
        return "*";   //这个指明熔断功能应用于所有的服务吧
    }

    @Override
    public ClientHttpResponse fallbackResponse() {//为进入熔断功能时执行的逻辑
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("oooops!error, i'm the fallback.".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
