package com.vitzro.factory;

import org.springframework.stereotype.Service;

import com.vitzro.dto.IMessage;
import com.vitzro.enums.eMessageForOpcode;

@SuppressWarnings("rawtypes")
@Service
public class MessageFactory {

	synchronized public IMessage create(int opcode) throws Exception{
		Class clazz;
		try {
			clazz = Class.forName(eMessageForOpcode.forValue(opcode).toString());
			
		} catch (ClassNotFoundException e) {
			throw e;
		}
		return (IMessage)clazz.newInstance();
	
	}
}
