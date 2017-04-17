package com.solt_inc.page.editProfile.editPanel.profile;

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
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.file.Folder;

import com.solt_inc.WicketApplication;
import com.solt_inc.component.file.ImageFile;
import com.solt_inc.component.folder.UploadFolder;
import com.solt_inc.component.panel.dateField.DateFieldPanel;
import com.solt_inc.component.panel.fileUpload.FileUploadPanel;
import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.dto.UserDto;
import com.solt_inc.model.entity.UserEntity;

public class EditUserPanel extends Panel {
    private static final long serialVersionUID = 7514416342722447820L;

    private final Folder UPLOAD_FOLDER = new UploadFolder(((WicketApplication) Application.get()).getUploadFolder(),
            "user" + File.separator + "profile" + File.separator + "photo" + File.separator);
    private IModel<ImageFile> fileModel = Model.of();

    private IModel<Integer> userIdModel;
    private IModel<UserDto> userDtoModel;

    private IModel<UserEntity> userEntityModel = Model.of();
    private IModel<String> firstNameModel = new PropertyModel<String>(userEntityModel, "firstName");
    private IModel<String> lastNameModel = new PropertyModel<String>(userEntityModel, "lastName");
    private IModel<String> photoNameModel = new PropertyModel<String>(userEntityModel, "photoName");
    private IModel<LocalDate> birthdayModel = new PropertyModel<LocalDate>(userEntityModel, "birthday");
    private IModel<String> companyModel = new PropertyModel<String>(userEntityModel, "company");
    private IModel<String> jobCategoryModel = new PropertyModel<String>(userEntityModel, "jobCategory");
    private IModel<String> locationModel = new PropertyModel<String>(userEntityModel, "location");


    public EditUserPanel(String id, IModel<UserDto> userDtoModel, IModel<Integer> userIdModel) {

        super(id, userDtoModel);
        this.userIdModel = userIdModel;
        this.userDtoModel = userDtoModel;
        
        UserDao userDao = new UserDao();
        userEntityModel.setObject(userDao.getUser(this.userIdModel.getObject()));

        Form<?> form = new Form<Void>("form") {
    		private static final long serialVersionUID = 1L;

	    	@Override
            public void onSubmit() {

            if (fileModel.getObject() != null) {
                ImageFile image = fileModel.getObject();
                photoNameModel.setObject(image.getName());
            }
            if(userDtoModel.getObject() != null) {
            	userDtoModel.setObject(new UserDto());
            }
            userDtoModel.getObject().setUserEntity(userEntityModel.getObject());
        }
    };
     TextField<String> firstName = new TextField<String>("firstName", firstNameModel);
     TextField<String> lastName = new TextField<String>("lastName", lastNameModel);
    // private Image photo = new Image("photo", photoNameModel);
     WebMarkupContainer photo = new WebMarkupContainer("photo") {
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
        if (photoNameModel.getObject() != null) {
            AttributeModifier photoSrcModifier = new AttributeModifier("src",
                    "/img/user/profile/photo/" + photoNameModel.getObject());
            photo.add(photoSrcModifier);
        }

     FileUploadPanel fileUploadPanel = new FileUploadPanel("fileUpload", UPLOAD_FOLDER, photo);
     DateFieldPanel dateFieldPanel = new DateFieldPanel("birthday", birthdayModel);

     TextField<String> company = new TextField<String>("company", companyModel);
     TextField<String> jobCategory = new TextField<String>("jobCategory", jobCategoryModel);
     TextField<String> location = new TextField<String>("location", locationModel);


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
