package com.solt_inc.model.entity;

import java.io.Serializable;

public class HobbyEntity implements Serializable {
    
    private String hobbyName;
    private String hobbyImage;
    
    public HobbyEntity() {
    }
    
    public HobbyEntity(String hobbyName, String hobbyImage) {
        
        this.hobbyName = hobbyName;
        this.hobbyImage = hobbyImage;
        
    }
    
    public String getHobbyName() {
        return hobbyName;
    }
    
    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }
    
    public String getHobbyImage() {
        return hobbyImage;
    }
    
    public void setHobbyImage(String hobbyImage) {
        this.hobbyImage = hobbyImage;
    }
}