package com.solt_inc.model.entity;

import java.io.Serializable;
import java.util.List;

public class UserEntity implements Serializable {
    
    private int   id;
    private String firstName;
    private String lastName;
    private String photoName;
    private String age;
    private String jobCategory;
    private String location;
    private String annualIncome;

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
    
    public String getFirstName() {
        return this.firstName;
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
    
    public String getPhotoName() {
        return photoName;
    }
    
    public void setPhotoName(String photoName) {
        this.photoName = photoName;
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
}