package org.hejianlin.my_mpc_test.proxy_demo.jdk_proxy_demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    /**
     * 被代理对象，原来的模板对象
     */
    private Object object;

    public MyInvocationHandler(Object object){
        this.object = object;
    }

    /**
     *
     * @param proxy 代理对象
     *
     * @param method 被代理的方法对象
     *
     * @param args 被代理的方法参数
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //自定义前置逻辑：
        playStart();
        //通过返回调用原目标对象的方法, 返回方法出参
        Object invoke = method.invoke(object, args);
        //自定义后置逻辑：
        playEnd();
        return invoke;
    }


    public void playStart() {
        System.out.println("电影开始前正在播放广告");
    }

    public void playEnd() {
        System.out.println("电影结束了，接续播放广告");
    }

}
