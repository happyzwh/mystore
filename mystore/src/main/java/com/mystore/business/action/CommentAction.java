package com.mystore.business.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constants;
import com.mystore.business.common.PageInfo;
import com.mystore.business.common.Pager;
import com.mystore.business.dto.Comment;
import com.mystore.business.dto.User;
import com.mystore.business.service.CommentService;

@Controller("commentAction")
@Scope("prototype")
public class CommentAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;
	
	private Integer proId;
	
	private String content;
	
	private Integer score;	
	
	private Integer type;
	
	private List<Comment> list;
	
	private Map<String,Object> proCommentStatis;
	
	public void save() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(proId == null || StringUtils.isBlank(content) || score == null){
				returnCode = -2;
				return;
			}
			
			String sessionId = ServletActionContext.getRequest().getSession().getId();
			User user = (User)redisTemplate.opsForValue().get(Constants.KEY_SESSION+"_"+sessionId);
			
			Comment comment = new Comment();
			comment.setId_pro(proId);
			comment.setContent(content);
			comment.setScore(score);
			comment.setId_user(user.getId());
			comment.setUserName(user.getUserName());
			
			commentService.addComment(comment);
			
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	}

	public String getCommentPage(){
		
		if(proId == null){
			return "commentPage";
		}
		
		proCommentStatis = commentService.getCommentStatisByProId(proId);
		
		Pager<Comment> pager = commentService.getProCommentByProId(proId, type, this.pageNo, this.pageSize);
		if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
			  list = pager.getResultList();
			  pageInfo = PageInfo.setPage(pager.getPageNo(), pager.getPageSize(), pager.getPageCount(), pager.getRowCount());
		}
		
		return "commentPage";
	}
	
	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Comment> getList() {
		return list;
	}

	public void setList(List<Comment> list) {
		this.list = list;
	}

	public Map<String, Object> getProCommentStatis() {
		return proCommentStatis;
	}

	public void setProCommentStatis(Map<String, Object> proCommentStatis) {
		this.proCommentStatis = proCommentStatis;
	}
	
}
