package com.solt_inc.model.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.wicket.Application;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Folder;

import com.solt_inc.WicketApplication;
import com.solt_inc.component.file.ImageFile;
import com.solt_inc.component.folder.UploadFolder;

public class SkillSetImageEntity implements Serializable {

	private static final long serialVersionUID = 1L;

//	private final UploadFolder UPLOAD_FOLDER_PATH = new UploadFolder(
//			((WicketApplication)Application.get()).getUploadFolder(),
//			"user" + File.separator + "skillset" + File.separator +"image" + File.separator);

	private int id;
    private int skillSetId;
    @NotNull
    private String imageName;
 //   private ImageFile imageFile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSkillSetId() {
        return skillSetId;
    }

    public void setSkillSetId(int SkillSetId) {
        this.skillSetId = SkillSetId;
    }
    
    public String getImageName() {
    	return this.imageName;
    }
    
    public void setImageName(String imageName) {
    	this.imageName = imageName;
    }

//    public ImageFile getImageFile() {
//        return imageFile;
//    }

//    public void setImageFile(ImageFile imageFile) {
//        this.imageFile = imageFile;
//    }

//    public void setImageFile(String imageName) {

//        this.imageFile = new ImageFile(UPLOAD_FOLDER_PATH, imageName);
//    }
    
//    public Folder getUploadFolderPath() {
//    	return this.UPLOAD_FOLDER_PATH;
//    }
    
}
