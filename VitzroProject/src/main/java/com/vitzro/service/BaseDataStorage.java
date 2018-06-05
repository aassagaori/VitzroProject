package com.vitzro.service;

import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitzro.config.ApplicationContextProvider;
import com.vitzro.mybatis.mapper.SelectMapper;
import com.vitzro.vo.voVMS;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

//@Service
@Getter
@Slf4j
public class BaseDataStorage  extends Observable implements InitializingBean, ILoadedMessageStorage{
	

	public static final int NOTIFY_DATALOADING_SUCCESS = 1;
	public static final int NOTIFY_DATALOADING_FAIL = 0;
	
	private ConcurrentHashMap<String, voVMS> vmsMap = null;
	
	@Autowired
	private SelectMapper selectMpp;

	/**
	 * DB 변경시 각 서비스 클래스에 통보
	 */
	public void action(int loadResult) {

		setChanged();		
		notifyObservers(loadResult);		
	}	
		
	
	//초기화
	@Override	
	public void afterPropertiesSet() throws Exception {	
		vmsMap = new ConcurrentHashMap<String, voVMS>();
		addObserver((CustomReceivedDataService)ApplicationContextProvider.getBean(CustomReceivedDataService.class));

		action(Load());
	}
	
	@Override
	public void Clear() {
		vmsMap.clear();
	}
	
	@Override
	public int Load() {
		
		log.info("Base Data Loading...");
		boolean ret = selectVms() | selectAvi();
		log.info("Base Data Loading...Success({})",ret);
		
		return (ret == true ? NOTIFY_DATALOADING_SUCCESS : NOTIFY_DATALOADING_FAIL); 
	}
	
	
		
	private boolean selectVms() {
		try {
			selectMpp.selectVms();
		} catch (Exception e) {
			log.error("Fail, Vms | " ,e);
			return false;
		}
		log.info("Success, Vms | ");
		return true;		
	}
	
	private boolean selectAvi() {
		try {
			selectMpp.selectAvi();
		} catch (Exception e) {
			log.error("Fail, Avi | " ,e);
			return false;
		}
		log.info("Success, Avi | ");
		return true;		
	}



	
	
}
