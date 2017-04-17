package com.solt_inc.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.validation.constraints.NotNull;

public class UserEntity implements Serializable {

    private int id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String photoName;
    private LocalDate birthday;
    private int age;
    private String company;
    private String jobCategory;
    private String location;

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setSqlBirthday(Date birthday) {
        if (birthday != null) {
            this.birthday = birthday.toLocalDate();
        }
    }

    public Date getSqlBirthday() {

    	Date date = null;
    	if(this.birthday != null) {
            date =  Date.valueOf(this.birthday);
    	}
    	return date;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String compony) {
        this.company = compony;
    }

    public Integer getAge() {
        if (this.age == 0 && this.birthday != null) {
            LocalDate birthday = LocalDate.parse(this.birthday.toString());
            LocalDate today = LocalDate.now();
            this.age = Period.between(birthday, today).getYears();
        }

        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}