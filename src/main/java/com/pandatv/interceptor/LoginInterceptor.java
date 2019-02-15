package com.pandatv.interceptor;

import com.pandatv.conf.Conf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基于URL实现的拦截器
 *
 * @author: likaiqing
 * @create: 2019-02-14 10:40
 **/
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if (path.matches(Const.NO_INTERCEPTOR_PATH)) {
            //不需要拦截，直接通过
            return true;
        } else {
            //这写你拦截要干的事，比如取缓存，session，权限判断等
            Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute(Conf.IS_LOGIN);
//            httpServletRequest.getSession().getAttribute(Conf.LOGIN_USER);
            return true;
        }
    }
}
