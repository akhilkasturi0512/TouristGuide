package com.example.touristguide.ApiCall.Models;

import com.google.gson.annotations.SerializedName;

public class LoginResBean {

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}