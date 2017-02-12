package com.solt_inc.page.registrationProfile;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.entity.UserEntity;
import com.solt_inc.page.homePage.HomePage;
import com.solt_inc.page.registrationProfile.inputPanel.profile.ProfileInputPanel;
public class RegistrationUserPage extends WebPage {
    private static final long serialVersionUID = 7514382722447820L;
    
    UserEntity user = new UserEntity();
    IModel<UserEntity> regModel = new CompoundPropertyModel<UserEntity>(user);
    Button cancelButton;
    
    public RegistrationUserPage(PageParameters params) {
        
        add(new FeedbackPanel("feedback"));
        
        Form<?> form = new Form("form");
        form.add(new ProfileInputPanel("profile", regModel));
//        form.add(new ProfileInputPanel("profile"));
//        form.add(new TestFileUploadPanel("profile"));
        form.add(new Button("register") {
            private static final long serialVersionUID = 1L;
            @Override
            public void onSubmit() {
                UserDao userDao = new UserDao();
                
                if(userDao.insert(regModel.getObject()) >= 1) {
                    setResponsePage(HomePage.class);
                } else {
                    error("insert error");
                }
            }
        });
        cancelButton = new Button("cancel") {
            @Override
            public void onSubmit() {
                setResponsePage(HomePage.class);
            }
            
        };
        cancelButton.setDefaultFormProcessing(false);
        form.add(cancelButton);
        
        add(form);
    }
}