package com.solt_inc.page.registrationProfile.inputPanel.profile;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.solt_inc.model.entity.UserEntity;

public class ProfileInputPanel extends Panel {
    private static final long serialVersionUID = 7514416342722447820L;
    
    public ProfileInputPanel(String id, IModel<UserEntity> regModel) {
        
        
        super(id, regModel);
        IModel<UserEntity> compound = new CompoundPropertyModel<UserEntity>(regModel);
        Form<UserEntity> form = new Form<UserEntity>("form", compound);
        
        TextField<String> name = new TextField<String>("name");
        name.setRequired(true);
        form.add(name);
        form.add(new TextField<String>("photo"));
        form.add(new TextField<String>("age"));
        form.add(new TextField<String>("jobCategory"));
        form.add(new TextField<String>("location"));
        form.add(new TextField<String>("annualIncome"));
        add(form);
    }
}