package com.it.springcloud.service;

import com.it.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int insert(Payment payment);

    public Payment findById(@Param("id") Long id);
}
