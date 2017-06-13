package com.josalvdel1.randomusercodetest.api.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ApiUser {

    @SerializedName("id")
    @Expose
    private ApiUserId id;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("name")
    @Expose
    private ApiUserName name;

    @SerializedName("location")
    @Expose
    private ApiUserLocation location;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("registered")
    @Expose
    private Date registeredDate;

    @SerializedName("picture")
    @Expose
    private ApiUserPictures pictures;

    public ApiUserId getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public ApiUserName getName() {
        return name;
    }

    public ApiUserLocation getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public ApiUserPictures getPictures() {
        return pictures;
    }

    public class ApiUserId{
        @SerializedName("name")
        @Expose
        private String idName;

        @SerializedName("value")
        @Expose
        private String value;

        public String getIdName() {
            return idName;
        }

        public String getValue() {
            return value;
        }
    }

    public class ApiUserName {

        @SerializedName("first")
        @Expose
        private String firstName;

        @SerializedName("last")
        @Expose
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }

    public class ApiUserLocation {

        @SerializedName("street")
        @Expose
        private String street;

        @SerializedName("city")
        @Expose
        private String city;

        @SerializedName("state")
        @Expose
        private String state;

        @SerializedName("zip")
        @Expose
        private Integer postalCode;

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public Integer getPostalCode() {
            return postalCode;
        }
    }

    public class ApiUserPictures {

        @SerializedName("large")
        @Expose
        private String largePicture;

        @SerializedName("medium")
        @Expose
        private String mediumPicture;

        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;

        public String getLargePicture() {
            return largePicture;
        }

        public String getMediumPicture() {
            return mediumPicture;
        }

        public String getThumbnail() {
            return thumbnail;
        }
    }

}
