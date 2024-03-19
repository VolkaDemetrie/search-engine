package com.volka.searchengine.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 쓰레드풀 설정
 *
 * @author volka
 */
@Configuration
public class ThreadPoolConfig {
    
    @Bean
    public ThreadPoolTaskExecutor threadPool() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(4);
        threadPool.setMaxPoolSize(8);
        threadPool.setQueueCapacity(1000);
        threadPool.setDaemon(true);
        threadPool.setThreadNamePrefix("Pool-Task :: ");
        threadPool.initialize();

        return threadPool; //FIXME : 예외처리 wrapper 추가해야함 20240319 volka
    }


//    public static class HandlingExecutor implements AsyncTaskExecutor {
//
//        private final AsyncTaskExecutor executor;
//
//        public HandlingExecutor(AsyncTaskExecutor executor) {
//            this.executor = executor;
//        }
//
//        @Override
//        public void execute(Runnable task) {
//
//        }
//
//        @Override
//        public Future<?> submit(Runnable task) {
//            return AsyncTaskExecutor.super.submit(task);
//        }
//
//        @Override
//        public <T> Future<T> submit(Callable<T> task) {
//            return AsyncTaskExecutor.super.submit(task);
//        }
//
//        @Override
//        public CompletableFuture<Void> submitCompletable(Runnable task) {
//            return AsyncTaskExecutor.super.submitCompletable(task);
//        }
//
//        @Override
//        public <T> CompletableFuture<T> submitCompletable(Callable<T> task) {
//            return AsyncTaskExecutor.super.submitCompletable(task);
//        }
//    }


//    @Bean
//    public TaskDecorator taskDecorator() {
//        TaskDecorator decorator = new TaskDecorator() {
//            @Override
//            public Runnable decorate(Runnable runnable) {
//                try {
//                    runnable.run();
//                } catch (BizException e) {
//
//                } catch (Exception e) {
//
//                }
//
//                return null;
//            }
//        }
//    }
}
