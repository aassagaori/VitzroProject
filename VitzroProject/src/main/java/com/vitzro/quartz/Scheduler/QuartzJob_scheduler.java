package com.vitzro.quartz.Scheduler;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import com.vitzro.quartz.Quarzjab_test;
import com.vitzro.quartz.Quarzjab_test2;

@Component
public class QuartzJob_scheduler {
	private SchedulerFactory schedulerFactory;
    private Scheduler scheduler;

    /* @PostConstruct
     * The PostConstruct annotation is used on a method that needs to be executed after dependency injection is done to perform any initialization
     * Spring의 DI 가 이르어지고 바로 실행되게 하는 어노테이션
     * */
    @PostConstruct
    public void start() throws SchedulerException{
    	SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    	Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        
    	//job 지정
        JobDetail job = JobBuilder.newJob(Quarzjab_test.class).build();                             
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();
        
    	//job 지정
        JobDetail job2 = JobBuilder.newJob(Quarzjab_test2.class).build();
        Trigger trigger2 = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("2/10 * * * * ?")).build();
        
        scheduler.scheduleJob(job, trigger);
        scheduler.scheduleJob(job2, trigger2);*/
    }

}
