package com.solt_inc.page.profilePage.panel.profile;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.entity.UserEntity;

public class ProfilePanel extends Panel {
    private static final long serialVersionUID = 7514416342722447820L;
    
    private IModel<UserEntity> userModel;
    private Form<UserEntity>   form;
    private WebMarkupContainer photo;
    
    public ProfilePanel(String id, IModel<Integer> userId) {
        
        super(id, userId);
        UserDao userDao = new UserDao();
        this.userModel = userDao.getUserProfile(userId);
        this.settings();
        
        form.add(new Label("name"));
        form.add(photo);
        form.add(new Label("age"));
        form.add(new Label("jobCategory"));
        form.add(new Label("location"));
        form.add(new Label("annualIncome"));
        add(form);
    }
    
    private void settings() {
        IModel<UserEntity> compound = new CompoundPropertyModel<UserEntity>(userModel);
        this.form = new Form<UserEntity>("form", compound);
        this.photo = new WebMarkupContainer("photo");
        this.photo.add(new AttributeModifier("src", getString("user.profile.photo.path") + userModel.getObject().getPhotoName()));
        
    }
}