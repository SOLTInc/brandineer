package com.solt_inc.test.panel;

import java.io.File;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.lang.Bytes;

public class TestFileUploadPanel extends Panel {

    private IModel<String> fileName;
    private String filePath;
    private IModel<String> path;
    private MarkupContainer image;
    private FileUploadField fileUpload;
    // private String UPLOAD_FOLDER = "C:\\test\\";

    public TestFileUploadPanel(String id, IModel<String> fileName, IModel<String> path,
            WebMarkupContainer imageComponent) {

        super(id);
        this.fileName = fileName;
        this.path = path;

        Label fileNameLabel = new Label("fileName", fileName);
        add(fileNameLabel);

        Label filePathLabel = new Label("filePath", path);
        add(filePathLabel);

        Form<?> form = new Form<Void>("form");
        form.setMultiPart(true);
        form.setMaxSize(Bytes.megabytes(1));
        add(form);

        this.image = ImageSetting("image");
        this.image.setOutputMarkupId(true);
        form.add(image);

        this.fileUpload = new FileUploadField("fileUpload", new ListModel<FileUpload>());
        // fileUpload.setOutputMarkupId(true);
        fileUpload.add(new AjaxFormComponentUpdatingBehavior("change") {

            IModel<String> fileName;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                uploadFile();
                target.appendJavaScript("alert('send!!');");
                // send(imageComponent, Broadcast.EXACT, target);
                // send(imageComponent, Broadcast.EXACT, this.fileName);
                // target.add(fileUpload);
            }

            private void uploadFile() {

                if (this.fileName == null) {
                    this.fileName = new Model<String>();
                }

                FileUpload uploadedFile = fileUpload.getFileUpload();
                if (uploadedFile != null) {

                    this.fileName.setObject(uploadedFile.getClientFileName());
                    // write to a new file
                    File newFile = new File(
                            // UPLOAD_FOLDER ,
                            getDirectory(path.getObject()), uploadedFile.getClientFileName());

                    if (newFile.exists()) {
                        newFile.delete();
                    }

                    try {
                        newFile.createNewFile();
                        uploadedFile.writeTo(newFile);

                        // image.add(new AttributeModifier("src",
                        // path.getObject() + uploadedFile.getClientFileName()
                        // UPLOAD_FOLDER + uploadedFile.getClientFileName()
                        // ));

                        info("saved file: "
                                // + UPLOAD_FOLDER
                                + path.getObject() + uploadedFile.getClientFileName());
                    } catch (Exception e) {
                        throw new IllegalStateException("Error");
                    }
                }
            }

        });
        form.add(fileUpload);

        AjaxLink selectFileButton = createSelectFileButton("selectFileButton");
        form.add(selectFileButton);

        AjaxButton uploadButton = createUploadButton("button");
        form.add(uploadButton);

    }

    private AjaxLink createSelectFileButton(String id) {
        AjaxLink ajaxLink = new AjaxLink<Void>(id) {

            @Override
            public void onClick(AjaxRequestTarget target) {
                // target.appendJavaScript("$(\"#" + fileUpload.getMarkupId() +
                // "\").click();");
                // target.add(fileUpload);

            }

        };
        return ajaxLink;

    }

    private MarkupContainer ImageSetting(String id) {

        MarkupContainer image;
        image = new WebMarkupContainer(id);
        if (fileName == null) {
            // filePath = getString("user.profile.photo.no_image");
            filePath = "/img/icon/no_image.png";
        } else {
            filePath = path.getObject() + fileName;
        }
        image.add(new AttributeModifier("src", filePath));
        return image;
    }

    private AjaxButton createUploadButton(String id) {
        AjaxButton uploadButton = new AjaxButton(id) {
            @Override
            public void onSubmit(AjaxRequestTarget target) {

                final FileUpload uploadedFile = fileUpload.getFileUpload();
                if (uploadedFile != null) {

                    // fileName = new
                    // Model<String>(uploadedFile.getClientFileName());
                    // write to a new file
                    File newFile = new File(
                            // UPLOAD_FOLDER ,
                            getDirectory(path.getObject()), uploadedFile.getClientFileName());

                    if (newFile.exists()) {
                        newFile.delete();
                    }

                    try {
                        newFile.createNewFile();
                        uploadedFile.writeTo(newFile);

                        image.add(new AttributeModifier("src", path.getObject() + uploadedFile.getClientFileName()
                        // UPLOAD_FOLDER + uploadedFile.getClientFileName()
                        ));

                        info("saved file: "
                                // + UPLOAD_FOLDER
                                + path.getObject() + uploadedFile.getClientFileName());
                    } catch (Exception e) {
                        throw new IllegalStateException("Error");
                    }
                }
                target.add(image);
            }
        };

        return uploadButton;

    }

    private File getDirectory(String dirName) {
        File dir = null;
        WebApplication application = (WebApplication) getApplication();
        String uploadPath = application.getServletContext().getRealPath(dirName);
        if (uploadPath != null) {
            dir = new File(uploadPath);

            if (!dir.exists()) {
                dir.mkdir();
            }
        }
        return dir;
    }

    public String getFileName() {
        final FileUpload uploadedFile = fileUpload.getFileUpload();
        if (uploadedFile != null) {

            fileName = new Model<String>(uploadedFile.getClientFileName());
            // write to a new file
            File newFile = new File(
                    // UPLOAD_FOLDER ,
                    getDirectory(path.getObject()), uploadedFile.getClientFileName());

            if (newFile.exists()) {
                newFile.delete();
            }

            try {
                newFile.createNewFile();
                uploadedFile.writeTo(newFile);

                // image.add(new AttributeModifier("src", path.getObject() +
                // uploadedFile.getClientFileName()
                // UPLOAD_FOLDER + uploadedFile.getClientFileName()
                // ));

                info("saved file: "
                        // + UPLOAD_FOLDER
                        + path.getObject() + uploadedFile.getClientFileName());
            } catch (Exception e) {
                throw new IllegalStateException("Error");
            }
        } else {
            fileName = new Model("noimage");
        }
        // TODO Auto-generated method stub
        return fileName.getObject();
    }
}