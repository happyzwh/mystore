package com.mystore.business.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("securityAction")
@Scope("prototype")
public class SecurityAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String index(){
		return "index";
	}

}
