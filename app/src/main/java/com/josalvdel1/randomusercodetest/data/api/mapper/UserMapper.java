package com.josalvdel1.randomusercodetest.data.api.mapper;

import com.josalvdel1.randomusercodetest.data.api.entity.ApiUser;
import com.josalvdel1.randomusercodetest.data.api.mapper.base.Mapper;
import com.josalvdel1.randomusercodetest.domain.entity.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserMapper implements Mapper<User, ApiUser> {

    @Inject
    public UserMapper() {
    }

    @Override
    public ApiUser modelToApi(User model) {
        throw new UnsupportedOperationException("method not implemented");
    }

    @Override
    public User apiToModel(ApiUser apiUser) {

        User user = new User(apiUser.getId().getIdName() + apiUser.getId().getValue());

        user.setGender(apiUser.getGender());
        user.setEmail(apiUser.getEmail());
        user.setPhone(apiUser.getPhone());
        user.setRegisteredDate(apiUser.getRegisteredDate());

        ApiUser.ApiUserLocation apiUserLocation = apiUser.getLocation();
        if (apiUserLocation != null) {
            user.setState(apiUserLocation.getState());
            user.setCity(apiUserLocation.getCity());
            user.setStreet(apiUserLocation.getStreet());
            user.setPostalCode(apiUserLocation.getPostalCode() != null
                    ? apiUserLocation.getPostalCode().toString()
                    : null);
        }

        ApiUser.ApiUserName apiUserName = apiUser.getName();
        if (apiUserName != null) {
            user.setFirstName(apiUserName.getFirstName());
            user.setLastName(apiUserName.getLastName());
        }

        ApiUser.ApiUserPictures apiUserPictures = apiUser.getPictures();
        if (apiUserPictures != null) {
            user.setLargePicture(apiUserPictures.getLargePicture());
            user.setMediumPicture(apiUserPictures.getMediumPicture());
            user.setThumbnail(apiUserPictures.getThumbnail());
        }
        return user;
    }
}
