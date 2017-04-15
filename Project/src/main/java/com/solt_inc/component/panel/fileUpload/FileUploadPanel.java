package com.solt_inc.component.panel.fileUpload;

import java.io.IOException;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.lang.Bytes;

import com.solt_inc.component.file.ImageFile;

public class FileUploadPanel extends Panel {

    private IModel<ImageFile> fileModel;
    private IModel<Folder> folderModel;
    private IModel<List<FileUpload>> uploadFileListModel = new ListModel<FileUpload>();
    private WebMarkupContainer targetComponent;

    private Form<?> form = new Form<Void>("form");
    private FileUploadField fileUpload = new FileUploadField("fileUpload", uploadFileListModel);
    private AjaxButton uploadButton = new AjaxButton("uploadButton") {
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
                } catch (IOException e) {
                    e.printStackTrace();

                    // throw new IllegalStateException("Error");

                } catch (Exception e) {
                    e.printStackTrace();
                    // throw new IllegalStateException("Error");
                }

                fileModel.setObject(newFile);

                info("saved file: "
                        // + UPLOAD_FOLDER
                        + newFile.getImagePath());
                send(targetComponent, Broadcast.BREADTH, newFile);
                target.add(targetComponent);

            }
        }
    };

    public FileUploadPanel(String id, IModel<ImageFile> fileModel, IModel<Folder> folderModel,
            WebMarkupContainer targetComponent) {

        super(id);
        this.fileModel = fileModel;
        this.folderModel = folderModel;
        this.targetComponent = targetComponent;

        form.setMultiPart(true);
        form.setMaxSize(Bytes.megabytes(1));
        add(form);

        form.add(fileUpload);
        form.add(uploadButton);

    }
}