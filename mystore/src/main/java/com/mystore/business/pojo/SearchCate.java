package com.mystore.business.pojo;

import java.util.List;

public class SearchCate {
	
	private Integer id;
	
	private Integer name;
	
	private String url;
	
	private SearchCate searchCate;
	
	private List<SearchCate> searchCates;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getName() {
		return name;
	}

	public void setName(Integer name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public SearchCate getSearchCate() {
		return searchCate;
	}

	public void setSearchCate(SearchCate searchCate) {
		this.searchCate = searchCate;
	}

	public List<SearchCate> getSearchCates() {
		return searchCates;
	}

	public void setSearchCates(List<SearchCate> searchCates) {
		this.searchCates = searchCates;
	}

}
