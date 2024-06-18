package org.hejianlin.my_mpc_test.aop.cglib_proxy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy  //开启代理，如果目标类是基于接口实现的，则是jdk动态代理，如果目标类是直接的一个类，则是cglib代理
@Aspect  //切面
@Component  //切面作为一个bean
public class CglibLogAspect {

    /**
     * 定义切点方法
     */
    @Pointcut("execution(* org.hejianlin.my_mpc_test.aop.cglib_proxy.service.*.*(..))")
    private void pointCutMethod(){

    }

    /**
     * 环绕通知,需要返回方法的执行结果
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("-----------------------");
        System.out.println("环绕通知: 进入方法");
        Object o = pjp.proceed();
        System.out.println("环绕通知: 退出方法");
        return o;
    }

    /**
     * 前置通知
     */
    @Before("pointCutMethod()")
    public void doBefore(){
        System.out.println("前置通知");
    }


    /**
     * 后置通知
     * @param result 定义接收目标方法返回的结果参数名称
     */
    @AfterReturning(pointcut = "pointCutMethod()", returning = "result")
    public void doAfterReturning(String result){
        System.out.println("后置通知，返回值：" + result);
    }

    /**
     * 异常通知
     * @param e 定义接收目标方法返回的异常参数名称
     */
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e){
        System.out.println("异常通知，异常：" + e.getMessage());
    }


    /**
     * 最终通知
     */
    @After("pointCutMethod()")
    public void doAfter(){
        System.out.println("最终通知");
    }

}
