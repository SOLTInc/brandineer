package com.solt_inc.model.entity;

import java.io.Serializable;

public class DevelopmentProcessEntity implements Serializable {

    private int id;
    private String processName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

}
