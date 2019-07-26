package com.pandatv.tools.proxy_mode.dynamic_proxy.jdk_dy_mode;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * @author: likaiqing
 * @create: 2019-01-18 11:44
 * CGLIB 创建的动态代理对象比 JDK 创建的动态代理对象的性能更高，但是 CGLIB 创建代理对象时所花费的时间却比 JDK 多得多。所以对于单例的对象，因为无需频繁创建对象，用 CGLIB 合适，反之使用JDK方式要更为合适一些
 * CGLIB 采用了非常底层的字节码技术，其原理是通过字节码技术为一个类创建子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。但因为采用的是继承，所以不能对final修饰的类进行代理
 **/
public class CglibProxyHandler implements MethodInterceptor {
    /**
     * 维护目标对象
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    private Object target;

    public Object getProxyInstance(final Object target) {
        this.target = target;
        // Enhancer类是CGLIB中的一个字节码增强器，它可以方便的对你想要处理的类进行扩展
        Enhancer enhancer = new Enhancer();
        //将被代理的对象设置成父类
        enhancer.setSuperclass(this.target.getClass());
        //回调方法，设置拦截器
        enhancer.setCallback(this);
        //动态创建一个代理类
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理先进行谈判......");
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println("演出完代理去收钱......");
        return result;
    }
}
