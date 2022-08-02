package com.example.touristguide.Storage;

import android.content.Context;
import android.preference.PreferenceManager;

public class LocalStorage {

    public static void setVisitStoreCategoryId(Context context, String key) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("setVisitStoreCategoryId", key);
        prefsEditor.commit();
    }

    public static String getVisitStoreCategoryId(Context context) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString("setVisitStoreCategoryId", "");
    }
    public static void setIfOrderSuccess(Context context, String key) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("setIfOrderSuccess", key);
        prefsEditor.commit();
    }

    public static String getIfOrderSuccess(Context context) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString("setIfOrderSuccess", "false");
    }
    public static void setIfDashboardAccess(Context context, String key) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("setIfDashboardAccess", key);
        prefsEditor.commit();
    }

    public static String getIfDashboardAccess(Context context) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString("setIfDashboardAccess", "");
    }
    public static void setStoreId(Context context, String key, String storeName) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("setStoreId", key);
        prefsEditor.putString("setStoreName", storeName);
        prefsEditor.commit();
    }

    public static String getStoreId(Context context) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString("setStoreId", "");
    }

    public static String getStoreName(Context context) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString("setStoreName", "");
    }

    public static void setBearerToken(Context context, String key) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("setBearerToken", key);
        prefsEditor.commit();
    }

    public static String getBearerToken(Context context) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString("setBearerToken", "");
    }
    public static void setSelectIndustryPosition(Context context, String key) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("setSelectIndustryPosition", key);
        prefsEditor.commit();
    }

    public static String getSelectIndustryPosition(Context context) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString("setSelectIndustryPosition", "0");
    }
    public static void setLatitude(Context context, String key) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("latitude", key);
        prefsEditor.commit();
    }

    public static String getLatitude(Context context) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString("latitude", "0.0");
    }

    public static void setLongitude(Context context, String key) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("longitude", key);
        prefsEditor.commit();
    }

    public static String getLongitude(Context context) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString("longitude", "0.0");
    }
    public static void setPincode(Context context, String key) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("setPincode", key);
        prefsEditor.commit();
    }
    public static void setAreaId(Context context, String key) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("setAreaId", key);
        prefsEditor.commit();
    }

    public static String getPincode(Context context) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString("setPincode", "");
    }

    public static String getAreaId(Context context) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString("setAreaId", "");
    }

    public static void setDeliveryAddress(Context context, String key) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("setDeliveryAddress", key);
        prefsEditor.commit();
    }

    public static String getDeliveryAddress(Context context) {
        android.content.SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString("setDeliveryAddress", "");
    }

}
