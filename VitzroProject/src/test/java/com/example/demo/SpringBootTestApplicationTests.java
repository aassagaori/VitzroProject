package com.example.demo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.reflect.TypeToken;
import com.vitzro.dto.IMessage;
import com.vitzro.dto.IReceivedMessage;
import com.vitzro.enums.eMessageForOpcode;
import com.vitzro.factory.MessageFactory;

//@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringBootConfiguration
public class SpringBootTestApplicationTests {

	@Test
	public void contextLoads() {
		int i = 12;
		byte b = (byte)i;
		
		System.out.println(Integer.toBinaryString(b));
		System.out.println(Integer.toBinaryString(b&0xff));
		System.out.println((byte)(1900-2018));
		
		System.out.println((byte)((1900-2018)&0xff));
		System.out.println(((byte)((byte)(1900-2018))&0xff));
		/*try {
			
			MessageFactory mf = new MessageFactory();
			IMessage m =mf.create((byte)eMessageForOpcode._CustomCollect.getValue());
			
	        ParameterizedType parameterizedType = (ParameterizedType) m.getClass().getGenericInterfaces()[0];
	        Type typeArgument = parameterizedType.getActualTypeArguments()[0];
	     
	        
	        System.out.println(typeArgument);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}

