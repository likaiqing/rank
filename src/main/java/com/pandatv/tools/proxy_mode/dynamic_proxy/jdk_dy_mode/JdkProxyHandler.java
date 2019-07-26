package com.pandatv.tools.proxy_mode.dynamic_proxy.jdk_dy_mode;

import com.pandatv.zhaomu.proxy_mode.Star;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: likaiqing
 * @create: 2019-01-17 16:34
 *JDK 动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler 来处理。但是 JDK 动态代理有个缺憾，或者说特点：JDK 实现动态代理需要实现类通过接口定义业务方法
 **/
public class JdkProxyHandler {
    private Object realStar;

    public JdkProxyHandler(Star star) {
        super();
        this.realStar = star;
    }


    /**
     * 给真实对象生成一个代理对象实例
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(realStar.getClass().getClassLoader(), realStar.getClass().getInterfaces(), (proxy, method, args) -> {
            System.out.println("代理先进行谈判.....");
            Object object = method.invoke(realStar, args);
            System.out.println("演出完代理去收钱......");
            return object;
        });
    }
    public Object getProxyInstance2() {
        return Proxy.newProxyInstance(realStar.getClass().getClassLoader(), realStar.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理先进行谈判.....");
                Object object = method.invoke(realStar, args);
                System.out.println("演出完代理去收钱......");
                return null;
            }
        });
    }
}
