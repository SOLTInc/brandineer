package com.solt_inc.page.registrationProfile.inputPanel.profile;

import java.time.LocalDate;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.solt_inc.component.commonPanel.dateField.DateFieldPanel;
import com.solt_inc.component.commonPanel.fileUpload.FileUploadPanel;
import com.solt_inc.model.dto.UserDto;
import com.solt_inc.model.entity.UserEntity;

public class ProfileInputPanel extends Panel {
    private static final long serialVersionUID = 7514416342722447820L;

    private IModel<UserDto> userDtoModel;
    private IModel<String> firstName;
    private IModel<String> lastName;
    private IModel<String> photoName;
    private IModel<LocalDate> birthday;
    private IModel<String> company;
    private IModel<String> jobCategory;
    private IModel<String> location;
    private DateFieldPanel dateFieldPanel;

    public ProfileInputPanel(String id, IModel<UserDto> userDtoModel) {

        super(id, userDtoModel);
        this.userDtoModel = userDtoModel;

        settings();
        Form<?> form = new Form("form");
        add(form);

        TextField<String> firstName = new TextField<String>("firstName", this.firstName);
        firstName.setRequired(true);
        form.add(firstName);

        TextField<String> lastName = new TextField<String>("lastName", this.lastName);
        lastName.setRequired(true);
        form.add(lastName);

        this.dateFieldPanel = new DateFieldPanel("birthday", this.birthday);
        form.add(dateFieldPanel);
        form.add(new TextField<String>("company", this.company));
        form.add(new TextField<String>("jobCategory", this.jobCategory));
        form.add(new TextField<String>("location", this.location));

        form.add(new FileUploadPanel("fileUpload", this.photoName,
                new Model<String>(getString("user.profile.photo.path"))));

    }

    private void settings() {

        UserDto userDto;
        if (userDtoModel.getObject() == null) {
            userDto = new UserDto();
        } else {
            userDto = userDtoModel.getObject();
        }
        UserEntity userEntity;
        if (userDto.getUserEntity() == null) {
            userEntity = new UserEntity();
        } else {
            userEntity = userDto.getUserEntity();
        }
        this.firstName = new PropertyModel<String>(userEntity, "firstName");
        this.lastName = new PropertyModel<String>(userEntity, "lastName");
        this.photoName = new PropertyModel<String>(userEntity, "photoName");
        this.company = new PropertyModel<String>(userEntity, "company");
        this.jobCategory = new PropertyModel<String>(userEntity, "jobCategory");
        this.location = new PropertyModel<String>(userEntity, "location");

        userDto.setUserEntity(userEntity);
        this.userDtoModel.setObject(userDto);
    }

    public IModel<UserDto> getUserModel() {

        UserDto userDto = this.userDtoModel.getObject();
        UserEntity userEntity = userDto.getUserEntity();
        this.birthday = this.dateFieldPanel.getDate();
        userEntity.setBirthday(this.birthday.getObject());
        userDto.setUserEntity(userEntity);
        this.userDtoModel.setObject(userDto);

        return this.userDtoModel;

    }
}
