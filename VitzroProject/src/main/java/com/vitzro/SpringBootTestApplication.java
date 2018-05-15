package com.vitzro;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.factory.CompletableFutureProcessorFactory;
import com.vitzro.netty.NettyClient;
import com.vitzro.netty.NettyServer;
import com.vitzro.processor.ACKProcessor;
import com.vitzro.protocol.ProtocolForm;

import lombok.extern.slf4j.Slf4j;


//@SpringBootApplication
/* @ComponentScan 
 * - 지정한 위치 이하에 있는 @Component와 @Configuration이 붙은 class를 스캔해서 Bean으로 등록한다
 * - 스프링 XML 설정의 <context:component-scan>을 대신해서 자바에 설정
 * */
//@ComponentScan(basePackages = "com.vitzro")
//@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages= "com.vitzro")
@Slf4j
public class SpringBootTestApplication {


	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestApplication.class, args);
//		try(ConfigurableApplicationContext context = SpringApplication.run(SpringBootTestApplication.class, args)){
//		}
		
	}
	

	@Bean
	ApplicationRunner run() {
//		log.debug("ApplicationRunner run");
//		System.out.println("ApplicationRunner run");
		log.debug("ApplicationRunner RUN");
		return a -> { 
			
//			System.out.println(TypeHelper.contains(env.getActiveProfiles(), "TCPServer"));
//			s.run();
//			Thread.sleep(5000l);
			
			ApplicationContextProvider.getBean(NettyClient.class).run();
			
			
//			.execute(() -> System.out.println("메롱"));
			CompletableFuture<String> f=  
					ApplicationContextProvider.getBean(CompletableFutureProcessorFactory.class).create((byte)0x01).processing(ProtocolForm.builder().build(), null);
			 
			
//			CompletableFuture<String> f= ApplicationContextProvider.getBean(ACKProcessor.class).processing(new ProtocolForm(), null);
			
			CompletableFuture.allOf(f).join();
			log.debug("run complete {}",f.get());
//			((ThreadPoolTaskExecutor)ApplicationContextProvider.getBean("eventThreadPoolTaskExecutor")).execute(ApplicationContextProvider.getBean(NettyClient.class));
//			((ThreadPoolTaskExecutor)ApplicationContextProvider.getBean("eventThreadPoolTaskExecutor")).execute(ApplicationContextProvider.getBean(NettyServer.class));
			
//			((ThreadPoolTaskExecutor)ApplicationContextProvider.getBean("eventThreadPoolTaskExecutor")).execute((NettyServer)ApplicationContextProvider.getBean(NettyServer.class));
//			s.run();
//			s.call();
//			ListenableFuture<String> f = t.processing(new ProtocolFormMessage());
//			t.Rhello(1, new Person());
//			f.addCallback(s->System.out.println(s) ,e->System.out.println(e.));
			
		};
	}
	
}
