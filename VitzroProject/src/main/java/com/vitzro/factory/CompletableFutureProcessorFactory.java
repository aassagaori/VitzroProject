package com.vitzro.factory;

import org.springframework.stereotype.Service;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.enums.eOpcode;
import com.vitzro.processor.ICompletableFutureProcessor;

@SuppressWarnings("rawtypes")
@Service
public class CompletableFutureProcessorFactory {

	synchronized public ICompletableFutureProcessor create(byte opcode) throws Exception{
		Class clazz;
		try {
			clazz = Class.forName(eOpcode.forValue(opcode).toString());
			
		} catch (ClassNotFoundException e) {
			throw e;
		}
		return (ICompletableFutureProcessor)ApplicationContextProvider.getBean(clazz);
		/*
		try {
//			return (ICompletableFutureProcessor)clazz.newInstance();
			
		} catch (InstantiationException e) {
			throw e;
			
		} catch (IllegalAccessException e) {
			throw e;
			
		}		*/		
	}
}
