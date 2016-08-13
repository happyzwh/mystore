package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.Comment;

public interface CommentMapper {
	
	/**
	 * 增加商品评论
	 * 
	 * */
	public void addComment(Comment comment);
	
	/**
	 * 根据主键查询商品评论数量
	 * 
	 * */
	public Integer getProCommentCountByProId(Comment comment);
	
	/**
	 * 根据主键查询商品评论列表
	 * 
	 * */
	public List<Comment> getProCommentByProId(Comment comment);
	
	/**
	 * 根据主键查询商品评论统计
	 * 
	 * */
	public Map<String,Object> getCommentStatisByProId(Integer proId);

}
