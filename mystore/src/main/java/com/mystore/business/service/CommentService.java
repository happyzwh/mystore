package com.mystore.business.service;

import java.util.Map;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.Comment;

public interface CommentService {
	
	/**
	 * 增加商品评论
	 * 
	 * */
	public void addComment(Comment comment);
	
	/**
	 * 根据主键查询商品评论
	 * 
	 * */
	public Pager<Comment> getProCommentByProId(Integer proId,Integer type,Integer pageNum,Integer pageSize);
	
	/**
	 * 根据主键查询商品评论统计
	 * 
	 * */
	public Map<String,Object> getCommentStatisByProId(Integer proId);
	
}
