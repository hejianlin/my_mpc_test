package org.hejianlin.my_mpc_test.aop;

import org.hejianlin.my_mpc_test.aop.cglib_proxy.service.CglibProxyDemoServiceImpl;
import org.hejianlin.my_mpc_test.aop.jdk_proxy.service.IJdkProxyService;
import org.hejianlin.my_mpc_test.aop.service.AopDemoServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {

        System.out.println("\n通过xml配置实现：");
        // create and configure beans
        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("aspects.xml");

        // retrieve configured instance
        AopDemoServiceImpl xmlService = xmlContext.getBean("demoService", AopDemoServiceImpl.class);

        // use configured instance
        xmlService.doMethod1();
        xmlService.doMethod2();
        try {
            xmlService.doMethod3();
        } catch (Exception e) {
            // e.printStackTrace();
        }

        System.out.println("\n通过AspectJ注解方式：Spring AOP的实现方式是动态织入，动态织入的方式是在运行时动态将要增强的代码织入到目标类中，这样往往是通过动态代理技术完成的");
        System.out.println("\n基于JDK代理实现的例子：");
        AnnotationConfigApplicationContext configContext = new AnnotationConfigApplicationContext("org.hejianlin.my_mpc_test");
        IJdkProxyService jdkProxyService = configContext.getBean(IJdkProxyService.class);
        jdkProxyService.doMethod1();
        jdkProxyService.doMethod2();
        try {
            jdkProxyService.doMethod3();
        } catch (Exception e) {
            // e.printStackTrace();
        }

        System.out.println("\n基于Cglib代理实现的例子：");
        CglibProxyDemoServiceImpl cglibProxyService = configContext.getBean(CglibProxyDemoServiceImpl.class);
        cglibProxyService.doMethod1();
        cglibProxyService.doMethod2();
        try {
            cglibProxyService.doMethod3();
        } catch (Exception e) {
            // e.printStackTrace();
        }

    }
}
