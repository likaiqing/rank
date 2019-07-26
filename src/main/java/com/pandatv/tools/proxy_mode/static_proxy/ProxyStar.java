package com.pandatv.tools.proxy_mode.static_proxy;

import com.pandatv.zhaomu.proxy_mode.Star;

/**
 * @author: likaiqing
 * @create: 2019-01-17 16:30
 **/
public class ProxyStar implements Star {

    private Star star;

    public ProxyStar(Star star) {
        this.star = star;
    }

    @Override
    public void sing() {
        System.out.println("代理先进行谈判.....");
        star.sing();
        System.out.println("演出完代理收钱.....");
    }
}
