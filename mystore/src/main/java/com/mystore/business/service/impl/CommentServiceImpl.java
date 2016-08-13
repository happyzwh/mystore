package com.mystore.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.common.Pager;
import com.mystore.business.dao.CommentMapper;
import com.mystore.business.dto.Comment;
import com.mystore.business.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		commentMapper.addComment(comment);
	}

	@Override
	public Pager<Comment> getProCommentByProId(Integer proId,Integer type,Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		Comment comment = new Comment();
		comment.setId_pro(proId);
		comment.setType(type);
		if(pageNum != null && pageNum == 0)pageNum =1;
		if(pageSize != null && pageSize == 0)pageSize = 10;
		if(pageNum != null && pageSize != null){
			int pageIndex = (pageNum - 1)*pageSize;
			comment.setPageIndex(pageIndex);
			comment.setPageSize(pageSize);
		}
		Pager<Comment> pager = new Pager<Comment>();
		if(pageNum != null && pageSize != null){
			pager.setPageNo(pageNum);
			pager.setPageSize(pageSize);
		}
		int count = commentMapper.getProCommentCountByProId(comment);
		if(count == 0)return null;
		pager.setRowCount(count);
		if(pageNum != null && pageSize != null){
			pager.setPageCount(count%pageSize==0?(count/pageSize):(count/pageSize+1));
		}
		List<Comment> list = commentMapper.getProCommentByProId(comment);
		pager.setResultList(list);
		return pager;
	}

	@Override
	public Map<String, Object> getCommentStatisByProId(Integer proId) {
		// TODO Auto-generated method stub
		return commentMapper.getCommentStatisByProId(proId);
	}

}
