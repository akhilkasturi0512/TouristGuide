package com.example.touristguide.ApiCall;

import com.example.touristguide.ApiCall.Models.CategoryResBean;
import com.example.touristguide.ApiCall.Models.HotelsPropertyResBean;
import com.example.touristguide.ApiCall.Models.LoginResBean;
import com.example.touristguide.ApiCall.Models.OtpResBean;
import com.example.touristguide.ApiCall.Models.ProfileResBean;
import com.example.touristguide.ApiCall.Models.PropertyDetailResBean;
import com.example.touristguide.ApiCall.Models.RegisterResBean;
import com.example.touristguide.ApiCall.Models.SaveBookingResBean;
import com.example.touristguide.ApiCall.Models.SearchResBean;
import com.example.touristguide.ApiCall.Models.UpdateProfileResBean;
import com.example.touristguide.Utils.ApiConstants;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @FormUrlEncoded
    @POST(ApiConstants.LOGIN_URL)
    Call<LoginResBean> login(@Field("email_mobile") String email);

    @FormUrlEncoded
    @POST(ApiConstants.OTP_URL)
    Call<OtpResBean> Otp(@Field("email") String email,
                         @Field("otp") String otp);

    @FormUrlEncoded
    @POST(ApiConstants.REGISTER_URL)
    Call<RegisterResBean> Register(@Field("name") String name,
                                   @Field("email") String email,
                                   @Field("mobile") String mobile);


    @POST(ApiConstants.CATEGORY_URL)
    @Headers({"Accept: application/json"})
    Call<CategoryResBean> Category(@Header("Authorization") String accessToken);

    @FormUrlEncoded
    @POST(ApiConstants.Search_URL)
    @Headers({"Accept: application/json"})
    Call<SearchResBean> Search(@Header("Authorization") String accessToken,
                               @Field("search") String bri);

    @FormUrlEncoded
    @POST(ApiConstants.PROPERTY_LIST_URL)
    @Headers({"Accept: application/json"})
    Call<HotelsPropertyResBean> PropertyList(@Header("Authorization") String accessToken,
                                             @Field("cat_id") String cat_id);

    @FormUrlEncoded
    @POST(ApiConstants.PROPERTY_DETAIL_LIST_URL)
    @Headers({"Accept: application/json"})
    Call<PropertyDetailResBean> PropertyDetail(@Header("Authorization") String accessToken,
                                               @Field("property_id") String property_id);

    @FormUrlEncoded
    @POST(ApiConstants.SAVE_BOOKING_URL)
    @Headers({"Accept: application/json"})
    Call<SaveBookingResBean> SaveBooking(@Header("Authorization") String accessToken,
                                         @Field("property_id") String property_id,
                                         @Field("booking_date")String booking_date,
                                         @Field("description") String description);


    @POST(ApiConstants.PROFILE_URL)
    @Headers({"Accept: application/json"})
    Call<ProfileResBean> Profile(@Header("Authorization") String accessToken);

    @Multipart
    @POST(ApiConstants.UPDATE_PROFILE_URL)
    @Headers({"Accept: application/json"})
    Call<UpdateProfileResBean> UpdateProfile(@Header("Authorization") String accessToken,
                                             @Part("name")RequestBody name,
                                             @Part("mobile")RequestBody mobile,
                                             @Part("email")RequestBody email,
                                             @Part MultipartBody.Part user_image);


}



