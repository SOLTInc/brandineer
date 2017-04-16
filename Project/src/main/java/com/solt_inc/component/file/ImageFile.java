package com.solt_inc.component.file;

import org.apache.wicket.Application;
import org.apache.wicket.util.file.File;

import com.solt_inc.WicketApplication;

public class ImageFile extends File {

	private static final long serialVersionUID = 1L;

	public ImageFile(File parent, String child) {
        super(parent, child);
    }

    public ImageFile(String string) {
        super(string);
    }

    public String getImagePath() {

        String filePath = super.getPath();
        String contextPath = ((WicketApplication) Application.get()).getContextPath();

        return filePath.replace(contextPath, "");
    }

}
