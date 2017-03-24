package com.solt_inc.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class UserEntity implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String photoName;
    private LocalDate birthday;
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
        this.birthday = birthday.toLocalDate();
    }

    public Date getSqlBirthday() {

        return Date.valueOf(this.birthday);
        // Calendar cal = Calendar.getInstance();
        // cal.setTime(this.birthday);
        // cal.set(Calendar.HOUR_OF_DAY, 0);
        // cal.set(Calendar.MINUTE, 0);
        // cal.set(Calendar.SECOND, 0);
        // cal.set(Calendar.MILLISECOND, 0);
        // java.sql.Date birthday = new java.sql.Date(cal.getTimeInMillis());
        // return birthday;
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
        int age;
        // LocalDate birthday = LocalDate.parse(getBirthday().toString());
        LocalDate birthday = LocalDate.of(2015, 1, 1);
        LocalDate today = LocalDate.now();
        age = Period.between(birthday, today).getYears();

        return age;
    }
}