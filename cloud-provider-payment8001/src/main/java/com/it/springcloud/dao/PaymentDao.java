package com.it.springcloud.dao;

import com.it.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public int insert(Payment payment);

    public Payment findById(@Param("id") Long id);
}
