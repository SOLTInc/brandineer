package com.solt_inc.rtest.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;

import com.solt_inc.rtest.page.sample.SamplePage;
import com.solt_inc.rtest.page.testModalView.TestModalWindowPage;
import com.solt_inc.rtest.page.testPage.TestPage;
import com.solt_inc.rtest.page.testSelectOption.testSelectOption;

public class TestTopPage extends WebPage {

    public TestTopPage() {

        Form<?> form = new Form<Void>("form");
        queue(form);

        Button testUploadPageButton = new Button("testPageButton") {
            @Override
            public void onSubmit() {

                setResponsePage(TestPage.class);
            }
        };
        queue(testUploadPageButton);

        Button samplePageButton = new Button("samplePageButton") {
            @Override
            public void onSubmit() {

                setResponsePage(SamplePage.class);
            }
        };
        queue(samplePageButton);

        Button selectOptionButton = new Button("selectOptionButton") {
            @Override
            public void onSubmit() {

                setResponsePage(testSelectOption.class);
            }
        };
        queue(selectOptionButton);

        Button modalButton = new Button("modalButton") {
            @Override
            public void onSubmit() {

                setResponsePage(TestModalWindowPage.class);
            }
        };
        queue(modalButton);

    }
}
