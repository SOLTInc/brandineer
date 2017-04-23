package com.solt_inc.rtest.page.testSelectOption;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.extensions.markup.html.form.select.SelectOption;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.Response;

import com.solt_inc.model.entity.HobbyImageEntity;
import com.solt_inc.model.entity.UserEntity;

public class testSelectOption extends WebPage{
	
	private IModel dataModel = Model.of("start");

	public testSelectOption() {
		
//		this.dataModel = dataModel;

		Form selectform = new Form("selectform");
		
		Label selectdata = new Label("selectdata", this.dataModel);
		
		Select select = new Select("select", this.dataModel);
		
		SelectOption option1 = new SelectOption("option1", Model.of("test1"));
		SelectOption option2 = new SelectOption("option2", Model.of("test2"));
		
		Button submitButton = new Button("selectsubmit") {
			@Override
			public void onSubmit() {
			}
		};
		
		add(selectform);
		selectform.add(selectdata);
		selectform.add(select);
		select.add(option1);
		select.add(option2);
		selectform.add(submitButton);
		
		
		

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
            }

        };

        AjaxButton cancelButton = new AjaxButton("cancelButton") {
            @Override
            public void onSubmit(AjaxRequestTarget target) {
            }

        };
        add(form);
        iconSelect.add(iconListView);
        form.add(iconSelect);
        form.add(setButton);
        form.add(cancelButton);
		
	}

}
