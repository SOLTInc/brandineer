package com.solt_inc.model.entity;

public class UserEntity {
    private String name;
    private String icon;
    private String age;
    private String jobCategory;
    private String location;
    private String annualIncome;
    
    public UserEntity() {
    }
    
    public UserEntity(String name) {
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
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