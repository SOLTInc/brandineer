package com.solt_inc.page.profilePage.panel.hobby;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.solt_inc.model.dao.HobbyDao;
import com.solt_inc.model.dao.HobbyImageDao;
import com.solt_inc.model.dto.HobbyDto;
import com.solt_inc.model.entity.HobbyEntity;
import com.solt_inc.model.entity.HobbyImageEntity;

public class HobbyPanel extends Panel {

    private IModel<Integer> userId;
    private ListView<HobbyDto> hobbyList;

    public HobbyPanel(String id, IModel<Integer> userId) {
        super(id, userId);

        this.userId = userId;
        settings();
        add(this.hobbyList);
    }

    private void settings() {

        List<HobbyDto> hobbyDtoList = new ArrayList<HobbyDto>();
        HobbyDao hobbyDao = new HobbyDao();
        HobbyImageDao hobbyImageDao = new HobbyImageDao();

        List<HobbyEntity> userHobbyList = hobbyDao.getUserHobby(userId);
        for (HobbyEntity entity : userHobbyList) {

            HobbyDto hobbyDto = new HobbyDto();
            hobbyDto.setHobbyEntity(entity);
            HobbyImageEntity hobbyImage = hobbyImageDao.getHobbyImage(entity.getId());
            hobbyDto.setHobbyImageEntity(hobbyImage);

            hobbyDtoList.add(hobbyDto);
        }

        this.hobbyList = new ListView<HobbyDto>("hobby", hobbyDtoList) {
            @Override
            protected void populateItem(ListItem<HobbyDto> item) {
                HobbyDto hobbyDto = (HobbyDto) item.getModelObject();
                WebMarkupContainer hobbyImage = new WebMarkupContainer("hobbyImage");
                hobbyImage.add(new AttributeModifier("src",
                        getString("user.hobby.img.path") + hobbyDto.getHobbyImageEntity().getImageName()));

                item.add(hobbyImage);
                item.add(new Label("hobbyName", hobbyDto.getHobbyEntity().getHobbyName()));
            }
        };

    }
}