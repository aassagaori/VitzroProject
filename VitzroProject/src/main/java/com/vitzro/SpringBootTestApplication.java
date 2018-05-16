package com.vitzro;

import java.util.concurrent.CompletableFuture;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.netty.NettyClient;
import com.vitzro.quartz.CustomCronJob;

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

	@Autowired
	public Environment env;
	
	@Bean
	ApplicationRunner run() {
//		log.debug("ApplicationRunner run");
//		System.out.println("ApplicationRunner run");
		log.debug("ApplicationRunner RUN");
		return a -> { 

			/*ApplicationContextProvider.getBean(NettyClient.class).run();
					 
			CompletableFuture<String> f = CompletableFuture.completedFuture("a");
			CompletableFuture.allOf(f).join();
			log.debug("run complete {}",f.get());*/
			
//			CompletableFuture<String> f= ApplicationContextProvider.getBean(ACKProcessor.class).processing(new ProtocolForm(), null);
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	    	Scheduler scheduler = schedulerFactory.getScheduler();
	        scheduler.start();

	    	//job 지정
	        JobDetail job = JobBuilder.newJob(CustomCronJob.class).build();                             
	        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();
	        
	        scheduler.scheduleJob(job, trigger);
//			ListenableFuture<String> f = t.processing(new ProtocolFormMessage());
//			t.Rhello(1, new Person());
//			f.addCallback(s->System.out.println(s) ,e->System.out.println(e.));
			
		};
	}
	
}
