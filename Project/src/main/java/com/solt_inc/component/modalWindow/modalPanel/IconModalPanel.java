package com.solt_inc.component.modalWindow.modalPanel;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.solt_inc.component.modalWindow.iconModalWindow.IconModalView;

public class IconModalPanel extends Panel {

    private IModel<String> iconNameModel;

    public IconModalPanel(String id, IModel<String> iconNameModel) {
        super(id);

        this.iconNameModel = iconNameModel;

        WebMarkupContainer wmc = new WebMarkupContainer("icon");
        wmc.setOutputMarkupId(true);

        if (this.iconNameModel.getObject() != null) {
            wmc.add(new AttributeModifier("src", "/img/icon/hobby/" + this.iconNameModel.getObject()));
        }

        ModalWindow modalWindow = new ModalWindow("modalWindow");
        Label label = new Label(modalWindow.getContentId(), "name");

        modalWindow.setContent(label);
        modalWindow.setContent(new IconModalView(modalWindow.getContentId(), this.iconNameModel, modalWindow));

        modalWindow.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {

            @Override
            public boolean onCloseButtonClicked(AjaxRequestTarget target) {

                return true;
            }

        });
        modalWindow.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {

            @Override
            public void onClose(AjaxRequestTarget target) {

                if (iconNameModel.getObject() != null) {
                    wmc.add(new AttributeModifier("src", "/img/icon/hobby/" + iconNameModel.getObject()));
                }
                target.add(wmc);
            }
        });
        AjaxLink<?> ajaxLink = new AjaxLink<Void>("openWindow") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                modalWindow.show(target);
            }
        };

        queue(wmc);
        add(modalWindow);
        add(ajaxLink);
    }

}
