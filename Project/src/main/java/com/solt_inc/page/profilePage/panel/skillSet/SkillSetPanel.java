package com.solt_inc.page.profilePage.panel.skillSet;

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
import com.solt_inc.model.dao.SkillSetDao;
import com.solt_inc.model.dao.SkillsetImageDao;
import com.solt_inc.model.dto.SkillSetDto;
import com.solt_inc.model.entity.SkillSetEntity;
import com.solt_inc.model.entity.SkillSetImageEntity;

public class SkillSetPanel extends Panel {

    private final UploadFolder SKILLSET_ICON_FOLDER = new UploadFolder(
            ((WicketApplication) Application.get()).getUploadFolder(),
            "user" + File.separator + "skillset" + File.separator + "image" + File.separator);

    private List<SkillSetDto> skillSetDtoList = new ArrayList<SkillSetDto>();

    private ListView<SkillSetDto> skillSetListView = new ListView<SkillSetDto>("skillSet", this.skillSetDtoList) {
        @Override
        protected void populateItem(ListItem<SkillSetDto> item) {

            SkillSetDto skillSetDto = (SkillSetDto) item.getModelObject();
            SkillSetEntity skillSetEntity = skillSetDto.getSkillSetEntity();
            List<SkillSetImageEntity> skillSetImageEntityList = skillSetDto.getSkillSetImageEntityList();

            ListView<SkillSetImageEntity> skillSetImageListView = new ListView<SkillSetImageEntity>("skillSetImageList",
                    skillSetImageEntityList) {
                @Override
                protected void populateItem(ListItem<SkillSetImageEntity> item) {
                    SkillSetImageEntity skillSetImageEntity = (SkillSetImageEntity) item.getDefaultModelObject();
                    WebMarkupContainer skillSetImageContainer = new WebMarkupContainer("skillSetImage");
                    skillSetImageContainer.add(new AttributeModifier("src",
                            SKILLSET_ICON_FOLDER.getUploadPath() + skillSetImageEntity.getImageName()));
                    item.add(skillSetImageContainer);

                }

            };
            item.queue(skillSetImageListView);
            item.queue(new Label("skillSetName", skillSetEntity.getProjectName()));
        }
    };

    public SkillSetPanel(String id, IModel<Integer> userId) {

        super(id, userId);

        setData(userId);

        skillSetListView.setDefaultModelObject(skillSetDtoList);
        queue(skillSetListView);
    }

    private void setData(IModel<Integer> userId) {

        SkillSetDao skillSetDao = new SkillSetDao();
        SkillsetImageDao skillSetImageDao = new SkillsetImageDao();

        List<SkillSetEntity> skillSetList = skillSetDao.getSkillSet(userId);
        for (SkillSetEntity skillSet : skillSetList) {

            SkillSetDto skillSetDto = new SkillSetDto();
            skillSetDto.setSkillSetEntity(skillSet);
            List<SkillSetImageEntity> skillSetImageList = skillSetImageDao.getSkillSetImage(skillSet.getId());
            skillSetDto.setSkillSetImageEntityList(skillSetImageList);

            skillSetDtoList.add(skillSetDto);
        }

    }

}