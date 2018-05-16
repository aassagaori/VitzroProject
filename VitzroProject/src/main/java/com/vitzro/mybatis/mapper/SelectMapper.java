package com.vitzro.mybatis.mapper;

import java.util.List;

import com.vitzro.vo.voBusstop;
import com.vitzro.vo.voRoute;

public interface SelectMapper {
	public List<voRoute> selectRoute(); 
	public List<voBusstop> selectBusstop(); 
}
