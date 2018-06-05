package com.vitzro.mybatis.mapper;

import java.util.List;

import com.vitzro.vo.voAVI;
import com.vitzro.vo.voVMS;

public interface SelectMapper {
	public List<voVMS> selectVms(); 
	public List<voAVI> selectAvi(); 
}
