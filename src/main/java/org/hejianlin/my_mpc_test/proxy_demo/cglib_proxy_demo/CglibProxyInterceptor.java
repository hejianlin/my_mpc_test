package org.hejianlin.my_mpc_test.proxy_demo.cglib_proxy_demo;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyInterceptor implements MethodInterceptor {

    /**
     *
     * @param o
     * @param method 被代理的方法
     * @param objects 方法参数
     * @param methodProxy  方法代理对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //前置处理
        playStart();
        //调用原来方法
        Object object = methodProxy.invokeSuper(o, objects);
        //后置处理
        playEnd();
        return object;
    }

    public void playStart() {
        System.out.println("电影开始前正在播放广告");
    }
    public void playEnd() {
        System.out.println("电影结束了，接续播放广告");
    }

}
