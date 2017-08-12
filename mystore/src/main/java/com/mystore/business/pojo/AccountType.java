package com.mystore.business.pojo;

import org.apache.commons.lang3.StringUtils;

public enum AccountType {
	
	GENERAL("普通","0"),
	GOLD("金币","1");
	
	private String name;
	private String value;
	
	private AccountType(String name,String value){
		this.name = name;
		this.value = value;
	}
	
	public static AccountType getAccountTypeByValue(String value){
		if(StringUtils.isBlank(value)){
			return null;
		}
		for(AccountType accountType:AccountType.values()){
			if(accountType.getValue().equals(value)){
				return accountType;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
