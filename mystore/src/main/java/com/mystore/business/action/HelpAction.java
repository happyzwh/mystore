package com.mystore.business.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.ConstansMap;
import com.mystore.business.dto.Info;
import com.mystore.business.dto.InfoCate;
import com.mystore.business.service.InfoService;

@Controller("helpAction")
@Scope("prototype")
public class HelpAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Autowired
	private InfoService infoService;
	
	private List<InfoCate> helpMenu;
	
	private String content;
	
	public String center(){
		if(id != null){
			Info info = infoService.getInfoById(id);
			if(info != null){
				content = info.getContent();
			}
		}
		return "center";
	}
	
	public String menu(){
		
		InfoCate infoCate = infoService.getInfoCateByBh(ConstansMap.HELP.getCode());
		if(infoCate != null){
			helpMenu = infoService.getInfoCateByPid(infoCate.getId());
		}
		return "menu";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<InfoCate> getHelpMenu() {
		return helpMenu;
	}

	public void setHelpMenu(List<InfoCate> helpMenu) {
		this.helpMenu = helpMenu;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
