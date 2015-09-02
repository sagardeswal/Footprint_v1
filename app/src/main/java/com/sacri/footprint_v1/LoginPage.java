package com.sacri.footprint_v1;

import android.app.AlertDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sacri.footprint_v1.Entities.UserDetails;

public class LoginPage extends AppCompatActivity {

    UserLocalStore userLocalStore;
    EditText etUserName;
    EditText etPassword;
    Button loginButton;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        etUserName = (EditText) findViewById(R.id.usernameEditText);
        etPassword = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        userLocalStore = new UserLocalStore(this);
    }

    public void loginButtonClicked(View view)
    {
        String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        UserDetails userDetails = new UserDetails(username,password);
        authenticate(userDetails);
        userLocalStore.setUserLoggedIn(true);

        /*
        Intent i = new Intent(this, Feed.class);
        final EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        String userName = usernameEditText.getText().toString();
        i.putExtra("userName", userName);
        startActivity(i);
        */
    }

    public void signUpButtonClicked(View view){
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

    public void authenticate(UserDetails userDetails){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(userDetails, new GetUserCallback() {
            @Override
            public void done(UserDetails returnedUserDetails) {
                if (returnedUserDetails == null) {
                    showErrorMessage();
                }
                else{
                    Log.i("com.sacri.fottprint_v1", "returnedUserData: " + returnedUserDetails.toString());
                    logUserIn(returnedUserDetails);
                }
            }
        });
    }

    private void showErrorMessage(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginPage.this);
        dialogBuilder.setMessage("Incorrect User");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(UserDetails userDetails){
        userLocalStore.storeUserData(userDetails);
        userLocalStore.setUserLoggedIn(true);

//        startActivity(new Intent(this, Feed.class));
        startActivity(new Intent(this, ProfileActivity.class));
    }
}
