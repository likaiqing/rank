package com.pandatv.tools.proxy_mode.static_proxy;

import com.pandatv.zhaomu.proxy_mode.RealStar;

/**
 * @author: likaiqing
 * @create: 2019-01-17 16:32
 **/
public class Test {
    public static void main(String[] args) {
        RealStar realStar = new RealStar();
        ProxyStar proxyStar = new ProxyStar(realStar);
        proxyStar.sing();
    }
}
