package com.solt_inc.model.dto;

import java.io.Serializable;
import java.util.List;
import com.solt_inc.model.entity.*;

public class UserDto implements Serializable {
    
    private UserEntity entity;
    private List<HobbyEntity> hobbyList;
    public  UserEntity getEntity() {
    	return entity;
    }   
    public  void setEntity(UserEntity entity) {
    	this.entity = entity;
    }   
    
    public List<HobbyEntity> getHobbyList() {
        return this.hobbyList;
    }
    public void setHobbyList(String hobbyName, String hobbyImage) {
         
        HobbyEntity hobby = new HobbyEntity(hobbyName, hobbyImage);
        
        this.hobbyList.add(hobby);
    }
}