package com.solt_inc.model.entity;

public class HobbyEntity {
    
    private String hobbyName;
    private String hobbyImage;
    
    public HobbyEntity() {
    }
    
    public HobbyEntity(String name) {
        
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