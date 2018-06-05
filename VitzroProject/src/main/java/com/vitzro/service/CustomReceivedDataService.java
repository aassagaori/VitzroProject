package com.vitzro.service;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.vitzro.dto.CustomReceiveContent;
import com.vitzro.dto.IReceivedMessage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomReceivedDataService implements InitializingBean, Observer, IReceivedMessageStroage, ILoadedMessageStorage{

	private ConcurrentHashMap<String, CustomReceiveContent> collectMap = null;
	
	@Override	
	public void afterPropertiesSet() throws Exception {	
		collectMap = new ConcurrentHashMap<String, CustomReceiveContent>();

	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		int siganl = (int)arg1;
		if (arg0 instanceof BaseDataStorage) {
			if(siganl == BaseDataStorage.NOTIFY_DATALOADING_SUCCESS) {
				Load();
			}else {
				
			}
		}
	}

	@Override
	public void Clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(IReceivedMessage m) {
		
	}

	@Override
	public int Load() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
}
