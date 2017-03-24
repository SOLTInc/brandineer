package com.solt_inc.model.dto;

import java.io.Serializable;

import com.solt_inc.model.entity.DevelopmentProcessEntity;
import com.solt_inc.model.entity.SkillSetEntity;

public class SkillSetDto implements Serializable {

    private SkillSetEntity SkillSetEntity;
    private DevelopmentProcessEntity processStartEntity;
    private DevelopmentProcessEntity processEndEntity;

    public SkillSetEntity getSkillSetEntity() {
        return SkillSetEntity;
    }

    public void setSkillSetEntity(SkillSetEntity skillSetEntity) {
        SkillSetEntity = skillSetEntity;
    }

    public DevelopmentProcessEntity getProcessStartEntity() {
        return this.processStartEntity;
    }

    public void setProcessStartEntity(DevelopmentProcessEntity processStartEntity) {
        this.processStartEntity = processStartEntity;
    }

    public DevelopmentProcessEntity getProcessEndEntity() {
        return this.processEndEntity;
    }

    public void setProcessEndEntity(DevelopmentProcessEntity processEndEntity) {
        this.processEndEntity = processEndEntity;
    }

}
