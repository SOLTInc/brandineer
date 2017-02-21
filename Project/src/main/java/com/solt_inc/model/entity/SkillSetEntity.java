package com.solt_inc.model.entity;

import java.io.Serializable;

public class SkillSetEntity implements Serializable {
    
    private String skillName;
    private String skillImage;
    
    public SkillSetEntity() {
    }
    
    public SkillSetEntity(String skillName, String skillImage) {
        
        this.skillName = skillName;
        this.skillImage = skillImage;
        
    }
    
    public String getSkillName() {
        return skillName;
    }
    
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
    
    public String getSkillImage() {
        return skillImage;
    }
    
    public void setSkillImage(String skillImage) {
        this.skillImage = skillImage;
    }
}