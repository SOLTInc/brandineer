package com.solt_inc.page.homePage;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.behavior.AttributeAppender;

import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.entity.UserEntity;
import com.solt_inc.page.editProfile.EditProfilePage;
import com.solt_inc.page.profilePage.ProfilePage;
import com.solt_inc.page.registrationProfile.RegistrationUserPage;
import com.solt_inc.rtest.page.TestTopPage;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;

    private IModel<Integer> userId = new Model<Integer>(1);
    private IModel<List<Integer>> userIdList = new ListModel<Integer>();
    private IModel<List<UserEntity>> userList = new ListModel<UserEntity>();

    private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");

    private Form<?> detailForm = new Form<Void>("detail") {

		private static final long serialVersionUID = 1L;

		@Override
        protected void onSubmit() {

            ProfilePage profilePage = new ProfilePage(userId);
            setResponsePage(profilePage);
        }
    };

   private DropDownChoice<Integer> idList = new DropDownChoice<Integer>("userIdList", userId, userIdList);

   private DropDownChoice<Integer> editIdList = new DropDownChoice<Integer>("userIdList", userId, userIdList);

    private Form<?> registrationForm = new Form<Void>("registration") {

		private static final long serialVersionUID = 1L;

		@Override
        protected void onSubmit() {
            setResponsePage(RegistrationUserPage.class);
        }
    };

    private Form<?> editForm = new Form<Void>("edit") {

		private static final long serialVersionUID = 1L;

		@Override
        protected void onSubmit() {
        	EditProfilePage editPage = new EditProfilePage(userId);
            setResponsePage(editPage);
        }
    };

    private Form<?> testForm = new Form<Void>("test") {

		private static final long serialVersionUID = 1L;

		@Override
        protected void onSubmit() {
            setResponsePage(TestTopPage.class);
        }
    };

    public HomePage() {
    	
    	UserDao userDao = new UserDao();
    	List<UserEntity> userList = userDao.getUserList();

    	Form<?> form = new Form<Void>("form");
    	ListView<UserEntity> userListView = new ListView<UserEntity>("userList", userList) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<UserEntity> item) {
				if (item.getIndex() % 2 == 0) {
					item.add(new AttributeAppender("class", new Model("right-line"), " "));
				}
				UserEntity user = (UserEntity)item.getModelObject();

				Label firstName = new Label("firstName", user.getFirstName());
				Label lastName = new Label("lastName", user.getLastName());
				Label jobCategory = new Label("jobCategory", user.getJobCategory());

				WebMarkupContainer imgContainer = new WebMarkupContainer("photo");
				imgContainer.add(new AttributeModifier("src","/img/user/profile/photo/" + user.getPhotoName()));
				
				Link<?> detailLink =  new Link<Void>("detailLink") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						setResponsePage(new ProfilePage(Model.of(user.getId())));
						
					}
					
				};

				Link<?> editLink =  new Link<Void>("editLink") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						setResponsePage(new EditProfilePage(Model.of(user.getId())));
						
					}
					
				};
				
				item.queue(imgContainer);
				item.queue(firstName);
				item.queue(lastName);
				item.queue(jobCategory);
				item.queue(detailLink);
				item.queue(editLink);
				
			}
    		
    	};

        queue(feedbackPanel);
//        add(form);
        queue(userListView);

        queue(detailForm);
        userIdList.setObject(userDao.getAllUsersId());
        idList.setRequired(true);
        detailForm.queue(idList);
        
        //userList.setObject(userDao.getAllUsers());
        //detailForm.add(userList);

        queue(registrationForm);
        queue(editForm);
        editIdList.setRequired(true);
        editForm.queue(editIdList);
        queue(testForm);

    }

}