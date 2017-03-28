package com.solt_inc.component.folder;

import java.io.File;

import org.apache.wicket.Application;
import org.apache.wicket.util.file.Folder;

import com.solt_inc.WicketApplication;

public class UploadFolder extends Folder {

    public UploadFolder(Folder parent, String child) {
        super(parent, child);
    }

    public UploadFolder(String parent, String child) {
        super(parent, child);

    }

    public String getUploadPath() {

        String uploadPath = super.getPath();
        String contextPath = ((WicketApplication) Application.get()).getContextPath();

        return uploadPath.replace(contextPath, "") + File.separator;
    }

}
