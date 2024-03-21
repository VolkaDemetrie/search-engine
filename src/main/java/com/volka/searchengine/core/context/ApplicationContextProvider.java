package com.volka.searchengine.core.context;

import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 스프링 컨테이너 Bean 제공자
 *
 * @author volka
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext appContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    public static Object getBean(String beanName) {
        return appContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> bean) {
        return appContext.getBean(bean);
    }
}
