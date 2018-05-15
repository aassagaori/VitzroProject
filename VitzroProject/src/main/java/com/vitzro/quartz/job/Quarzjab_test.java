package com.vitzro.quartz.job;

import org.assertj.core.util.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class Quarzjab_test implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		 System.out.println("--------------------------job1수행---------------------------");
//	     System.out.println(DateUtil.now());		
	}



}
