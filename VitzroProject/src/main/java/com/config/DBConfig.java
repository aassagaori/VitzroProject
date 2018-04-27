package com.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;



/**
 * @Author : Kim Ki Hwan
 * @Date   : 2018. 4. 18.
 * 마이바티스를 스프링과 함께 사용하기 위해서는 최소한 한개의 SqlSessionFactory 와 최소한 한개의 매퍼 인터페이스가 필요
 * maven - 마이바티스-스프링-부트-스타터는 다음과 같은일을 처리한다.
 * - 존재하는 DataSource 자동감지
 * - SqlSeessionFactoryBean을 사용해서 해당 데이터소스를 전달하는 SqlSessionFactory의 객체를 생성하고 등록
 * - SqlSessionFactory을 이용해 SqlSessionTemplate의 객체를 생성하고 등록
 * - 퍼들을 자동스캔한 뒤 스캔된 것들을 SqlSessionTemplate에 연결하고 Spring Context에 등록해서 당신의 빈들에 주입할 수 있게 해준다.
 */
@Configuration
@MapperScan("com.db.mapper")
/* properties 파일 불러오기 기본적으로 spring boot 에서는 application.properties 은 불러온다. 
 * 그리고 나머지 것들은 추가하면된다.*/
//@PropertySource(value = "file:${user.dir}/conf/db2.properties")
public class DBConfig {
	
	/* @Bean - 스프링 XML 설정의 <bean />과 동일한 기능을 제공 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sessionFactory2 = new SqlSessionFactoryBean();
		sessionFactory2.setDataSource(dataSource);
		sessionFactory2.setTypeAliasesPackage("com.vo");
		sessionFactory2.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:appQuery/*.xml"));
		return (SqlSessionFactory)sessionFactory2.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory2){
		return new SqlSessionTemplate(sqlSessionFactory2, ExecutorType.REUSE); 
	}
	
	@Bean(name="transactionManager")
	public DataSourceTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		return dataSourceTransactionManager;
	}
	
	
}
