package com.forezp.client.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by fangzhipeng on 2017/6/16.
 */
@Configuration
public class FeignConfig {

    /**
     * 注入该 Bean 之后， Feign 在远程调用失败后会进行重试。
     * @return
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, SECONDS.toMillis(1), 5);
    }

}
