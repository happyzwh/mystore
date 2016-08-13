package com.mystore.business.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.dto.Info;
import com.mystore.business.service.InfoService;

@ParentPackage("struts-default")  
@Namespace("/info")
@Controller("infoAction")
@Scope("prototype")
public class InfoAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private InfoService infoService;
	
	private Integer id;
	
	private Info info;
	
//	@Action(value = "detail", results = { @Result(location = "/pages/notice/detail.jsp")})
	public String detail(){
		
		if(id != null){
			info = infoService.getInfoById(id);
		}
		
		return "detail";
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
	

}
