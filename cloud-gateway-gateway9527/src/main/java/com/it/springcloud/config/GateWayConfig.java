package com.it.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 第二种方式
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        /**
         * 获取网关
         */
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("lgy",r
                ->r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator1(RouteLocatorBuilder routeLocatorBuilder) {
        /**
         * 获取网关
         */
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("lgy",r
                ->r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
