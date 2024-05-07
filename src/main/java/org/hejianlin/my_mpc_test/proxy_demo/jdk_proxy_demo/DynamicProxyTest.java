package org.hejianlin.my_mpc_test.proxy_demo.jdk_proxy_demo;

import org.hejianlin.my_mpc_test.proxy_demo.CaptainAmericaMovie;
import org.hejianlin.my_mpc_test.proxy_demo.IronManVIPMovie;
import org.hejianlin.my_mpc_test.proxy_demo.Movie;
import org.hejianlin.my_mpc_test.proxy_demo.VIPMovie;

import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    public static void main(String[] args) {

        //加这个方法能够查看到产生的代理类
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

        //新建被代理对象，即原对象实例
        IronManVIPMovie ironManVIPMovie = new IronManVIPMovie();
        //新建反射控制器，传入被代理对象
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(ironManVIPMovie);
        //创建代理对象，向上转型为代理接口对象， 需要传入被代理对象的类加载器、被代理对象的接口、以及反射控制器;
        VIPMovie vipMovieProxy = (VIPMovie) Proxy.newProxyInstance(IronManVIPMovie.class.getClassLoader(), IronManVIPMovie.class.getInterfaces(), myInvocationHandler);
        //通过代理对象调用目标方法
        vipMovieProxy.vipPlay();

        System.out.println();

        //另外一个例子
        CaptainAmericaMovie captainAmericaMovie = new CaptainAmericaMovie();
        MyInvocationHandler myInvocationHandler1 = new MyInvocationHandler(captainAmericaMovie);
        Movie movieProxy = (Movie) Proxy.newProxyInstance(captainAmericaMovie.getClass().getClassLoader(), captainAmericaMovie.getClass().getInterfaces(), myInvocationHandler1);
        movieProxy.play();

        System.out.println();

        System.out.println("第一个代理类：" + vipMovieProxy.getClass());
        System.out.println("第二个代理类：" + movieProxy.getClass());
    }

}
