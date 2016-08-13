package com.mystore.business.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.PageInfo;
import com.mystore.business.common.Pager;
import com.mystore.business.dto.Consult;
import com.mystore.business.service.ConsultService;

@Controller("consultAction")
@Scope("prototype")
public class ConsultAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ConsultService consultService;
	
	private Integer proId;
	
	private String content;
	
	private String type;
	
	private List<Consult> list;
	
	private Map<String,Object> proConsultStatis;
	
	public void save() throws IOException{
		Integer returnCode = 0;
		try{
			
			if(proId == null || StringUtils.isBlank(content) || StringUtils.isBlank(type)){
				returnCode = -2;
				return;
			}
			
			Consult consult = new Consult();
			consult.setId_pro(proId);
			consult.setContent(content);
			consult.setType(type);
			
			consultService.addConsult(consult);
			
		}catch(Exception e){
			returnCode = -1;
		}finally{
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().print(returnCode);
		}
	}
	
	public String getConsultPage(){
		
		if(proId == null){
			return "commentPage";
		}
		
		proConsultStatis = consultService.getConsultStatisByProId(proId);
		
		Pager<Consult> pager = consultService.getProConsultByProId(proId, type, this.pageNo, this.pageSize);
		if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
			  list = pager.getResultList();
			  pageInfo = PageInfo.setPage(pager.getPageNo(), pager.getPageSize(), pager.getPageCount(), pager.getRowCount());
		}
		return "consultPage";
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Consult> getList() {
		return list;
	}

	public void setList(List<Consult> list) {
		this.list = list;
	}

	public Map<String, Object> getProConsultStatis() {
		return proConsultStatis;
	}

	public void setProConsultStatis(Map<String, Object> proConsultStatis) {
		this.proConsultStatis = proConsultStatis;
	}
	
}
