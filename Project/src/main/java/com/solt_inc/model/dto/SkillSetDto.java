package com.solt_inc.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.util.file.File;

import com.solt_inc.WicketApplication;
import com.solt_inc.component.file.ImageFile;
import com.solt_inc.component.folder.UploadFolder;
import com.solt_inc.model.entity.DevelopmentProcessEntity;
import com.solt_inc.model.entity.SkillSetEntity;
import com.solt_inc.model.entity.SkillSetImageEntity;

public class SkillSetDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private final UploadFolder UPLOAD_FOLDER_PATH = new UploadFolder(
			((WicketApplication)Application.get()).getUploadFolder(),
			"user" + File.separator + "skillset" + File.separator +"image" + File.separator);


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
    
    public List<ImageFile> getImageFileList() {
    	List<ImageFile> imageFileList = new ArrayList<ImageFile>();
    	
    	for(SkillSetImageEntity skillsetImageEntity: skillSetImageEntityList) {
    		imageFileList.add(new ImageFile(UPLOAD_FOLDER_PATH, skillsetImageEntity.getImageName()));
    	}
    	return imageFileList;
    }
    public void setImageFileList(List<ImageFile> imageFileList) {
    	
    	if(skillSetImageEntityList.size() != 0) {
    		skillSetImageEntityList.clear();
    	}
    	for(ImageFile imageFile: imageFileList) {
    		SkillSetImageEntity skillsetImageEntity = new SkillSetImageEntity();
    		skillsetImageEntity.setImageFile(imageFile);
    		this.skillSetImageEntityList.add(skillsetImageEntity);
    	}
    	
    }

}
