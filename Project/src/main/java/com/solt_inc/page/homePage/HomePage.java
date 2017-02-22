package com.solt_inc.page.homePage;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.entity.UserEntity;
import com.solt_inc.page.profilePage.ProfilePage;
import com.solt_inc.page.registrationProfile.RegistrationUserPage;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;
    
    private IModel<Integer> userId = new Model<Integer>();
    private IModel<UserEntity> user;
    private List<Integer> userIdList;
    
    private Form<?> detailForm = new Form("detail") {
        @Override
        protected void onSubmit() {
            
//            UserDao userDao = new UserDao();
//            user = userDao.getUserProfile(userId);
            
            ProfilePage profilePage = new ProfilePage(userId);
            setResponsePage(profilePage);
        }
    };
    
    private Form<?> registrationForm = new Form("registration") {
        @Override
        protected void onSubmit() {
            setResponsePage(RegistrationUserPage.class);
        }
    };
    
    public HomePage() {
        
        add(new FeedbackPanel("feedBack"));
        add(detailForm);
        this.setUserId();
        DropDownChoice<Integer> idList = new DropDownChoice<Integer>("idList",userId, this.userIdList);
        idList.setRequired(true);
        detailForm.add(idList);
        add(registrationForm);
        
    }
    
    private void setUserId() {
        
        UserDao userDao = new UserDao();
        this.userIdList = userDao.getAllUserId();
    }
}