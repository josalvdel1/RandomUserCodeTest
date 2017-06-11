package com.josalvdel1.randomusercodetest.domain.entity;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

import static com.josalvdel1.randomusercodetest.domain.entity.User.GENDER.FEMALE;
import static com.josalvdel1.randomusercodetest.domain.entity.User.GENDER.MALE;

public class User {

    private String id;
    @GENDER
    private String gender;
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String postalCode;
    private String state;
    private String email;
    private String phone;
    private Date registeredDate;
    private String largePicture;
    private String mediumPicture;
    private String thumbnail;

    /**
     * avoid object instantiation without required fields
     */
    private User() {
    }

    public User(@NonNull String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public User setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public User setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getState() {
        return state;
    }

    public User setState(String state) {
        this.state = state;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public User setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
        return this;
    }

    public String getLargePicture() {
        return largePicture;
    }

    public User setLargePicture(String largePicture) {
        this.largePicture = largePicture;
        return this;
    }

    public String getMediumPicture() {
        return mediumPicture;
    }

    public User setMediumPicture(String mediumPicture) {
        this.mediumPicture = mediumPicture;
        return this;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public User setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        User user = (User) o;
        return id.equals(user.getId());
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({MALE, FEMALE})
    public @interface GENDER {
        String MALE = "Male";
        String FEMALE = "Female";
    }
}
