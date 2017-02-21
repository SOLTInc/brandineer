package com.solt_inc.page.registrationProfile.inputPanel.hobby;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.solt_inc.model.entity.HobbyEntity;

public class HobbyInputPanel extends Panel {
    
    public HobbyInputPanel(String id, IModel<HobbyEntity> regModel) {
        
        super(id, regModel);
        IModel<HobbyEntity> compound = new CompoundPropertyModel<HobbyEntity>(regModel);
        Form<HobbyEntity> form = new Form<HobbyEntity>("form", compound);
        
        form.add(new TextField("hobby_name"));
        form.add(new TextField("hobby_image"));
        add(form);
    }
}