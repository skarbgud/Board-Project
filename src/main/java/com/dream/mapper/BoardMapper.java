package com.dream.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dream.vo.BoardVO;

@Mapper
public interface BoardMapper {

	public List<BoardVO> getBoardList();
	
}
