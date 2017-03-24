package com.solt_inc.model.dto;

import java.io.Serializable;

import com.solt_inc.model.entity.UserEntity;

public class UserDto implements Serializable {

    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}