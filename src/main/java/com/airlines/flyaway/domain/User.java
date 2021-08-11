package com.airlines.flyaway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.airlines.flyaway.constants.UserStatus;
import com.airlines.flyaway.constants.UserTypes;

@Entity
@Table(name = "user")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private long userId;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "mobileNum")
    private String mobileNum;
	
	@Column(name = "passWord")
	private String passWord;
	
//	@Column(name = "")
	private UserTypes uTypes;
	private UserStatus uStatus;

	public User() {
		super();
	}
	
	public User(long userId, String userName, String mobileNum, String passWord, UserTypes uTypes, UserStatus uStatus) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.mobileNum = mobileNum;
		this.passWord = passWord;
		this.uTypes = uTypes;
		this.uStatus = uStatus;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public UserTypes getuTypes() {
		return uTypes;
	}
	public void setuTypes(UserTypes uTypes) {
		this.uTypes = uTypes;
	}
	public UserStatus getuStatus() {
		return uStatus;
	}
	public void setuStatus(UserStatus uStatus) {
		this.uStatus = uStatus;
	}
	
	
	
	
	

}
