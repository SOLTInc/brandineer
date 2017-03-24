package com.solt_inc.page.profilePage.panel.skillSet;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.solt_inc.model.dao.SkillSetDao;
import com.solt_inc.model.entity.SkillSetEntity;

public class SkillSetPanel extends Panel {

    private List<SkillSetEntity> skillList;

    public SkillSetPanel(String id, IModel<Integer> userId) {

        super(id, userId);

        SkillSetDao skillDao = new SkillSetDao();
        this.skillList = skillDao.getSkillSet(userId);

        ListView<SkillSetEntity> skillList = new ListView<SkillSetEntity>("skillSet", this.skillList) {
            @Override
            protected void populateItem(ListItem<SkillSetEntity> item) {
                SkillSetEntity skill = (SkillSetEntity) item.getModelObject();
                item.add(new Label("skillName", skill.getProjectName()));
            }
        };
        add(skillList);
    }
}