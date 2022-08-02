package com.example.touristguide.ApiCall.Models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategoryResBean implements Serializable {

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("status")
	private boolean status;

	public List<DataItem> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}

	public class DataItem implements Serializable{

		@SerializedName("image")
		private String image;

		@SerializedName("name")
		private String name;

		@SerializedName("id")
		private int id;

		public String getImage(){
			return image;
		}

		public String getName(){
			return name;
		}

		public int getId(){
			return id;
		}
	}
}