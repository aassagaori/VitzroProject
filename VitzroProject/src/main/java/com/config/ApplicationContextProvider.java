package com.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/* @Configuration, @Component 차이점
 * @Configuration - Bean을 생성 할 때 Single Tone으로 한번만 생성
 * @Component - Bean을 생성 할 때 java에서 new로 생성하듯이 생성한다.
 * */
@Component
public class ApplicationContextProvider implements ApplicationContextAware  {

	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		context = arg0;
	}
    
    /**
     * class타입으로 bean을 가져온다.
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
     return context.getBean(clazz);
    }
    
    /**
     * 이름으로 bean을 가져온다.
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
     return context.getBean(beanName);
    }



}
