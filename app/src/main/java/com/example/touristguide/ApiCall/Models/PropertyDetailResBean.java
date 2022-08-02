package com.example.touristguide.ApiCall.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PropertyDetailResBean {

	@SerializedName("data")
	private Data data;

	@SerializedName("status")
	private boolean status;

	public Data getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}

	public class Data{

		@SerializedName("business_name")
		private String businessName;

		@SerializedName("image")
		private String image;

		@SerializedName("deleted_status")
		private int deletedStatus;

		@SerializedName("address")
		private String address;

		@SerializedName("city")
		private Object city;

		@SerializedName("latitude")
		private String latitude;

		@SerializedName("mobile_no")
		private Object mobileNo;

		@SerializedName("description")
		private String description;

		@SerializedName("created_at")
		private String createdAt;

		@SerializedName("city_name")
		private Object cityName;

		@SerializedName("updated_at")
		private String updatedAt;

		@SerializedName("state_name")
		private String stateName;

		@SerializedName("price")
		private String price;

		@SerializedName("property_images")
		private List<PropertyImagesItem> propertyImages;

		@SerializedName("cat_id")
		private int catId;

		@SerializedName("location")
		private Object location;

		@SerializedName("id")
		private int id;

		@SerializedName("state")
		private int state;

		@SerializedName("category")
		private Category category;

		@SerializedName("longitude")
		private String longitude;

		@SerializedName("status")
		private int status;

		public String getBusinessName(){
			return businessName;
		}

		public String getImage(){
			return image;
		}

		public int getDeletedStatus(){
			return deletedStatus;
		}

		public String getAddress(){
			return address;
		}

		public Object getCity(){
			return city;
		}

		public String getLatitude(){
			return latitude;
		}

		public Object getMobileNo(){
			return mobileNo;
		}

		public String getDescription(){
			return description;
		}

		public String getCreatedAt(){
			return createdAt;
		}

		public Object getCityName(){
			return cityName;
		}

		public String getUpdatedAt(){
			return updatedAt;
		}

		public String getStateName(){
			return stateName;
		}

		public String getPrice(){
			return price;
		}

		public List<PropertyImagesItem> getPropertyImages(){
			return propertyImages;
		}

		public int getCatId(){
			return catId;
		}

		public Object getLocation(){
			return location;
		}

		public int getId(){
			return id;
		}

		public int getState(){
			return state;
		}

		public Category getCategory(){
			return category;
		}

		public String getLongitude(){
			return longitude;
		}

		public int getStatus(){
			return status;
		}

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

		public class Category{

			@SerializedName("image")
			private String image;

			@SerializedName("deleted_status")
			private int deletedStatus;

			@SerializedName("updated_at")
			private String updatedAt;

			@SerializedName("name")
			private String name;

			@SerializedName("created_at")
			private String createdAt;

			@SerializedName("id")
			private int id;

			@SerializedName("status")
			private int status;

			public String getImage(){
				return image;
			}

			public int getDeletedStatus(){
				return deletedStatus;
			}

			public String getUpdatedAt(){
				return updatedAt;
			}

			public String getName(){
				return name;
			}

			public String getCreatedAt(){
				return createdAt;
			}

			public int getId(){
				return id;
			}

			public int getStatus(){
				return status;
			}
		}
	}
}