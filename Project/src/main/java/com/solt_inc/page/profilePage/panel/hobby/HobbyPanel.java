package com.solt_inc.page.profilePage.panel.hobby;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.solt_inc.model.entity.HobbyEntity;
import com.solt_inc.model.entity.UserEntity;

public class HobbyPanel extends Panel {
    
    public HobbyPanel(String id, IModel<UserEntity> regModel) {
        
        super(id, regModel);
        
        ListView<HobbyEntity> hobbyList = new ListView<HobbyEntity>("hobby", regModel.getObject().getHobbyList()) {
            @Override
            protected void populateItem(ListItem<HobbyEntity> item) {
                HobbyEntity hobby = (HobbyEntity) item.getModelObject();
                item.add(new Label("hobby_image", hobby.getHobbyImage()));
                item.add(new Label("hobby_name", hobby.getHobbyName()));
            }
        };
        add(hobbyList);
    }
}