package com.solt_inc.model.entity;

import java.io.Serializable;
import java.util.List;

public class UserEntity implements Serializable {
    
    private int   id;
    private String name;
    private String photo;
    private String age;
    private String jobCategory;
    private String location;
    private String annualIncome;
    private List<HobbyEntity> hobbyList;
    
    public UserEntity() {
    }
    
    public UserEntity(String name) {
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhoto() {
        return photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public String getAge() {
        return age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    public String getJobCategory() {
        return jobCategory;
    }
    
    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getAnnualIncome() {
        return annualIncome;
    }
    
    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }
    
    public List<HobbyEntity> getHobbyList() {
        return this.hobbyList;
    }
    public void setHobbyList(String hobbyName, String hobbyImage) {
         
        HobbyEntity hobby = new HobbyEntity(hobbyName, hobbyImage);
        
        this.hobbyList.add(hobby);
    }
}