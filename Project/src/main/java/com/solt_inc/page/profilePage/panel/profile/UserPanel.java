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
    private IModel<String> firstNameModel = new PropertyModel<String>(userModel, "firstName");
    private IModel<String> lastNameModel = new PropertyModel<String>(userModel, "lastName");
    private IModel<String> photoNameModel = new PropertyModel<String>(userModel, "photoName");
    private IModel<Integer> ageModel = new PropertyModel<Integer>(userModel, "age");
    private IModel<LocalDate> birthdayModel = new PropertyModel<LocalDate>(userModel, "birthday");
    private IModel<String> companyModel = new PropertyModel<String>(userModel, "company");
    private IModel<String> jobCategoryModel = new PropertyModel<String>(userModel, "jobCategory");
    private IModel<String> locationModel = new PropertyModel<String>(userModel, "location");

    private Label firstName = new Label("firstName", firstNameModel);

    private Label lastName = new Label("lastName", lastNameModel);

    private Label age = new Label("age", ageModel);

    private Label birthday = new Label("birthday", birthdayModel);

    private Label company = new Label("company", companyModel);

    private Label jobCategory = new Label("jobCategory", jobCategoryModel);

    private Label location = new Label("location", locationModel);

    private WebMarkupContainer photo = new WebMarkupContainer("photo");

    public UserPanel(String id, IModel<Integer> userId) {

        super(id, userId);
        UserDao userDao = new UserDao();
        UserEntity userEntity = userDao.getUser(userId.getObject());
        this.userModel.setObject(userEntity);

        if (firstNameModel == null) {
            firstName.setVisible(false);
        }
        add(firstName);

        if (lastNameModel == null) {
            lastName.setVisible(false);
        }
        add(lastName);

        photo.add(new AttributeModifier("src", getString("user.profile.photo.path") + photoNameModel.getObject()));
        add(photo);

        if (this.ageModel == null) {
            age.setVisible(false);
        }
        add(age);

        if (this.birthdayModel == null) {
            birthday.setVisible(false);
        }
        add(birthday);

        if (companyModel == null) {
            company.setVisible(false);
        }
        add(company);

        if (jobCategory == null) {
            jobCategory.setVisible(false);
        }
        add(jobCategory);

        if (location == null) {
            location.setVisible(false);
        }
        add(location);

    }

}
