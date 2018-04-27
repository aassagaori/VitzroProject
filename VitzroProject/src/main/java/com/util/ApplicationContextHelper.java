package com.util;

import org.springframework.context.support.AbstractApplicationContext;

public class ApplicationContextHelper {
	public AbstractApplicationContext context;
	public ApplicationContextHelper() {}
	
	private static ApplicationContextHelper _instance = null;
	
	public static ApplicationContextHelper getInstance() {
		if (_instance == null) {
			synchronized (ApplicationContextHelper.class) {
				if (_instance == null) {
					_instance = new ApplicationContextHelper();
				}
			}
		}
		return _instance;
	}
	
	public void loadApplicationContext(String contextPath) {
		
		/* contextPath 에 작성된 경로의 XML 문서를 context로 만든다. */
		this.context = new org.springframework.context.support.ClassPathXmlApplicationContext(contextPath);
		
		// AbstractApplicationContext 클래스에 선언된 registerShutdownHook() 메서드를 호출해서 종료 훅을 등록한다.
    	// applicationContext 컨텍스트에 종료 훅(shutdown hook)을 추가한다.
	    // 훅(hook)은 어플리케이션 종료보다 먼저 호출된다.
		// 종료 훅을 등록함으로써 안정적인 종료를 보장하고 모든 리소스를 릴리즈함
		this.context.registerShutdownHook();
	}
	
	public AbstractApplicationContext getContext() {
		return this.context;
	}
}
