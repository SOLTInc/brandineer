package com.solt_inc.component.modalWindow.icon;

import java.io.Serializable;

public class HobbyIconEntity implements Serializable {

    private int id;
    private String iconName;
    private String iconFileName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getIconFileName() {
        return iconFileName;
    }

    public void setIconFileName(String iconFileName) {
        this.iconFileName = iconFileName;
    }

}
