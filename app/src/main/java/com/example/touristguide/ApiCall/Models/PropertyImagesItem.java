package com.example.touristguide.ApiCall.Models;

import com.google.gson.annotations.SerializedName;

public class PropertyImagesItem{

	@SerializedName("image")
	private String image;

	@SerializedName("deleted_status")
	private int deletedStatus;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("image_key")
	private int imageKey;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("service_property_id")
	private int servicePropertyId;

	public String getImage(){
		return image;
	}

	public int getDeletedStatus(){
		return deletedStatus;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getImageKey(){
		return imageKey;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public int getServicePropertyId(){
		return servicePropertyId;
	}
}