package com.example.touristguide.ApiCall.Models;

import com.google.gson.annotations.SerializedName;

public class UpdateProfileResBean {

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public Data getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}

	public class Data{

		@SerializedName("device_key")
		private Object deviceKey;

		@SerializedName("deleted_status")
		private int deletedStatus;

		@SerializedName("address")
		private Object address;

		@SerializedName("city")
		private int city;

		@SerializedName("year")
		private String year;

		@SerializedName("verify_otp_status")
		private int verifyOtpStatus;

		@SerializedName("mobile")
		private String mobile;

		@SerializedName("created_at")
		private String createdAt;

		@SerializedName("email_verified_at")
		private Object emailVerifiedAt;

		@SerializedName("is_property_added")
		private String isPropertyAdded;

		@SerializedName("profile_image")
		private String profileImage;

		@SerializedName("refer_id")
		private Object referId;

		@SerializedName("month")
		private int month;

		@SerializedName("register_otp")
		private int registerOtp;

		@SerializedName("updated_at")
		private String updatedAt;

		@SerializedName("name")
		private String name;

		@SerializedName("id")
		private int id;

		@SerializedName("state")
		private Object state;

		@SerializedName("referal_id")
		private String referalId;

		@SerializedName("day")
		private int day;

		@SerializedName("email")
		private String email;

		@SerializedName("status")
		private int status;

		public Object getDeviceKey(){
			return deviceKey;
		}

		public int getDeletedStatus(){
			return deletedStatus;
		}

		public Object getAddress(){
			return address;
		}

		public int getCity(){
			return city;
		}

		public String getYear(){
			return year;
		}

		public int getVerifyOtpStatus(){
			return verifyOtpStatus;
		}

		public String getMobile(){
			return mobile;
		}

		public String getCreatedAt(){
			return createdAt;
		}

		public Object getEmailVerifiedAt(){
			return emailVerifiedAt;
		}

		public String getIsPropertyAdded(){
			return isPropertyAdded;
		}

		public String getProfileImage(){
			return profileImage;
		}

		public Object getReferId(){
			return referId;
		}

		public int getMonth(){
			return month;
		}

		public int getRegisterOtp(){
			return registerOtp;
		}

		public String getUpdatedAt(){
			return updatedAt;
		}

		public String getName(){
			return name;
		}

		public int getId(){
			return id;
		}

		public Object getState(){
			return state;
		}

		public String getReferalId(){
			return referalId;
		}

		public int getDay(){
			return day;
		}

		public String getEmail(){
			return email;
		}

		public int getStatus(){
			return status;
		}
	}
}