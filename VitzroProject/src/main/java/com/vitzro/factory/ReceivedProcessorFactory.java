package com.vitzro.factory;

import org.springframework.stereotype.Service;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.enums.eProcessorForOpcode;
import com.vitzro.processor.AbstractReceivedProcessor;
import com.vitzro.processor.ICompletableFutureProcessor;

@SuppressWarnings("rawtypes")
@Service
public class ReceivedProcessorFactory {

	synchronized public AbstractReceivedProcessor create(int opcode) throws Exception{
		Class clazz;
		try {
			clazz = Class.forName(eProcessorForOpcode.forValue(opcode).toString());
			
		} catch (ClassNotFoundException e) {
			throw e;
		}
		return (AbstractReceivedProcessor)ApplicationContextProvider.getBean(clazz);
	
	}
}
