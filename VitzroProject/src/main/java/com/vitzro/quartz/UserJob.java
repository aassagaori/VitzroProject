package com.vitzro.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class UserJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("====================================================================");
		System.out.println("QuartzJobBean_Test1");
	}

}
