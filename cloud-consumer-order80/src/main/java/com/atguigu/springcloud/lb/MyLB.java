package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {

    //原子类
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //坐标 访问次数  使用CAS 在不加锁的情况下 保证线程安全 数据一致性  保证每次访问的次数都是按顺序正确累加
    private final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;  //2147483647 = Interger.Max().Value(); 最大值 不能下标越界
        }while (!this.atomicInteger.compareAndSet(current,next));//第一个参数是期望值，第二个参数是修改值是修改值

        System.out.println("**********第几次访问，次数Next：" + next );
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstanceList) {
        int index = getAndIncrement() % serviceInstanceList.size();//得到服务器的下标位置 Ribbon负载均衡轮询原理

        return serviceInstanceList.get(index);
    }
}
