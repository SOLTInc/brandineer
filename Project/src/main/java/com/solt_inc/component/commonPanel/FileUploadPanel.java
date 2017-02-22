package com.solt_inc.component.commonPanel;


import java.io.File;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.lang.Bytes;

public class FileUploadPanel extends Panel {

    private Form<?> form;
    private MarkupContainer image;
    private FileUploadField fileUpload;
    private Button uploadButton;

    private Model<String> fileName;
    private String filePath;
    private IModel<String> path;
    //    private String UPLOAD_FOLDER = "C:\\test\\";

    public FileUploadPanel(String id, IModel<String> path) {

        super(id);
        this.path = path;

        settings();

        form = new Form<Void>("form");
        form.setMultiPart(true);
        form.setMaxSize(Bytes.megabytes(1));

        form.add(image);
        form.add(fileUpload);
        form.add(uploadButton);


        add(form);

    }
    private void settings() {

        image = new WebMarkupContainer("image");
        if(fileName == null) {
            filePath = getString("user.profile.photo.no_image");
        } else {
            filePath = path.getObject() + fileName.getObject();
        }
        image.add(new AttributeModifier("src", filePath));

        fileUpload = new FileUploadField("fileUpload", new ListModel());

        uploadButton = new Button("button") {
            @Override
            public void onSubmit() {

                
                final FileUpload uploadedFile = fileUpload.getFileUpload();
                if (uploadedFile != null) {

                    // write to a new file
                    File newFile = new File(
                            // UPLOAD_FOLDER ,
                            getDirectory(path.getObject()),
                            uploadedFile.getClientFileName());

                    if (newFile.exists()) {
                        newFile.delete();
                    }

                    try {
                        newFile.createNewFile();
                        uploadedFile.writeTo(newFile);

                        image.add(new AttributeModifier(
                                "src", 
                                path.getObject() + uploadedFile.getClientFileName()
                                //  UPLOAD_FOLDER + uploadedFile.getClientFileName()
                                ));

                        info("saved file: " 
                                //    + UPLOAD_FOLDER
                                + path.getObject()
                                + uploadedFile.getClientFileName());
                    } catch (Exception e) {
                        throw new IllegalStateException("Error");
                    }
                }
            }
        };

    }
    private File getDirectory(String dirName) {
        WebApplication application = (WebApplication)getApplication();
        String uploadPath = application.getServletContext().getRealPath(dirName);
        File dir = new File(uploadPath);

        if(!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }
}