package com.example.touristguide.ApiCall.Models;

import com.google.gson.annotations.SerializedName;

public class SaveBookingResBean {

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

		@SerializedName("unique_id")
		private int uniqueId;

		@SerializedName("updated_at")
		private String updatedAt;

		@SerializedName("user_id")
		private int userId;

		@SerializedName("total_amount")
		private String totalAmount;

		@SerializedName("booking_date")
		private String bookingDate;

		@SerializedName("description")
		private String description;

		@SerializedName("created_at")
		private String createdAt;

		@SerializedName("id")
		private int id;

		@SerializedName("property_id")
		private String propertyId;

		@SerializedName("property_name")
		private String propertyName;

		@SerializedName("status")
		private String status;

		public int getUniqueId(){
			return uniqueId;
		}

		public String getUpdatedAt(){
			return updatedAt;
		}

		public int getUserId(){
			return userId;
		}

		public String getTotalAmount(){
			return totalAmount;
		}

		public String getBookingDate(){
			return bookingDate;
		}

		public String getDescription(){
			return description;
		}

		public String getCreatedAt(){
			return createdAt;
		}

		public int getId(){
			return id;
		}

		public String getPropertyId(){
			return propertyId;
		}

		public String getPropertyName(){
			return propertyName;
		}

		public String getStatus(){
			return status;
		}
	}
}