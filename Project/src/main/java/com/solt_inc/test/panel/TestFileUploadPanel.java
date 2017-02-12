package com.solt_inc.test.panel;


import java.io.File;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Bytes;

public class TestFileUploadPanel extends Panel {

    private FileUploadField fileUpload;
    private String UPLOAD_FOLDER = "C:\\test\\";
    private String dir = "no date";

    public TestFileUploadPanel(String id) {

        super(id);
 //       add(new FeedbackPanel("feedback"));

        Form<?> form = new Form<Void>("form");
        
        Button button = createButton();
        form.add(button);
//        form.add(new Button("button") {
//         @Override
//         public void onSubmit() {
//
//            final FileUpload uploadedFile = fileUpload.getFileUpload();
//            if (uploadedFile != null) {
//
//                // write to a new file
//                File newFile = new File(UPLOAD_FOLDER
//                    , uploadedFile.getClientFileName());
//                
//                dir = UPLOAD_FOLDER + uploadedFile.getClientFileName();
//                
//
//                if (newFile.exists()) {
//                    newFile.delete();
//                }
//
//                try {
//                    newFile.createNewFile();
//                    uploadedFile.writeTo(newFile);
//                    
//
//                    info("saved file: " + UPLOAD_FOLDER + uploadedFile.getClientFileName());
//                } catch (Exception e) {
//                    throw new IllegalStateException("Error");
//                }
//             }
//
//            }
//
//        });

        form.add(new Label("dir", new Model(dir)));
        // Enable multipart mode (need for uploads file)
        form.setMultiPart(true);

        // max upload size, 10k
        form.setMaxSize(Bytes.megabytes(1));

        form.add(fileUpload = new FileUploadField("fileUpload"));

        add(form);

    }
    private Button createButton() {
        Button button = new Button("button") {
            @Override
            public void onSubmit() {

               final FileUpload uploadedFile = fileUpload.getFileUpload();
               if (uploadedFile != null) {

                   // write to a new file
                   File newFile = new File(UPLOAD_FOLDER
                       , uploadedFile.getClientFileName());
                   
                   dir = UPLOAD_FOLDER + uploadedFile.getClientFileName();
                   

                   if (newFile.exists()) {
                       newFile.delete();
                   }

                   try {
                       newFile.createNewFile();
                       uploadedFile.writeTo(newFile);
                       

                       info("saved file: " + UPLOAD_FOLDER + uploadedFile.getClientFileName());
                   } catch (Exception e) {
                       throw new IllegalStateException("Error");
                   }
                }

               }

           };
           
           return button;

    }
}