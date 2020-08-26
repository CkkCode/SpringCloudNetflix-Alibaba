package com.atguigu.springcloud.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //lombok 自动生成 实体类 get set方法 构造函数
@AllArgsConstructor //创建所有属性的构造方法
@NoArgsConstructor  //创建没有参数的构造方法
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this(code,message,null);
    }
}
