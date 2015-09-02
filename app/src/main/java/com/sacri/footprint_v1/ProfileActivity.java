package com.sacri.footprint_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sacri.footprint_v1.Entities.UserDetails;

public class ProfileActivity extends AppCompatActivity {

    private UserLocalStore userLocalStore;
    private TextView tvName;
    private TextView tvMobile;
    private TextView tvEmail;
    private TextView tvGender;
    private TextView tvCity;
    private TextView tvNationality;
    private TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvName = (TextView) findViewById(R.id.displayNameTextView);
        tvMobile = (TextView) findViewById(R.id.displayMobileTextView);
        tvEmail = (TextView) findViewById(R.id.displayEmailTextView);
        tvGender = (TextView) findViewById(R.id.displayGenderTextView);
        tvCity = (TextView) findViewById(R.id.displayCityTextView);
        tvNationality = (TextView) findViewById(R.id.displayNationalityTextView);
        tvUsername = (TextView) findViewById(R.id.displayUsernameTextView);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(authenticate()==true){
            displayUserDetails();

        } else{
            startActivity(new Intent(ProfileActivity.this,LoginPage.class));
        }
    }

    private void displayUserDetails(){
        UserDetails userDetails = userLocalStore.getLoggedInUser();
        tvName.setText(userDetails.getName());
        tvMobile.setText(userDetails.getMobileNumber());
        tvEmail.setText(userDetails.getEmail());
        tvGender.setText(userDetails.getGender());
        tvCity.setText(userDetails.getCity());
        tvNationality.setText(userDetails.getNationality());
        tvUsername.setText(userDetails.getUserName());

    }

    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }


    public void signOutButtonClicked(View view){

        userLocalStore.clearUserData();
        userLocalStore.setUserLoggedIn(false);

        Intent i = new Intent(this, LoginPage.class);
        startActivity(i);
    }


}
