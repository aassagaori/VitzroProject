package com.db.mapper;

import java.util.List;

import com.data.vo.CctvVO;

public interface BaseInfoLoadMapper {
	/* 메소드의 명은 Mapper에 정의되어있는 쿼리문 ID와 동일 해야한다. */
	public List<CctvVO> selectCctvList(); 
}
