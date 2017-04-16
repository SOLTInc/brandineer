package com.solt_inc.page.registrationProfile.inputPanel.profile;

import java.io.File;
import java.time.LocalDate;

import org.apache.wicket.Application;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.file.Folder;

import com.solt_inc.WicketApplication;
import com.solt_inc.component.file.ImageFile;
import com.solt_inc.component.folder.UploadFolder;
import com.solt_inc.component.panel.dateField.DateFieldPanel;
import com.solt_inc.component.panel.fileUpload.FileUploadPanel;
import com.solt_inc.model.dto.UserDto;
import com.solt_inc.model.entity.UserEntity;

public class ProfileInputPanel extends Panel {
    private static final long serialVersionUID = 7514416342722447820L;

    private final Folder UPLOAD_FOLDER = new UploadFolder(((WicketApplication) Application.get()).getUploadFolder(),
            "user" + File.separator + "profile" + File.separator + "photo" + File.separator);
    private IModel<ImageFile> fileModel = Model.of();

    private IModel<UserDto> userDtoModel;

    private UserDto userDto = new UserDto();
    private UserEntity userEntity = new UserEntity();
    private IModel<String> firstNameModel = LambdaModel.of(userEntity::getFirstName, userEntity::setFirstName);
    private IModel<String> lastNameModel = LambdaModel.of(userEntity::getLastName, userEntity::setLastName);
    private IModel<String> photoNameModel = LambdaModel.of(userEntity::getPhotoName, userEntity::setPhotoName);
    private IModel<LocalDate> birthdayModel = LambdaModel.of(userEntity::getBirthday, userEntity::setBirthday);
    private IModel<String> companyModel = LambdaModel.of(userEntity::getCompany, userEntity::setCompany);
    private IModel<String> jobCategoryModel = LambdaModel.of(userEntity::getJobCategory, userEntity::setJobCategory);
    private IModel<String> locationModel = LambdaModel.of(userEntity::getLocation, userEntity::setLocation);

    private Form<?> form = new Form<Void>("form") {
		private static final long serialVersionUID = 1L;

		@Override
        public void onSubmit() {

            if (fileModel.getObject() != null) {
                ImageFile image = fileModel.getObject();
                photoNameModel.setObject(image.getName());
            }
            userDto.setUserEntity(userEntity);
            userDtoModel.setObject(userDto);
        }
    };
    private TextField<String> firstName = new TextField<String>("firstName", firstNameModel);
    private TextField<String> lastName = new TextField<String>("lastName", lastNameModel);
    // private Image photo = new Image("photo", photoNameModel);
    private WebMarkupContainer photo = new WebMarkupContainer("photo") {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public void onEvent(IEvent<?> event) {
            Object payload = event.getPayload();
            if (payload instanceof ImageFile) {
                ImageFile imageFile = (ImageFile) payload;
                photoNameModel.setObject(imageFile.getName());
                add(new AttributeModifier("src", imageFile.getImagePath()));
            }
        }
    };
    private FileUploadPanel fileUploadPanel = new FileUploadPanel("fileUpload", UPLOAD_FOLDER, photo);
    private DateFieldPanel dateFieldPanel = new DateFieldPanel("birthday", birthdayModel);

    private TextField<String> company = new TextField<String>("company", companyModel);
    private TextField<String> jobCategory = new TextField<String>("jobCategory", jobCategoryModel);
    private TextField<String> location = new TextField<String>("location", locationModel);

    public ProfileInputPanel(String id, IModel<UserDto> userDtoModel) {

        super(id, userDtoModel);
        this.userDtoModel = userDtoModel;
        if (userDtoModel.getObject() != null) {
            userDto = userDtoModel.getObject();
        }
        if (userDto.getUserEntity() != null) {
            userEntity = userDto.getUserEntity();
        }
        if (photoNameModel.getObject() != null) {
            AttributeModifier photoSrcModifier = new AttributeModifier("src",
                    "/img/user/profile/photo/" + photoNameModel.getObject());
            photo.add(photoSrcModifier);
        }

        queue(form);

        // firstName.add(new PropertyValidator<String>());
        form.queue(firstName);

        // lastName.add(new PropertyValidator<String>());
        form.queue(lastName);
        form.queue(dateFieldPanel);
        form.queue(company);
        form.queue(jobCategory);
        form.queue(location);
        form.queue(fileUploadPanel);
        photo.setOutputMarkupId(true);
        form.queue(photo);

    }

}
