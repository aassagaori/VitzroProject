package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vitzro.enums.eOpcode;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class SpringBootTestApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println(eOpcode._ACK.toString());
		
//		InterruptedException i = new InterruptedException();
//		System.out.println(i.getClass().getTypeName());
//		System.out.println(i.getClass().getSimpleName());
		
		try {
			int a = 1/0;
		}
		catch (Exception e) {
			System.out.println(e.getClass().getTypeName());
			if(e.getClass().getSimpleName().equals("ArithmeticException")) {
				System.out.println("aaaaaaaaaaaaaa");
			}
		}
	}

}
