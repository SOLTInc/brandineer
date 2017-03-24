package com.solt_inc.test.page;

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

import com.solt_inc.model.dao.UserDao;
import com.solt_inc.page.homePage.HomePage;
import com.solt_inc.test.panel.TestFileUploadPanel;

public class TestPage extends WebPage {
    private static final long serialVersionUID = 7514382722447820L;

    private IModel<String> fileName;
    private IModel<String> filePath;

    private TestFileUploadPanel fileuploadPanel;
    private AttributeModifier attri;
    private IModel<String> testModel;
    private Label testLabel;
    private WebMarkupContainer image;

    public TestPage(PageParameters params) {

        add(new FeedbackPanel("feedback"));

        Form<?> form = new Form("form");
        add(form);

        testModel = new Model<String>("test");
        testLabel = new Label("testLabel", testModel);
        testLabel.setOutputMarkupId(true);
        form.add(testLabel);

        this.image = settingImage("image");
        image.setOutputMarkupId(true);
        form.add(image);

        filePath = new Model<String>("/img/test/");
        fileuploadPanel = new TestFileUploadPanel("fileuploadPanel", fileName, filePath, image);
        form.add(fileuploadPanel);

        Button submitButton = new Button("submit") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                UserDao userDao = new UserDao();

            }
        };
        form.add(submitButton);

        Button cancelButton = new Button("cancel") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                setResponsePage(HomePage.class);

            }
        };
        form.add(cancelButton);

    }

    private WebMarkupContainer settingImage(String id) {

        IModel<String> filePath = new Model<String>();
        this.image = new WebMarkupContainer(id) {
            @Override
            public void onEvent(IEvent<?> event) {

                Object payload = event.getPayload();
                if (payload instanceof IModel) {
                    @SuppressWarnings("unchecked")
                    IModel<String> fileName = (Model<String>) payload;
                    // target.appendJavaScript("alert('onEvent!!');");
                    IModel<String> file = new Model<String>("/img/test/" + fileName.getObject());

                    this.add(new AttributeModifier("src", file));
                }
                if (payload instanceof AjaxRequestTarget) {
                    AjaxRequestTarget target = (AjaxRequestTarget) payload;
                    String fileName = fileuploadPanel.getFileName();
                    testModel.setObject(fileName);
                    IModel<String> file = new Model<String>("/img/test/" + fileName);
                    this.add(new AttributeModifier("src", file));
                    target.add(image);
                    target.add(testLabel);
                }

            }
        };

        if (fileName == null) {
            /// filePath.setObject(getString("noimage"));
            filePath.setObject("/img/icon/no_image.png");
        } else {
            // filePath.setObject(getString("path") + fileName.getObject());
            filePath.setObject("/img/test/" + fileName.getObject());
        }
        attri = new AttributeModifier("src", filePath);
        image.add(attri);

        return image;
    }
}