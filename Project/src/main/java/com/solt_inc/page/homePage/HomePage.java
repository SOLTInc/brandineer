package com.solt_inc.page.homePage;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import com.solt_inc.model.dao.UserDao;
import com.solt_inc.page.profilePage.ProfilePage;
import com.solt_inc.page.registrationProfile.RegistrationUserPage;
import com.solt_inc.rtest.page.TestTopPage;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;

    private IModel<Integer> userId = new Model<Integer>(1);
    // private List<Integer> userIdList = new ArrayList<Integer>();
    private IModel<List<Integer>> userIdList = new ListModel<Integer>();

    private Form<?> detailForm = new Form<Void>("detail") {
        @Override
        protected void onSubmit() {

            ProfilePage profilePage = new ProfilePage(userId);
            setResponsePage(profilePage);
        }
    };

    private Form<?> registrationForm = new Form<Void>("registration") {
        @Override
        protected void onSubmit() {
            setResponsePage(RegistrationUserPage.class);
        }
    };

    private Form<?> testForm = new Form<Void>("test") {
        @Override
        protected void onSubmit() {
            setResponsePage(TestTopPage.class);
        }
    };

    private DropDownChoice<Integer> idList = new DropDownChoice<Integer>("idList", userId, this.userIdList);

    public HomePage() {

        add(new FeedbackPanel("feedBack"));
        add(detailForm);
        this.setUserId();
        idList.setRequired(true);
        detailForm.add(idList);
        add(registrationForm);
        add(testForm);

    }

    private void setUserId() {

        UserDao userDao = new UserDao();
        this.userIdList.setObject(userDao.getAllUsersId());
    }
}