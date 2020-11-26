package com.it.springcloud.controller;

import com.it.springcloud.service.OrderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 超时 异常时触发
 * @DefaultProperties 全局服务降级  方法上有@HystrixCommand时降级
 * 方法上有@HystrixCommand(fallbackMethod="")时,独立方法降级
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback="payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private OrderHystrixService orderHystrixService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    @HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Long id){
        String result = orderHystrixService.paymentInfo_OK(id);
        log.info("*******result:"+result);
        return result;
    }

    //    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod="paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3500")  //3秒钟以内就是正常的业务逻辑
    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = orderHystrixService.paymentInfo_TimeOut(id);
        log.info("*******result:"+result);
        return result;
    }

    //兜底方法
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，对付支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,(┬＿┬)";
    }

    //下面是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试,(┬＿┬)";
    }

}
