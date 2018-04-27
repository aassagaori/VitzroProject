package com.service;

import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.data.structs.Vitzro_ReceiveData;
import com.processor.Processable;
import com.processor.Processor_0xA0;


@Service
public class AsyService {
	
	private static final Logger logger = LoggerFactory.getLogger(AsyService.class);
	
	@Getter @Setter
	private String test;
	
	public AsyService(){
		System.out.println(	new Object(){}.getClass());
	}
	
	/* @Async - 이 메서드는 비동기로 처리된다고 명시 
	 * - 설정한 메소드는 지정한 Executor가 관리하게 한다  
	 * 
	 * 제약사항
	 * - pulbic method에만 사용가능 합니다.
	 * - 같은 객체내의 method끼리 호출시 async method는 동작하지 않습니다
	 * */
	@Async("threadPoolTaskExecutor")
	public void method(Channel ch, Vitzro_ReceiveData data){
		try {
			System.out.println(	new Object(){}.getClass()+ " - " + new Object(){}.getClass().getEnclosingMethod().getName());
			
			Processable dataProcessor = null;
			
			byte receiveOpcode = data.getHeader().getOPCODE();
			
			switch(receiveOpcode){
			case (byte)0xA0: // ACK 데이터
				logger.info("OPCODE : [{}] !!", data.getHeader().getOPCODE());
				dataProcessor = new Processor_0xA0(ch, data);
				break;
			default:
				System.out.println("OPCODE ERROR");
				logger.info("Recieved Wrong Opcode at - #{}", receiveOpcode);
				break;
			}
			
			if (dataProcessor != null) {
				dataProcessor.Processing(); 
			}else {			
				logger.error("INVALID_OPCODE : #{}", receiveOpcode);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	



}
