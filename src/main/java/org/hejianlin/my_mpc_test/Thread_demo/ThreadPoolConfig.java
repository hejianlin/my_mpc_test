package org.hejianlin.my_mpc_test.Thread_demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int processors = Runtime.getRuntime().availableProcessors();
        //核心线程数目
        executor.setCorePoolSize(processors);
        //指定最大线程数
        executor.setMaxPoolSize(processors * 4);
        //队列中最大的数目
        executor.setQueueCapacity(processors * 250);
        //线程名称前缀
        executor.setThreadNamePrefix("defaultThreadPool-");
        //CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程空闲后的最大存活时间
        executor.setKeepAliveSeconds(60);
        //任务包装器
        requestContextDecorator(executor);
        //等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //加载
        executor.initialize();
        return executor;
    }

    /**
     * 任务包装器
     *
     * @param executor
     */
    private void requestContextDecorator(ThreadPoolTaskExecutor executor) {
        executor.setTaskDecorator(runnable -> {
            //ThreadLocal需要使用InheritableThreadLocal类的实现，可以做到变量从主线程传递到子线程
            //TODO:在这里可以重新取得线程中的ThreadLocal保存的值
            return () -> {
                try {
                    //TODO:重新设置ThreadLocal的值
                    runnable.run();
                } finally {
                    //TODO: 任务结束时，需要清除ThreadLocal的值
                }
            };
        });
    }
}
