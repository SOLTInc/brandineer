package com.solt_inc.rtest.page.testPage;

import org.apache.wicket.Application;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.file.File;

import com.solt_inc.WicketApplication;
import com.solt_inc.component.file.ImageFile;
import com.solt_inc.component.folder.UploadFolder;
import com.solt_inc.model.dao.UserDao;
import com.solt_inc.page.homePage.HomePage;
import com.solt_inc.rtest.panel.TestFileUploadPanel;

public class TestPage extends WebPage {
    private static final long serialVersionUID = 7514382722447820L;

    private UploadFolder folder = new UploadFolder(((WicketApplication) Application.get()).getUploadFolder(),
            "test" + File.separator);
    private IModel<ImageFile> fileModel = new Model<ImageFile>(new ImageFile("/img/icon/no_image.png"));
    private IModel<UploadFolder> folderModel = new Model<UploadFolder>(folder);
    private IModel<String> fileNameModel = new Model();
    private IModel<String> testModel = new Model<String>("test");

    private FeedbackPanel feedback = new FeedbackPanel("feedback");
    private Form<?> form = new Form("form");
    private Label testLabel = new Label("testLabel", testModel);
    private WebMarkupContainer image = new WebMarkupContainer("image") {
        @Override
        public void onEvent(IEvent<?> event) {
            Object payload = event.getPayload();
            if (payload instanceof IModel) {
                @SuppressWarnings("unchecked")
                IModel<String> fileName = (Model<String>) payload;
                // target.appendJavaScript("alert('onEvent!!');");

                IModel<String> file = new Model<String>("/img/test/" + fileName.getObject());

                this.add(new AttributeModifier("src", file));
            } else if (payload instanceof AjaxRequestTarget) {
                // AjaxRequestTarget target = (AjaxRequestTarget) payload;
                // target.appendJavaScript("alert('send !!')");
                // fileModel = fileuploadPanel.getFile();
                // testModel.setObject(fileModel.getObject().getImagePath());
                // fileNameModel.setObject(fileModel.getObject().getImagePath());
                //
                // target.add(image);
                // target.add(testLabel);

            } else if (payload instanceof ImageFile) {
                ImageFile imageFile = (ImageFile) payload;
                fileNameModel.setObject(imageFile.getName());
                this.add(new AttributeModifier("src", "/img/test/" + fileNameModel.getObject()));
            }

        }

    };
    private TestFileUploadPanel fileuploadPanel = new TestFileUploadPanel("fileuploadPanel", fileModel, folderModel,
            image);

    private Button submitButton = new Button("submit") {
        private static final long serialVersionUID = 1L;

        @Override
        public void onSubmit() {
            UserDao userDao = new UserDao();

        }
    };

    private Button cancelButton = new Button("cancel") {
        private static final long serialVersionUID = 1L;

        @Override
        public void onSubmit() {
            setResponsePage(HomePage.class);

        }
    };

    public TestPage(PageParameters params) {

        queue(feedback);
        queue(form);
        testLabel.setOutputMarkupId(true);
        queue(testLabel);
        // this.image = settingImage("image");
        this.fileNameModel = new Model<String>("/img/icon/no_image.png");
        image.setOutputMarkupId(true);
        queue(image);
        queue(fileuploadPanel);
        queue(submitButton);
        queue(cancelButton);
    }

    // private WebMarkupContainer settingImage(String id) {
    //
    // IModel<String> filePath = new Model<String>();
    // this.image = new WebMarkupContainer(id) {
    // @Override
    // public void onEvent(IEvent<?> event) {
    //
    // Object payload = event.getPayload();
    // if (payload instanceof IModel) {
    // @SuppressWarnings("unchecked")
    // IModel<String> fileName = (Model<String>) payload;
    // // target.appendJavaScript("alert('onEvent!!');");
    //
    // IModel<String> file = new Model<String>("/img/test/" +
    // fileName.getObject());
    //
    // this.add(new AttributeModifier("src", file));
    // }
    // if (payload instanceof AjaxRequestTarget) {
    // AjaxRequestTarget target = (AjaxRequestTarget) payload;
    // target.appendJavaScript("alert('send !!')");
    // fileModel = fileuploadPanel.getFile();
    // this.add(new AttributeModifier("src",
    // fileModel.getObject().getImagePath()));
    // target.add(image);
    // target.add(testLabel);
    // }
    //
    // if (payload instanceof ImageFile) {
    // ImageFile imageFile = (ImageFile) payload;
    // this.add(new AttributeModifier("src", imageFile.getImagePath()));
    // this.onModelChanged();
    // }
    //
    // }
    // };

    // fileModel = new Model<ImageFile>();
    //
    // fileModel.setObject(new ImageFile("/img/icon/no_image.png"));
    // attri = new AttributeModifier("src",
    // fileModel.getObject().getImagePath());
    // image.add(attri);
    //
    // return image;
    // }
}