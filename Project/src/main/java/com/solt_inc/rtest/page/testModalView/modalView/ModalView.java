package com.solt_inc.rtest.page.testModalView.modalView;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.extensions.markup.html.form.select.SelectOption;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.Response;

import com.solt_inc.model.entity.HobbyImageEntity;

public class ModalView extends WebPage {

    ModalWindow modalWindow;
    IModel<String> dataModel;

    public ModalView(IModel<Integer> model, ModalWindow modalWindow) {

        //        this.dataModel = model;
        this.modalWindow = modalWindow;

        this.dataModel = Model.of("first");

        List<HobbyImageEntity> iconEntityList = new ArrayList<HobbyImageEntity>();
        HobbyImageEntity data = new HobbyImageEntity();
        data.setId(1);
        data.setImageName("java.jpg");
        iconEntityList.add(data);

        HobbyImageEntity data2 = new HobbyImageEntity();
        data2.setId(2);
        data2.setImageName("kiwi.jpg");
        iconEntityList.add(data2);

        Form<?> form = new Form<Void>("form");

        Select iconSelect = new Select("selectIcon", dataModel);
        ListView<HobbyImageEntity> iconListView = new ListView<HobbyImageEntity>("iconListView", iconEntityList) {
            @Override
            protected void populateItem(ListItem<HobbyImageEntity> item) {
                HobbyImageEntity iconEntity = (HobbyImageEntity) item.getDefaultModelObject();
                SelectOption<String> optionIcon = new SelectOption<String>("optionIcon",
                        Model.of(iconEntity.getImageName()));
                WebMarkupContainer wmc = new WebMarkupContainer("iconName");
                Behavior addIconName = new Behavior() {
                    @Override
                    public void beforeRender(Component component) {
                        Response response = component.getResponse();
                        response.write(iconEntity.getImageName());
                    }
                };
                wmc.add(addIconName);
                optionIcon.add(new AttributeModifier("data-img-src", "/img/test/" + iconEntity.getImageName()));
                optionIcon.add(new AttributeModifier("value", iconEntity.getId()));
                optionIcon.add(wmc);

                item.add(optionIcon);
            }
        };
        AjaxButton setButton = new AjaxButton("setButton") {
            @Override
            public void onSubmit(AjaxRequestTarget target) {
                modalWindow.close(target);
            }

        };

        AjaxButton cancelButton = new AjaxButton("cancelButton") {
            @Override
            public void onSubmit(AjaxRequestTarget target) {
                modalWindow.close(target);
            }

        };
        queue(form);
        iconSelect.queue(iconListView);
        form.queue(iconSelect);
        form.queue(setButton);
        form.queue(cancelButton);

    }
}
