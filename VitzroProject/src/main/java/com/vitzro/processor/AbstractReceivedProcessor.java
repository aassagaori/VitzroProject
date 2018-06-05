package com.vitzro.processor;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.dto.IMessage;
import com.vitzro.dto.IReceivedMessage;
import com.vitzro.dto.ProtocolData;
import com.vitzro.dto.ProtocolForm;
import com.vitzro.factory.MessageFactory;
import com.vitzro.service.CustomReceivedDataService;

public abstract class AbstractReceivedProcessor<O> implements IUnCompletableFutureProcessor<ProtocolForm, O>{
	
	CustomReceivedDataService recvS = ApplicationContextProvider.getBean(CustomReceivedDataService.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public O processing(ProtocolForm t) {
		Object obj;
		try {
			MessageFactory mf = ApplicationContextProvider.getBean(MessageFactory.class);
			IMessage m = mf.create(t.getOp_code());
//			IMessage m = mf.create((byte)0x01);
			((IReceivedMessage)m).decode(t.g);
			obj = method(((IReceivedMessage)m)); 
		} catch (Exception e) {
			return null;
		}
		
		return (O) obj;
	}

	@SuppressWarnings("rawtypes")
	abstract public O method(IReceivedMessage msg) throws Exception;
}
