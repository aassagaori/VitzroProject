package com.vitzro.config;

import org.springframework.context.annotation.Configuration;


@Configuration
public class QuarzConfig {
	
	/*@Bean
	public JobDetailFactoryBean QuartzJobBean_Test1_jobDetail(){
		System.out.println("333333333333333333333333");
		JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
		jobDetailFactoryBean.setJobClass(QuartzJobBean_Test1.class);
		jobDetailFactoryBean.setDurability(true);
		return jobDetailFactoryBean;
	}
	
	@Bean
	public CronTriggerFactoryBean QuartzJobBean_Test1_FactoryBean(){
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail( QuartzJobBean_Test1_jobDetail().getObject() );
		cronTriggerFactoryBean.setCronExpression("0/10 * * * * ?");
		return cronTriggerFactoryBean;
	}
	//////////////////////////////////////////////////////////////////////////////////////
	@Bean
	public JobDetailFactoryBean QuartzJobBean_Test2_jobDetail(){
		JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
		jobDetailFactoryBean.setJobClass(QuartzJobBean_Test2.class);
		jobDetailFactoryBean.setDurability(true);
		return jobDetailFactoryBean;
	}
	
	@Bean
	public CronTriggerFactoryBean QuartzJobBean_Test2_FactoryBean(){
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail( QuartzJobBean_Test2_jobDetail().getObject() );
		cronTriggerFactoryBean.setCronExpression("0/10 * * * * ?");
		return cronTriggerFactoryBean;
	}
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException{
		
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setQuartzProperties(quartzProperties());

		Trigger[] triggers = { QuartzJobBean_Test1_FactoryBean().getObject()
							 , QuartzJobBean_Test2_FactoryBean().getObject()};

		scheduler.setTriggers(triggers);

		return scheduler;
	}
	
	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();

		return propertiesFactoryBean.getObject();

	}*/
	
}
