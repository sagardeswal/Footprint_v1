package com.sacri.footprint_v1;

import android.content.Context;
import android.content.SharedPreferences;

import com.sacri.footprint_v1.Entities.UserDetails;

/**
 * Stores userdetails in the mobile
 */
public class UserLocalStore {
    public static final String SP_USER_DETAILS = "userDetails";
    public static final String SP_POST_DETAILS = "postDetails";

    SharedPreferences userDetailsLocalDatabase;
    SharedPreferences postDetailsLocaldatabase;

    public UserLocalStore(Context context){
        userDetailsLocalDatabase = context.getSharedPreferences(SP_USER_DETAILS, 0);
    }

    public void storeUserData(UserDetails userDetails){
        SharedPreferences.Editor spEditor = userDetailsLocalDatabase.edit();
        spEditor.putString("name", userDetails.getName());
        spEditor.putString("mobileNumber", userDetails.getMobileNumber());
        spEditor.putString("gender", userDetails.getGender());
        spEditor.putString("email", userDetails.getEmail());
        spEditor.putString("city", userDetails.getCity());
        spEditor.putString("nationality", userDetails.getNationality());
        spEditor.putString("userName", userDetails.getUserName());
        spEditor.putString("password", userDetails.getPassword());
        spEditor.commit();
    }

    public UserDetails getLoggedInUser(){
        String name = userDetailsLocalDatabase.getString("name", "");
        String mobileNumber = userDetailsLocalDatabase.getString("mobileNumber", "");
        String gender = userDetailsLocalDatabase.getString("gender", "");
        String email = userDetailsLocalDatabase.getString("email", "");
        String city = userDetailsLocalDatabase.getString("city", "");
        String nationality = userDetailsLocalDatabase.getString("nationality", "");
        String userName = userDetailsLocalDatabase.getString("userName", "");
        String password = userDetailsLocalDatabase.getString("password", "");
        UserDetails userDetails = new UserDetails(name,mobileNumber,gender,email,city,nationality,userName,password);
        return userDetails;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userDetailsLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if(userDetailsLocalDatabase.getBoolean("loggedIn", false)==true){
            return true;
        } else {
            return false;
        }

    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userDetailsLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
