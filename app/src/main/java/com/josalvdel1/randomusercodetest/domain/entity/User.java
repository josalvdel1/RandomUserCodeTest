package com.josalvdel1.randomusercodetest.domain.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

import static com.josalvdel1.randomusercodetest.domain.entity.User.Gender.FEMALE;
import static com.josalvdel1.randomusercodetest.domain.entity.User.Gender.MALE;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    private String id;
    @Gender
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
    private boolean blackListed;

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

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getLargePicture() {
        return largePicture;
    }

    public void setLargePicture(String largePicture) {
        this.largePicture = largePicture;
    }

    public String getMediumPicture() {
        return mediumPicture;
    }

    public void setMediumPicture(String mediumPicture) {
        this.mediumPicture = mediumPicture;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setBlackListed(boolean blackListed) {
        this.blackListed = blackListed;
    }

    public boolean isBlackListed() {
        return blackListed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({MALE, FEMALE})
    public @interface Gender {
        String MALE = "Male";
        String FEMALE = "Female";
    }
}