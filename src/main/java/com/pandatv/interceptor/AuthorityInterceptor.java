package com.pandatv.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * https://www.toutiao.com/a6655264888891376142/?tt_from=weixin&utm_campaign=client_share&wxshare_count=1&timestamp=1549755363&app=news_article&utm_source=weixin&iid=59526085849&utm_medium=toutiao_android&group_id=6655264888891376142
 * 基于注解的拦截器
 * @author: likaiqing
 * @create: 2019-02-14 10:27
 **/
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //1,方法注解级拦截器
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        //有@LoginRequied注解，需要认证
        if (methodAnnotation != null) {
            //这写拦截器需要干的事，比如取缓存，session,权限判断等
            System.out.println("************************");
            return true;
        }
        return true;
    }
}
