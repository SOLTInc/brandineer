package com.solt_inc.rtest.page.testModalView;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.solt_inc.rtest.page.testModalView.modalView.ModalView;


public class TestModalWindowPage extends WebPage {

    private ModalWindow modalWindow = new ModalWindow("modalWindow");
    private IModel<Integer> model = Model.of(1);

    private AjaxLink<?> ajaxLink = new AjaxLink<Void>("openWindow") {
        @Override
        public void onClick(AjaxRequestTarget target) {
            modalWindow.show(target);
        }

    };

    public TestModalWindowPage(PageParameters params) {

        super(params);
        Label label = new Label(modalWindow.getContentId(), "name");
        TextField text = new TextField(modalWindow.getContentId(), Model.of());
        //		Image image = new Image(modalWindow.getContentId(), "/img/apple.jpg");

        modalWindow.setContent(label);
        modalWindow.setContent(text);
        modalWindow.setPageCreator(new ModalWindow.PageCreator() {
            @Override
            public Page createPage() {
                return new ModalView(model, modalWindow);
            }

        });

        add(modalWindow);
        add(ajaxLink);
    }

}
