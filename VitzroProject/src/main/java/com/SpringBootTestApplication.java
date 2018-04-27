package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.config.ApplicationContextProvider;
import com.netty.NettyTCPServer;
import com.service.LoadEquipment;

@SpringBootApplication
/* @ComponentScan 
 * - 지정한 위치 이하에 있는 @Component와 @Configuration이 붙은 class를 스캔해서 Bean으로 등록한다
 * - 스프링 XML 설정의 <context:component-scan>을 대신해서 자바에 설정
 * */
@ComponentScan(basePackages = "com")
public class SpringBootTestApplication {

	public static void main(String[] args) {
		
		try {
			System.out.println("(1)");
			SpringApplication.run(SpringBootTestApplication.class, args);
			
			System.out.println("(2)");
			ApplicationContextProvider.getBean(LoadEquipment.class).loadCctvList();
			
			System.out.println("(3)");
			ApplicationContextProvider.getBean(NettyTCPServer.class).start();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
