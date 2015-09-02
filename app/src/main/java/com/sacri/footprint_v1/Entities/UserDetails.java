package com.sacri.footprint_v1.Entities;

/**
 * Entity class that carries details of the user
 */
public class UserDetails {

    private String name;
    private String mobileNumber;
    private String gender;
    private String email;
    private String city;
    private String nationality;
    private String userName;
    private String password;

    public UserDetails(String name, String mobileNumber, String email, String gender, String city, String nationality,
                       String userName, String password){
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.email = email;
        this.city = city;
        this.nationality = nationality;
        this.userName = userName;
        this.password = password;

    }

    public UserDetails(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
