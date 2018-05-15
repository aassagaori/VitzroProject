package com.vitzro.quartz.job;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.assertj.core.util.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.processor.ACKProcessor;
import com.vitzro.protocol.ProtocolForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Quarzjab_test2 implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		/* System.out.println("--------------------------job2수행---------------------------");
	    	
	     
	 	CompletableFuture<String> f= null;
		try {
			f = ApplicationContextProvider.getBean(ACKProcessor.class).processing(new ProtocolForm());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CompletableFuture.allOf(f).join();
		try {
			log.debug("job2수행 run complete {}",f.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}



}
