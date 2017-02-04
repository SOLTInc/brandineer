package com.solt_inc.page.profilePage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.solt_inc.UserSession;
import com.solt_inc.model.entity.UserEntity;
import com.solt_inc.page.homePage.HomePage;
import com.solt_inc.page.mailPage.MailPage;
import com.solt_inc.page.profilePage.panel.hobby.HobbyPanel;
import com.solt_inc.page.profilePage.panel.profile.ProfilePanel;
import com.solt_inc.page.profilePage.panel.skillSet.SkillSetPanel;

public class ProfilePage extends WebPage {
    
    protected UserSession session;
    IModel<String> followImagePath = new Model<String>() {
        @Override
        public String getObject() {
            
            String followIcon;
            session = (UserSession)Session.get();
            if(session.isFollower()) {
                followIcon = "path.icon.follow";
            } else {
                followIcon = "path.icon.unfollow";
            }
            return followIcon;
            
        }
    };
    Form<?> form = new Form("form");
    Button backButton = new Button("back") {
        
        @Override
        public void onSubmit() {
            setResponsePage(HomePage.class);
        }
    };
    
    public ProfilePage(IModel<UserEntity> user) {
        
        super(user);
        Link<Void> mailLink = new Link<Void>("mail") {
            @Override
            public void onClick() {
                setResponsePage(MailPage.class);
            }
         };
        Link<Void> followLink = new Link<Void>("follow") {
            @Override
            public void onClick() {
                setResponsePage(MailPage.class);
            }
        };
        
        WebMarkupContainer mailIcon = new WebMarkupContainer("mailIcon");
        mailIcon.add(new AttributeModifier("src", getString("path.icon.mail")));
        WebMarkupContainer followIcon = new WebMarkupContainer("followIcon");
        followIcon.add(new AttributeModifier("src", getString(followImagePath.getObject())));
        
        form.add(mailLink);
        mailLink.add(mailIcon);
        form.add(followLink);
        followLink.add(followIcon);
        ProfilePanel profile = new ProfilePanel("profile", user);
        form.add(profile);
        HobbyPanel hobby = new HobbyPanel("hobby", user);
        form.add(hobby);
        SkillSetPanel portfolio = new SkillSetPanel("portfolio", user);
        form.add(portfolio);
        form.add(backButton);
        add(form);
    }
}