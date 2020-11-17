package com.it.springcloud.config;

import com.it.springcloud.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ifnull {

    public static CommonResult ifzero(int num){
        log.info("*****插入结果："+num);
        if(num > 0){
            return new CommonResult(200,"插入数据库成功",num);
        }else{
            return new CommonResult(444,"插入数据库失败！！！",null);
        }
    }

    public static CommonResult ifnull(Object data,Long id){
        log.info("*****查询结果："+data);
        if(data != null){
            return new CommonResult(200,"查询成功",data);
        }else{
            return new CommonResult(444,"没有对应记录，查询ID："+id,null);
        }
    }

}
