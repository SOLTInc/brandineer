package com.solt_inc.page.profilePage.panel.hobby;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.solt_inc.model.dao.HobbyDao;
import com.solt_inc.model.entity.HobbyEntity;

public class HobbyPanel extends Panel {
    
    private List<HobbyEntity> hobbyList;
    
    public HobbyPanel(String id, IModel<Integer> userId) {
        
        super(id, userId);
        HobbyDao hobbyDao = new HobbyDao();
        this.hobbyList = hobbyDao.getUserHobby(userId);
        
        ListView<HobbyEntity> hobbyList = new ListView<HobbyEntity>("hobby", this.hobbyList) {
            @Override
            protected void populateItem(ListItem<HobbyEntity> item) {
                HobbyEntity hobby = (HobbyEntity) item.getModelObject();
                WebMarkupContainer hobbyImage = new WebMarkupContainer("hobbyImage");
                hobbyImage.add(new AttributeModifier("src", getString("user.hobby.img.path") + hobby.getHobbyImage()));
                item.add(hobbyImage);
                item.add(new Label("hobbyName", hobby.getHobbyName()));
            }
        };
        add(hobbyList);
    }
}