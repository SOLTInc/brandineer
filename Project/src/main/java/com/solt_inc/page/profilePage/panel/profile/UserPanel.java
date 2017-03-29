package com.solt_inc.page.profilePage.panel.profile;

import java.time.LocalDate;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.entity.UserEntity;

public class UserPanel extends Panel {
    private static final long serialVersionUID = 7514416342722447820L;

    private IModel<UserEntity> userModel = new Model<UserEntity>();
    private IModel<String> firstName = new PropertyModel<String>(userModel, "firstName");
    private IModel<String> lastName = new PropertyModel<String>(userModel, "lastName");
    private IModel<Integer> age = new PropertyModel<Integer>(userModel, "age");
    private IModel<LocalDate> birthday = new PropertyModel<LocalDate>(userModel, "birthday");
    private IModel<String> company = new PropertyModel<String>(userModel, "company");
    private IModel<String> jobCategory = new PropertyModel<String>(userModel, "jobCategory");
    private IModel<String> location = new PropertyModel<String>(userModel, "location");
    private WebMarkupContainer photo = new WebMarkupContainer("photo");

    public UserPanel(String id, IModel<Integer> userId) {

        super(id, userId);
        UserDao userDao = new UserDao();
        UserEntity userEntity = userDao.getUser(userId.getObject());
        this.userModel.setObject(userEntity);
        this.settings();

        if (this.firstName != null) {
            add(new Label("firstName", firstName));
        } else {
            add(new Label("firtsName").setVisible(false));
        }
        if (this.lastName != null) {
            add(new Label("lastName", lastName));
        } else {
            add(new Label("lastName").setVisible(false));
        }
        if (this.photo != null) {
            add(photo);
        } else {
            add(new Label("photo").setVisible(false));
        }
        if (this.age != null) {
            add(new Label("age", age));
        } else {
            add(new Label("age").setVisible(false));
        }
        if (this.birthday != null) {
            add(new Label("birthday", birthday));
        } else {
            add(new Label("birthday").setVisible(false));
        }
        if (this.company != null) {
            add(new Label("company", company));
        } else {
            add(new Label("company").setVisible(false));
        }
        if (this.jobCategory != null) {
            add(new Label("jobCategory", jobCategory));
        } else {
            add(new Label("jobCategory").setVisible(false));
        }
        if (this.location != null) {
            add(new Label("location", location));
        } else {
            add(new Label("location").setVisible(false));
        }

        photo.add(new AttributeModifier("src", getString("user.profile.photo.path") + userEntity.getPhotoName()));
    }

    private void settings() {
        UserEntity userEntity = userModel.getObject();
        // this.firstName = new Model<String>(userEntity.getFirstName());
        this.lastName = new Model<String>(userEntity.getLastName());
        this.age = new Model<Integer>(userEntity.getAge());
        this.birthday = new Model<LocalDate>(userEntity.getBirthday());
        this.company = new Model<String>(userEntity.getCompany());
        this.jobCategory = new Model<String>(userEntity.getJobCategory());
        this.location = new Model<String>(userEntity.getLocation());

    }
}
