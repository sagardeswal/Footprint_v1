package com.sacri.footprint_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.sacri.footprint_v1.Entities.UserDetails;

public class SignUp extends AppCompatActivity {

    Button signUpButton;
    EditText etName;
    EditText etMobileNumber;
    EditText etGender;
    EditText etEmail;
    EditText etCity;
    EditText etNationality;
    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = (EditText) findViewById(R.id.nameEditText);
        etMobileNumber = (EditText) findViewById(R.id.mobileEditText);
        etGender = (EditText) findViewById(R.id.emailEditText);
        etEmail = (EditText) findViewById(R.id.genderEditText);
        etCity = (EditText) findViewById(R.id.cityEditText);
        etNationality = (EditText) findViewById(R.id.nationalityEditText);
        etUsername = (EditText) findViewById(R.id.usernameEditText);
        etPassword = (EditText) findViewById(R.id.passwordEditText);
        signUpButton = (Button) findViewById(R.id.signUpButton);

    }

    public void signUpButtonClicked(View view){

        String name = etName.getText().toString();
        String mobileNumber = etMobileNumber.getText().toString();
        String gender = etGender.getText().toString();
        String email = etEmail.getText().toString();
        String city = etCity.getText().toString();
        String nationality = etNationality.getText().toString();
        String userName = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        UserDetails userDetails = new UserDetails(name,mobileNumber,gender,email,city,nationality,userName,password);
        registerUser(userDetails);



        //Intent intent = new Intent(this, LoginPage.class);
        Toast.makeText(SignUp.this,"Sign Up successful",Toast.LENGTH_LONG).show();
        //startActivity(intent);
    }

    private void registerUser(UserDetails userDetails){
        Log.i("com.sacri.footprint_v1", "Username: " + userDetails.getUserName());
        Log.i("com.sacri.footprint_v1", "Password: " + userDetails.getPassword());
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(userDetails, new GetUserCallback() {
            @Override
            public void done(UserDetails returnedUserDetails) {
                startActivity(new Intent(SignUp.this, LoginPage.class));
            }
        });
    }
}
