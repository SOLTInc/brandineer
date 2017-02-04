package com.solt_inc.page.registrationProfile.inputPanel.skillSet;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.solt_inc.model.entity.UserEntity;

public class SkillSetInputPanel extends Panel {
       
    public SkillSetInputPanel(String id, IModel<UserEntity> regModel) {
        
        super(id, regModel);
        IModel<UserEntity> compound = new CompoundPropertyModel<UserEntity>(regModel);
        Form<UserEntity> form = new Form<UserEntity>("form", compound);
        
        form.add(new Label("name"));
        form.add(new Label("photo"));
        form.add(new Label("age"));
        form.add(new Label("job_category"));
        form.add(new Label("location"));
        form.add(new Label("annual_income"));
        add(form);
    }
}