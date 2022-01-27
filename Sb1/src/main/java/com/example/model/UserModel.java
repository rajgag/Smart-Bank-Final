package com.example.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class UserModel {

	private int corporateId;
	
	@NotEmpty(message = "*Required")
	@Size(max = 15,message="max 15 char only")
	private String userLoginId;
	
	@NotEmpty(message = "*Required")
	private String userPassword;
	
	@NotEmpty(message = "*Required")
	private String userAdd;
	
	@NotEmpty(message = "*Required")
	private String userDept;
	
	@Pattern(regexp = "^[0-9]{10}$" ,message = "Invalid Ph-No")
	private String userPhno;
	
	
	private String userStatus;
	private String passChanged;
	
	
	public int getCorporateId() {
		return corporateId;
	}

	public void setCorporateId(int corporateId) {
		this.corporateId = corporateId;
	}

	public String getUserAdd() {
		return userAdd;
	}

	public void setUserAdd(String userAdd) {
		this.userAdd = userAdd;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public String getUserPhno() {
		return userPhno;
	}

	public void setUserPhno(String userPhno) {
		this.userPhno = userPhno;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getPassChanged() {
		return passChanged;
	}

	public void setPassChanged(String passChanged) {
		this.passChanged = passChanged;
	}
	

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
