package org.hejianlin.my_mpc_test.proxy_demo.cglib_proxy_demo;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import org.hejianlin.my_mpc_test.proxy_demo.CaptainAmerica2MovieImpl;

public class CglibProxyTest {
    public static void main(String[] args) {

        //java17需要在启动命令中增加如下vm参数： --add-opens java.base/java.lang=ALL-UNNAMED
        //不然会报错，主要原因是Java 模块化之后，有些 jdk 内部的类不能被访问了，但是在 Java 16 之前都只是警告，而在 Java 16 之后则会直接报错

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "cglib");

        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(CaptainAmerica2MovieImpl.class);
        //设置回调函数
        enhancer.setCallback(new CglibProxyInterceptor());
        //这里create的方法就是正式创建代理类
        CaptainAmerica2MovieImpl captainAmerica2Movie = (CaptainAmerica2MovieImpl) enhancer.create();
        //调用代理类的play方法
        captainAmerica2Movie.play();
        System.out.println("cglib生成的代理类：" + captainAmerica2Movie.getClass());
    }
}
