package com.forezp.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fangzhipeng on 2017/6/13.
 *
 *
 */
@Configuration
public class RibbonConfig {

    @Bean
    @LoadBalanced //1、RestTemplate 结合了ribbon 开启了负载均衡功能
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
