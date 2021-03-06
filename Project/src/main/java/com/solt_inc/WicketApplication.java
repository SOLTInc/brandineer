package com.solt_inc;

import java.io.File;

import org.apache.wicket.Session;
import org.apache.wicket.bean.validation.BeanValidationConfiguration;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.solt_inc.component.folder.UploadFolder;
import com.solt_inc.page.homePage.HomePage;
import com.solt_inc.page.mailPage.MailPage;
import com.solt_inc.page.registrationProfile.RegistrationUserPage;

import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.pages.SignInPage;
/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.solt_inc.Start#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication {

    private UploadFolder uploadFolder;
    private String contextPath;

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
     * 
     * @return the folder for uploads
     */
    public UploadFolder getUploadFolder() {
        return this.uploadFolder;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

		getDebugSettings().setAjaxDebugModeEnabled(false); 

        new BeanValidationConfiguration().configure(this);
        // uploadFolder = new Folder(this.getSystemProperty("java.io.tmpdir"),
        // "wickekt-uploads");
        uploadFolder = new UploadFolder(this.getContextPath(), "img/");
        uploadFolder.mkdirs();

        mountPage("/home", HomePage.class);
        // mountPage("/profile", ProfilePage.class);
        //mountPage("/registration", RegistrationUserPage.class);
        //mountPage("/mail", MailPage.class);

        getApplicationSettings().setUploadProgressUpdatesEnabled(true);
    }



    /** 削除予定 **/
    private String getSystemProperty(String key) {

        String property = System.getProperty(key);
        property += (property.endsWith(File.separator)) ? "" : File.separator;
        return property;

    }

    public String getContextPath() {
        if (contextPath == null) {
            contextPath = this.getServletContext().getRealPath("/");
        }
        return contextPath;
    }
	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}
    	@Override
	protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
		return MyAuthenticatedWebSession.class;
	}
}