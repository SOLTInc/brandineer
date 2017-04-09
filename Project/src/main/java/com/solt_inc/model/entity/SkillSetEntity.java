package com.solt_inc.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class SkillSetEntity implements Serializable {

    private int id;
    private int userId;
    private LocalDate projectStart;
    private LocalDate projectEnd;
    @NotNull
    private String projectName;
    private String projectDescription;
    private int processStart = -1;
    private int processEnd = -1;
    private String programmingLanguage;
    private String DB;
    private String IDE;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDB() {
        return DB;
    }

    public void setDB(String dB) {
        DB = dB;
    }

    public String getIDE() {
        return IDE;
    }

    public void setIDE(String iDE) {
        IDE = iDE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(LocalDate projectStart) {
        this.projectStart = projectStart;
    }

    public Date getSqlProjectStart() {
        return Date.valueOf(this.projectStart);
    }

    public void setSqlProjectStart(Date projectStart) {
        this.projectStart = projectStart.toLocalDate();
    }

    public LocalDate getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(LocalDate projectEnd) {
        this.projectEnd = projectEnd;
    }

    public Date getSqlProjectEnd() {
        return Date.valueOf(this.projectEnd);
    }

    public void setSqlProjectEnd(Date projectEnd) {
        this.projectEnd = projectEnd.toLocalDate();
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public int getProcessStart() {
        return processStart;
    }

    public void setProcessStart(int processStart) {
        this.processStart = processStart;
    }

    public int getProcessEnd() {
        return processEnd;
    }

    public void setProcessEnd(int processEnd) {
        this.processEnd = processEnd;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}