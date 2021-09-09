package com.airlines.flyaway.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.airlines.flyaway.constants.UserStatus;
import com.airlines.flyaway.constants.UserTypes;
import com.airlines.flyaway.constants.convertors.UserStatusConvertor;
import com.airlines.flyaway.constants.convertors.UserTypesConvertor;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 438912828052334704L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long userId;
	
	@Column
	private String userName;
	
	@Column
    private String mobileNum;
	
	@Column
	private String emailId;
	
	@Column
	private String password;
	
	@Column
    @Convert(converter = UserTypesConvertor.class)
	private UserTypes type;
	
	@Column(name="status")
	@Convert(converter = UserStatusConvertor.class)
	private UserStatus uStatus;

	public User() {
		super();
	}
	
	public User(long userId, String userName, String mobileNum, String passWord, UserTypes type, UserStatus uStatus) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.mobileNum = mobileNum;
		this.password = passWord;
		this.type = type;
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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserTypes getType() {
		return type;
	}
	public void setType(UserTypes type) {
		this.type = type;
	}
	
	public UserStatus getuStatus() {
		return uStatus;
	}
	public void setuStatus(UserStatus uStatus) {
		this.uStatus = uStatus;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", mobileNum=" + mobileNum + ", emailId=" + emailId
				+ ", password=" + password + ", type=" + type + ", uStatus=" + uStatus + "]";
	}
}
