package com.solt_inc.viewController.homePage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import com.solt_inc.viewController.profilePage.ProfilePage;
import com.solt_inc.viewController.registrationProfile.RegistrationUserPage;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;
    
    private Model userId = new Model();
    
    private Form detail = new Form("detail") {
        @Override
        protected void onSubmit() {
            ProfilePage profilePage = new ProfilePage( (HomePage)this.getParent() );
            setResponsePage(profilePage);
        }
    };
    
    private Form registration = new Form("registration") {
        @Override
        protected void onSubmit() {
            RegistrationUserPage userPage = new RegistrationUserPage();
            setResponsePage(userPage);
        }
    };
    
    public HomePage() {
        add(detail);
        TextField id = new TextField("id", this.userId);
        detail.add(id);
        add(registration);
    }
    
    public Model getUserId() {
        return this.userId;
    }
}