package com.pandatv.tools.proxy_mode.dynamic_proxy.jdk_dy_mode;

import com.pandatv.zhaomu.proxy_mode.RealStar;
import com.pandatv.zhaomu.proxy_mode.Star;

/**
 * @author: likaiqing
 * @create: 2019-01-18 11:01
 * 代理模式中的动态代理，其实套路都是相同的，只是使用了不同的技术而已
 * 总结：CGLIB 创建的动态代理对象比 JDK 创建的动态代理对象的性能更高，但是 CGLIB 创建代理对象时所花费的时间却比 JDK 多得多。所以对于单例的对象，因为无需频繁创建对象，用 CGLIB 合适，反之使用JDK方式要更为合适一些。同时由于 CGLIB 由于是采用动态创建子类的方法，对于final修饰的方法无法进行代理
 **/
public class Test {
    public static void main(String[] args) {
//        jdkProxyTest();
        cglibProxyTest();
    }

    private static void cglibProxyTest() {
        Star realStar = new RealStar();
        Star proxy = (Star) new CglibProxyHandler().getProxyInstance(realStar);
        proxy.sing();
    }

    private static void jdkProxyTest() {
        Star realStar = new RealStar();
        Star proxy = (Star) new JdkProxyHandler(realStar).getProxyInstance();
        proxy.sing();
    }
}
