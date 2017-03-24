package com.solt_inc.test.page.nextPage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.solt_inc.test.page.TestPage;

public class NextTestPage extends WebPage {
    private static final long serialVersionUID = 7514382722447820L;

    private IModel<String> fileName;

    public NextTestPage(IModel<String> fileName) {
        super(fileName);
        this.fileName = fileName;

        add(new FeedbackPanel("feedback"));

        Form<?> form = new Form("form");
        add(form);

        WebMarkupContainer image = createImage("image");
        form.add(image);

        Button backButton = new Button("back") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                setResponsePage(TestPage.class);

            }
        };
        form.add(backButton);

    }

    private WebMarkupContainer createImage(String id) {

        WebMarkupContainer image = new WebMarkupContainer(id);
        IModel<String> filePath = new Model<String>();
        ;
        if (fileName != null) {
            filePath.setObject(getString("noimage"));
        } else {
            filePath.setObject(getString("path") + fileName.getObject());
        }
        image.add(new AttributeModifier("src", filePath));

        return image;
    }
}