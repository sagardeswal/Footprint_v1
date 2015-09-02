package com.sacri.footprint_v1.EditTextEntities;

import android.view.View;
import android.widget.EditText;

import com.sacri.footprint_v1.R;

/**
 * Created by bazinga on 28/08/15.
 */
public class ETUserDetails {

    private EditText etName;
    private EditText etMobileNumber;
    private EditText etGender;
    private EditText etEmail;
    private EditText etCity;
    private EditText etNationality;
    private EditText etUsername;
    private EditText etPassword;

    public ETUserDetails(View view){
        etName = (EditText) view.findViewById(R.id.nameEditText);
        etMobileNumber = (EditText) view.findViewById(R.id.mobileEditText);
        etGender = (EditText) view.findViewById(R.id.emailEditText);
        etEmail = (EditText) view.findViewById(R.id.genderEditText);
        etCity = (EditText) view.findViewById(R.id.cityEditText);
        etNationality = (EditText) view.findViewById(R.id.nationalityEditText);
        etUsername = (EditText) view.findViewById(R.id.usernameEditText);
        etPassword = (EditText) view.findViewById(R.id.passwordEditText);
    }

    public EditText getEtCity() {
        return etCity;
    }

    public void setEtCity(EditText etCity) {
        this.etCity = etCity;
    }

    public EditText getEtEmail() {
        return etEmail;
    }

    public void setEtEmail(EditText etEmail) {
        this.etEmail = etEmail;
    }

    public EditText getEtGender() {
        return etGender;
    }

    public void setEtGender(EditText etGender) {
        this.etGender = etGender;
    }

    public EditText getEtMobileNumber() {
        return etMobileNumber;
    }

    public void setEtMobileNumber(EditText etMobileNumber) {
        this.etMobileNumber = etMobileNumber;
    }

    public EditText getEtName() {
        return etName;
    }

    public void setEtName(EditText etName) {
        this.etName = etName;
    }

    public EditText getEtNationality() {
        return etNationality;
    }

    public void setEtNationality(EditText etNationality) {
        this.etNationality = etNationality;
    }

    public EditText getEtPassword() {
        return etPassword;
    }

    public void setEtPassword(EditText etPassword) {
        this.etPassword = etPassword;
    }

    public EditText getEtUsername() {
        return etUsername;
    }

    public void setEtUsername(EditText etUsername) {
        this.etUsername = etUsername;
    }
}
