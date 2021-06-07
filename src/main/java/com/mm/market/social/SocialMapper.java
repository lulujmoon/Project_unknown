package com.mm.market.social;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mm.market.util.Pager;

@Mapper
public interface SocialMapper {

	//List
	public List<SocialVO> getList(Pager pager) throws Exception;
	//Category List
	public List<SocialVO> getCategoryList(Pager pager) throws Exception;
	//Count
	public Long getTotalCount(Pager pager) throws Exception;
	//Select
	public SocialVO getSelect(SocialVO socialVO) throws Exception;
	//Social Num
	public long getSocialNum() throws Exception;
	//Insert
	public int setInsert(SocialVO socialVO) throws Exception;
	//File Insert
	public int setFileInsert(SocialFileVO socialFileVO) throws Exception;
	//File Delete
	public int setFileDelete(SocialFileVO socialFileVO) throws Exception;
	//File Select
	public SocialFileVO setFileSelect(SocialFileVO socialFileVO) throws Exception;
	//Update
	public int setUpdate(SocialVO socialVO) throws Exception;
	//Delete
	public int setDelete(SocialVO socialVO) throws Exception;
	
}
