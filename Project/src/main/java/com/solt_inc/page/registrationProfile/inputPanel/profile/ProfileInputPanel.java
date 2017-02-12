package com.solt_inc.page.registrationProfile.inputPanel.profile;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.solt_inc.component.commonPanel.FileUploadPanel;
import com.solt_inc.model.entity.UserEntity;

public class ProfileInputPanel extends Panel {
    private static final long serialVersionUID = 7514416342722447820L;
    
    private Form<UserEntity> form;
    private TextField<String> firstName;
    private TextField<String> lastName;
   
    public ProfileInputPanel(String id, IModel<UserEntity> regModel) {
//      public ProfileInputPanel(String id) {
        
        super(id, regModel);
        
        settings();
        form = new Form<UserEntity>("form");
        
        firstName = new TextField<String>("firstName");
        firstName.setRequired(true);
        form.add(firstName);
        lastName = new TextField<String>("lastName");
        lastName.setRequired(true);
        form.add(lastName);
        
        form.add(new TextField<String>("age"));
        form.add(new TextField<String>("jobCategory"));
        form.add(new TextField<String>("location"));
        form.add(new TextField<String>("annualIncome"));
        
        form.add(new FileUploadPanel("fileUpload", new Model<String>(getString("user.profile.photo.path"))));
        add(form);
    }
    
    private void settings() {
        
        
    }
}
