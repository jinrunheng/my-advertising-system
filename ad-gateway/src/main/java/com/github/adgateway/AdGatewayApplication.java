package com.github.adgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @SpringCloudApplication 注解包括：
 *
 * @SpringBootApplication
 * @EnableDiscoveryClient
 * @EnableCircuitBreaker
 */
@SpringCloudApplication
@EnableZuulProxy
public class AdGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdGatewayApplication.class, args);
    }

}
