package com.solt_inc.component.modalWindow.iconModalWindow;

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
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.Response;

import com.solt_inc.component.modalWindow.icon.HobbyIconDao;
import com.solt_inc.component.modalWindow.icon.HobbyIconEntity;

public class IconModalView extends Panel {

    ModalWindow modalWindow;
    IModel<String> iconNameModel;

    public IconModalView(String id, IModel<String> iconNameModel, ModalWindow modalWindow) {
        super(id);

        this.iconNameModel = iconNameModel;
        this.modalWindow = modalWindow;

        HobbyIconDao iconDao = new HobbyIconDao();
        List<HobbyIconEntity> iconEntityList = iconDao.getIconList();

        Form<?> form = new Form<Void>("form");

        Select<String> iconSelect = new Select<String>("selectIcon", iconNameModel);
        ListView<HobbyIconEntity> iconListView = new ListView<HobbyIconEntity>("iconListView", iconEntityList) {
            @Override
            protected void populateItem(ListItem<HobbyIconEntity> item) {
                HobbyIconEntity iconEntity = (HobbyIconEntity) item.getDefaultModelObject();
                SelectOption<String> optionIcon = new SelectOption<String>("optionIcon",
                        Model.of(iconEntity.getIconFileName()));
                WebMarkupContainer optionValueContainer = new WebMarkupContainer("iconName");
                Behavior addIconName = new Behavior() {
                    @Override
                    public void beforeRender(Component component) {
                        Response response = component.getResponse();
                        response.write(iconEntity.getIconName());
                    }
                };
                optionValueContainer.add(addIconName);
                optionIcon
                        .add(new AttributeModifier("data-img-src", "/img/icon/hobby/" + iconEntity.getIconFileName()));
                optionIcon.add(optionValueContainer);

                item.add(optionIcon);
            }
        };
        AjaxButton setButton = new AjaxButton("setButton") {
            @Override
            public void onSubmit(AjaxRequestTarget target) {

                iconListView.removeAll();
                modalWindow.close(target);
            }

        };

        queue(form);
        iconSelect.queue(iconListView);
        form.queue(iconSelect);
        form.queue(setButton);

    }
}
