package com.it.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements OrderHystrixService{

    @Override
    public String paymentInfo_OK(Long id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,当前服务已宕机 (┬＿┬)";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut , 当前服务已宕机 (┬＿┬)";
    }
}
