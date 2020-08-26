package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data   //lombok 自动生成 实体类 get set方法 构造函数
@AllArgsConstructor //创建所有属性的构造方法
@NoArgsConstructor  //创建没有参数的构造方法
public class Payment implements Serializable {

    private Long id;
    private String serial;
}
