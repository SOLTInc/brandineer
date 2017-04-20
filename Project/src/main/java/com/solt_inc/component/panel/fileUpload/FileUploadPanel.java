package com.solt_inc.component.panel.fileUpload;

import java.io.IOException;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnEventHeaderItem;
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

    private static final long serialVersionUID = 1L;
    private Folder uploadFolder;
    private IModel<List<FileUpload>> uploadFileListModel = new ListModel<FileUpload>();
    private WebMarkupContainer targetComponent;

    private Form<?> form = new Form<Void>("form");
    private FileUploadField fileUpload = new FileUploadField("fileUpload", uploadFileListModel) {
        @Override
        public void renderHead(IHeaderResponse response) {
            super.renderHead(response);
            String js = "$('#" + sendFileButton.getMarkupId() + "').click()";
            response.render(OnEventHeaderItem.forScript(this.getMarkupId(), "change", js));
        }

    };
    private AjaxButton sendFileButton = new AjaxButton("sendFileButton") {
        private static final long serialVersionUID = 1L;

        @Override
        public void onSubmit(AjaxRequestTarget target) {
            final FileUpload uploadedFile = fileUpload.getFileUpload();
            if (uploadedFile != null) {
                uploadFolder.mkdirs();
                ImageFile newFile = new ImageFile(uploadFolder,
                        uploadedFile.getMD5() + uploadedFile.getClientFileName());

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

                info("saved file: "
                        // + UPLOAD_FOLDER
                        + newFile.getImagePath());
                send(targetComponent, Broadcast.BREADTH, newFile);
                target.add(targetComponent);

            }
        }
    };
    private AjaxButton uploadButton = new AjaxButton("uploadButton") {

        @Override
        public void onSubmit(AjaxRequestTarget arg0) {
        }

        @Override
        public void renderHead(IHeaderResponse response) {
            super.renderHead(response);
            String js = "$('#" + fileUpload.getMarkupId() + "').click()";
            response.render(OnEventHeaderItem.forScript(this.getMarkupId(), "click", js));
        }
    };

    public FileUploadPanel(String id, Folder uploadFolder,
            WebMarkupContainer targetComponent) {

        super(id);
        this.uploadFolder = uploadFolder;
        this.targetComponent = targetComponent;

        AttributeModifier displayNone = new AttributeModifier("style", "display:none");

        form.setMultiPart(true);
        form.setMaxSize(Bytes.megabytes(5));
        queue(form);

//        fileUpload.add(displayNone);
        form.queue(fileUpload);

        sendFileButton.add(displayNone);
        form.queue(sendFileButton);

        uploadButton.setVisible(false);
        form.queue(uploadButton);
    }
}