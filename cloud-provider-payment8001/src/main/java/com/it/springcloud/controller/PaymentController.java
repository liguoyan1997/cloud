package com.it.springcloud.controller;

import com.it.springcloud.config.Ifnull;
import com.it.springcloud.entity.CommonResult;
import com.it.springcloud.entity.Payment;
import com.it.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int num = paymentService.insert(payment);
        return Ifnull.ifzero(num,port);
    }

    @GetMapping("/payment/getId/{id}")
    public CommonResult getId(@PathVariable("id") Long id){
        Payment payment = paymentService.findById(id);
        return Ifnull.ifnull(payment,id,port);
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String s : services) {
            log.info(s);
        }
        /*获取服务的实例*/
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance serviceInstance : instances){
            log.info(serviceInstance.getServiceId()+"-"+serviceInstance.getHost()+"-"+serviceInstance.getPort()+"-"+serviceInstance.getUri());
        }
        return discoveryClient;
    }
}
