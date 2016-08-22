package com.mystore.business.dto;

public class User extends BasicDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer rankId;
	
	private String userName;
	
	private String password;
	
	private String nickName;
	
	private String realName;
	
	private String sex;
	
	private String mobile;
	
	private String phone;
	
	private String isMobileValid;
	
	private String email;
	
	private String isEmailValid;
	
	private String ipLastLogin;
	
	private String pwdPay;
	
	private String type;
	
	private String imgHead;
	
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRankId() {
		return rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIsMobileValid() {
		return isMobileValid;
	}

	public void setIsMobileValid(String isMobileValid) {
		this.isMobileValid = isMobileValid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsEmailValid() {
		return isEmailValid;
	}

	public void setIsEmailValid(String isEmailValid) {
		this.isEmailValid = isEmailValid;
	}

	public String getIpLastLogin() {
		return ipLastLogin;
	}

	public void setIpLastLogin(String ipLastLogin) {
		this.ipLastLogin = ipLastLogin;
	}

	public String getPwdPay() {
		return pwdPay;
	}

	public void setPwdPay(String pwdPay) {
		this.pwdPay = pwdPay;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImgHead() {
		return imgHead;
	}

	public void setImgHead(String imgHead) {
		this.imgHead = imgHead;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
