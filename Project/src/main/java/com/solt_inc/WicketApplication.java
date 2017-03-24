package com.solt_inc;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.solt_inc.page.homePage.HomePage;
import com.solt_inc.page.mailPage.MailPage;
import com.solt_inc.page.registrationProfile.RegistrationUserPage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.solt_inc.Start#main(String[])
 */
public class WicketApplication extends WebApplication {

    /**
     * Constructor
     */
    public WicketApplication() {
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        mountPage("/home", HomePage.class);
        // mountPage("/profile", ProfilePage.class);
        mountPage("/registration", RegistrationUserPage.class);
        mountPage("/mail", MailPage.class);
    }

    @Override
    public Session newSession(Request request, Response response) {
        /*** test **/
        return new UserSession(request, new Model<Integer>(1));
    }
}