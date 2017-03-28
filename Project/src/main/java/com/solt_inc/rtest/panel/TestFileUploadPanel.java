package com.solt_inc.rtest.panel;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import com.solt_inc.component.file.ImageFile;
import com.solt_inc.component.folder.UploadFolder;
import com.solt_inc.component.form.FileUploadForm;

public class TestFileUploadPanel extends Panel {

    private IModel<ImageFile> fileModel;
    private IModel<String> fileNameModel;
    private IModel<UploadFolder> folderModel;
    private MarkupContainer image;
    private FileUploadField fileUpload;
    private IModel<List<FileUpload>> fileList;
    private Label filePathLabel;
    private WebMarkupContainer imageComponent;
    // private String UPLOAD_FOLDER = "C:\\test\\";

    public TestFileUploadPanel(String id, IModel<ImageFile> fileModel, IModel<UploadFolder> folderModel,
            WebMarkupContainer imageComponent) {

        super(id);
        this.fileModel = fileModel;
        this.folderModel = folderModel;
        this.imageComponent = imageComponent;
        this.fileNameModel = new Model<String>(this.fileModel.getObject().getName());
        Label fileNameLabel = new Label("fileName", this.fileNameModel);
        fileNameLabel.setOutputMarkupId(true);
        queue(fileNameLabel);

        // Label filePathLabel = new Label("filePath",
        // this.folderModel.getObject().getUploadPath());

        filePathLabel = new Label("filePath", this.fileModel.getObject().getImagePath());
        filePathLabel.setOutputMarkupId(true);
        queue(filePathLabel);

        FileUploadForm form = new FileUploadForm("form");
        queue(form);

        this.image = ImageSetting("image");
        this.image.setOutputMarkupId(true);
        // form.add(image);
        queue(image);

        this.fileList = new ListModel<FileUpload>();
        this.fileUpload = new FileUploadField("fileUpload", fileList);
        // fileUpload.setOutputMarkupId(true);
        this.fileUpload.add(new AjaxFormComponentUpdatingBehavior("blue") {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                // try {
                // Thread.sleep(2000);
                // } catch (InterruptedException e) {
                // e.printStackTrace();
                // }
                List<FileUpload> testlist = fileUpload.getConvertedInput();
                String test = "noimage";
                if (testlist != null) {
                    for (FileUpload data : testlist) {
                        if (data.getClientFileName() != null) {
                            test = data.getClientFileName();
                        }
                    }
                    fileModel.setObject(new ImageFile(folderModel.getObject() + test));
                    fileNameModel.setObject(test);
                    // uploadFile();
                    target.appendJavaScript("alert('send!!');");
                    send(imageComponent, Broadcast.EXACT, this.uploadFile());
                    // send(imageComponent, Broadcast.EXACT, target);
                    // send(imageComponent, Broadcast.EXACT, this.fileName);
                    // target.add(fileUpload);
                } else {
                    fileNameModel.setObject("file is filure....");

                }
                target.add(fileNameLabel);
            }

            private ImageFile uploadFile() {

                ImageFile newFile = null;

                FileUpload uploadedFile = fileUpload.getFileUpload();
                if (uploadedFile != null) {

                    // write to a new file
                    newFile = new ImageFile(folderModel.getObject(), uploadedFile.getClientFileName());

                    if (newFile.exists()) {
                        newFile.delete();
                    }

                    try {
                        newFile.createNewFile();
                        uploadedFile.writeTo(newFile);

                    } catch (Exception e) {
                        throw new IllegalStateException("Error");
                    }
                }
                return newFile;
            }

        });
        // form.add(fileUpload);
        queue(fileUpload);

        AjaxLink selectFileButton = createSelectFileButton("selectFileButton");
        // form.add(selectFileButton);
        queue(selectFileButton);

        AjaxButton uploadButton = createUploadButton("button");
        // form.add(uploadButton);
        queue(uploadButton);

    }

    private AjaxLink createSelectFileButton(String id) {
        AjaxLink ajaxLink = new AjaxLink<Void>(id) {

            @Override
            public void onClick(AjaxRequestTarget target) {
                // target.appendJavaScript("$(\"#" + fileUpload.getMarkupId() +
                // "\").click();");
                // target.add(fileUpload);
                send(imageComponent, Broadcast.BREADTH, target);

                send(imageComponent, Broadcast.BREADTH, fileModel.getObject());

            }

        };
        return ajaxLink;

    }

    private MarkupContainer ImageSetting(String id) {

        MarkupContainer image;
        image = new WebMarkupContainer(id);
        if (fileModel == null) {
            // filePath = getString("user.profile.photo.no_image");
            fileModel.setObject(new ImageFile("/img/icon/no_image.png"));
        }
        image.add(new AttributeModifier("src", fileModel.getObject().getImagePath()));
        return image;
    }

    private AjaxButton createUploadButton(String id) {
        AjaxButton uploadButton = new AjaxButton(id) {
            @Override
            public void onSubmit(AjaxRequestTarget target) {

                final FileUpload uploadedFile = fileUpload.getFileUpload();
                if (uploadedFile != null) {

                    folderModel.getObject().mkdirs();

                    ImageFile newFile = new ImageFile(folderModel.getObject(), uploadedFile.getClientFileName());

                    if (newFile.exists()) {
                        newFile.delete();
                    }

                    try {
                        newFile.createNewFile();
                        uploadedFile.writeTo(newFile);

                        fileModel.setObject(newFile);

                        image.add(new AttributeModifier("src", fileModel.getObject().getImagePath()));

                        info("saved file: "
                                // + UPLOAD_FOLDER
                                + newFile.getImagePath());
                        target.add(image);
                        target.add(filePathLabel);
                    } catch (Exception e) {
                        throw new IllegalStateException("Error");
                    }
                }
            }
        };

        return uploadButton;

    }

    public IModel<ImageFile> getFile() {
        return this.fileModel;
    }
}