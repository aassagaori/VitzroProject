package com.vitzro.config;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import com.vitzro.config.handler.CustomSpringAyncUncaughtExceptonHandler;


@Configuration
/* asynchronous mehtod(비동기 메소드)를 호출하는 빈에 @EnableAsync가 필요 
 * 이 어노테이션은 스프링이 @Async 메서드를 백그라운드 쓰레드풀을 통해 실행하게 해줌 
 * 자바 설정Java configuration으로 비동기 처리enabling asynchronous processing를 쓰려면 간단히 설정 클래스에 @EnableAsync를 추가해주기만 하면 된다
 * @EnableAsync를 명시함으로서 비동기로 작동할 @Async가 명시된 빈을 인식하도록 한다.
 * 출처 - http://dveamer.github.io/java/SpringAsync.html
 * */
@EnableAsync(proxyTargetClass=true)
public class SpringAsyncConfig implements AsyncConfigurer{
	
	/* @Autowired - Spring의 의존관계(DI)를 자동으로 설정, 타입(by type)으로 연결 */
	@Autowired
	private Environment env;
	
	
	@Override
	@Bean(name = "eventThreadPoolTaskExecutor")
	public Executor getAsyncExecutor() {
		
		ThreadPoolTaskExecutor tp = new ThreadPoolTaskExecutor();
		tp.setThreadNamePrefix("Event | ");
		
		// 쓰레드 풀 생성시 최초 가용 쓰레드
        tp.setCorePoolSize(Integer.parseInt(env.getProperty("threading.corepoolsize")));
        // corepoolsize만큼 스레드가 현재 사용중이면 queuesize만큼 queue에 할당
        tp.setQueueCapacity(Integer.parseInt(env.getProperty("threading.queuesize")));
        // queuesize만큼 스레드가 할당되면 maxpoolsize이하로 스레드 생성
        tp.setMaxPoolSize(Integer.parseInt(env.getProperty("threading.maxpoolsize")));
        tp.setKeepAliveSeconds(Integer.parseInt(env.getProperty("threading.keepalivetime")));
		tp.initialize();
		return tp;
	}

	
	
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		// TODO Auto-generated method stub
		return new CustomSpringAyncUncaughtExceptonHandler();
	}


}
