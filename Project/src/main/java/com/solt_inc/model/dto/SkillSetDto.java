package com.solt_inc.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.solt_inc.model.entity.DevelopmentProcessEntity;
import com.solt_inc.model.entity.SkillSetEntity;
import com.solt_inc.model.entity.SkillSetImageEntity;

public class SkillSetDto implements Serializable {

    private SkillSetEntity skillSetEntity;
    private List<SkillSetImageEntity> skillSetImageEntityList = new ArrayList<SkillSetImageEntity>();
    private DevelopmentProcessEntity processStartEntity;
    private DevelopmentProcessEntity processEndEntity;

    public SkillSetEntity getSkillSetEntity() {
        return skillSetEntity;
    }

    public void setSkillSetEntity(SkillSetEntity skillSetEntity) {
        this.skillSetEntity = skillSetEntity;
    }

    public List<SkillSetImageEntity> getSkillSetImageEntityList() {
        return skillSetImageEntityList;
    }

    public void setSkillSetImageEntityList(List<SkillSetImageEntity> skillSetImageList) {
        this.skillSetImageEntityList = skillSetImageList;
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
