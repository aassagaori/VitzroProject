package com.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
/* asynchronous mehtod(비동기 메소드)를 호출하는 빈에 @EnableAsync가 필요 
 * 이 어노테이션은 스프링이 @Async 메서드를 백그라운드 쓰레드풀을 통해 실행하게 해줌 
 * 자바 설정Java configuration으로 비동기 처리enabling asynchronous processing를 쓰려면 간단히 설정 클래스에 @EnableAsync를 추가해주기만 하면 된다
 * @EnableAsync를 명시함으로서 비동기로 작동할 @Async가 명시된 빈을 인식하도록 한다.
 * 출처 - http://dveamer.github.io/java/SpringAsync.html
 * */
@EnableAsync
public class ThreadConfig implements AsyncConfigurer{
	
	/* @Autowired - Spring의 의존관계(DI)를 자동으로 설정, 타입(by type)으로 연결 */
	@Autowired
	private Environment env;
	
	@Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 쓰레드 풀 생성시 최소 가용 쓰레드로 10개를 생성
        taskExecutor.setCorePoolSize(Integer.parseInt(env.getProperty("threading.initpoolsize")));
        //대기 중인 큐마저 모두 차버리면 가용 쓰레드를 최대 20개까지 생성한다
        taskExecutor.setMaxPoolSize(Integer.parseInt(env.getProperty("threading.maxpoolsize")));
        // 만약 10개 쓰레드가 모두 사용 중이라면 큐에 50개까지 대기
        taskExecutor.setQueueCapacity(Integer.parseInt(env.getProperty("threading.queuesize")));
        
        taskExecutor.initialize();

        return taskExecutor;
    }
	
	/* Thread ExceptionHandler 
	 * 출처: http://cofs.tistory.com/321?category=627165 [CofS] */	
//	@Override
//	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//		return new ThreadHandler();
//	}
	
	public String threadState(){
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor)ApplicationContextProvider.getBean("threadPoolTaskExecutor");
		return "Thread[ CorePollsize["+taskExecutor.getCorePoolSize()+"]  ActiveCount["+ taskExecutor.getActiveCount() +"] ]" ;
	}

}
