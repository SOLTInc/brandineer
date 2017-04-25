package com.solt_inc.page.profilePage.panel.hobby;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.file.File;

import com.solt_inc.WicketApplication;
import com.solt_inc.component.folder.UploadFolder;
import com.solt_inc.model.dao.HobbyDao;
import com.solt_inc.model.dao.HobbyImageDao;
import com.solt_inc.model.dto.HobbyDto;
import com.solt_inc.model.entity.HobbyEntity;
import com.solt_inc.model.entity.HobbyImageEntity;

public class HobbyPanel extends Panel {

    private final UploadFolder HOBBY_ICON_FOLDER = new UploadFolder(
            ((WicketApplication) Application.get()).getUploadFolder(),
            "icon" + File.separator + "hobby" + File.separator);

    private IModel<Integer> userId;

    private List<HobbyDto> hobbyDtoList = new ArrayList<HobbyDto>();

    private ListView<HobbyDto> hobbyListView = new ListView<HobbyDto>("hobby", hobbyDtoList) {
        @Override
        protected void populateItem(ListItem<HobbyDto> item) {
            HobbyDto hobbyDto = (HobbyDto) item.getModelObject();
            HobbyEntity hobbyEntity = hobbyDto.getHobbyEntity();

            WebMarkupContainer hobbyIconContainer = new WebMarkupContainer("hobbyIcon");
            hobbyIconContainer
                    .add(new AttributeModifier("class", "fa " + hobbyEntity.getHobbyIcon()+" fa-stack-1x text-primary"));
            item.add(hobbyIconContainer);

            item.add(new Label("hobbyName", hobbyEntity.getHobbyName()));
        }
    };

    public HobbyPanel(String id, IModel<Integer> userId) {
        super(id, userId);

        this.userId = userId;
        setData();
        hobbyListView.setDefaultModelObject(hobbyDtoList);
        queue(hobbyListView);
    }

    private void setData() {

        HobbyDao hobbyDao = new HobbyDao();
        HobbyImageDao hobbyImageDao = new HobbyImageDao();

        List<HobbyEntity> userHobbyList = hobbyDao.getUserHobby(userId.getObject());
        for (HobbyEntity entity : userHobbyList) {

            HobbyDto hobbyDto = new HobbyDto();
            hobbyDto.setHobbyEntity(entity);
            List<HobbyImageEntity> hobbyImageList = hobbyImageDao.getHobbyImage(entity.getId());
            hobbyDto.setHobbyImageEntityList(hobbyImageList);

            hobbyDtoList.add(hobbyDto);
        }

    }
}