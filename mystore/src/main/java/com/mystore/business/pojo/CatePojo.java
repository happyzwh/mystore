package com.mystore.business.pojo;

import java.util.List;

public class CatePojo implements java.io.Serializable{
	
	private Integer id;
	
	private String name;
	
	private List<CatePojo> sons;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CatePojo> getSons() {
		return sons;
	}

	public void setSons(List<CatePojo> sons) {
		this.sons = sons;
	}
	
	

}
