package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.vo.CctvVO;
import com.db.mapper.BaseInfoLoadMapper;


@Service
public class LoadEquipment {
	
	/* getter/setter , new 연산자로 객체 생성 없이도 접근 가능 */
	@Autowired
	private BaseInfoLoadMapper baseInfoLoadDataMapper;
	
	/* @Autowired는 타입으로(by type), @Resource는 이름으로(by name)으로 연결
	 * @Qualifier - @Autowired 어노테이션과 함께 사용된고 @Autowired의 목적에서 동일 타입의 빈객체가 존재시 특정빈을 삽입
	 * */
	@Autowired
    private AsyService asdasd;

	public void loadCctvList() {
		List<CctvVO> cctvList = null ;
		
		try {
			System.out.println("Load Equipment ...");
			cctvList = baseInfoLoadDataMapper.selectCctvList();
			System.out.println("Selected VDS List [Total : "+cctvList.size()+"]" );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
