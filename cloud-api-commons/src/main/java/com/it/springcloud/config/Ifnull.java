package com.it.springcloud.config;

import com.it.springcloud.entity.CommonResult;

public class Ifnull {

    public static CommonResult ifzero(int num,String port){
        if(num > 0){
            return new CommonResult(200,"插入数据库成功,port:"+port,num);
        }else{
            return new CommonResult(444,"插入数据库失败！！！",null);
        }
    }

    public static CommonResult ifnull(Object data,Long id,String port){
        if(data != null){
            return new CommonResult(200,"查询成功,port:"+port,data);
        }else{
            return new CommonResult(444,"没有对应记录，查询ID："+id,null);
        }
    }

}
