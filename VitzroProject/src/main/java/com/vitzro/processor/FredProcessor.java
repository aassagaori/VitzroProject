package com.vitzro.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.vitzro.dto.CustomReceiveContent;
import com.vitzro.dto.IReceivedMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Scope("prototype")
public class FredProcessor extends AbstractReceivedProcessor<String> {
	
	@Override
	public String method(IReceivedMessage msg) throws Exception {
		recvS.put(msg);
		return "Success";
	}
}
