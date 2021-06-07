package com.mm.market.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
	
	//List
	public List<CommentVO> getList(CommentVO commentVO) throws Exception;
	//Select
	public CommentVO getSelect(CommentVO commentVO) throws Exception;
	//Insert
	public int setInsert(CommentVO commentVO) throws Exception;
	//Update
	public int setUpdate(CommentVO commentVO) throws Exception;
	//Delete
	public int setDelete(CommentVO commentVO) throws Exception;

}
