
package com.solt_inc.page.profilePage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.solt_inc.UserSession;
import com.solt_inc.page.homePage.HomePage;
import com.solt_inc.page.mailPage.MailPage;
import com.solt_inc.page.profilePage.panel.hobby.HobbyPanel;
import com.solt_inc.page.profilePage.panel.profile.ProfilePanel;
import com.solt_inc.page.profilePage.panel.skillSet.SkillSetPanel;

public class ProfilePage extends WebPage {

    protected UserSession session;
    private IModel<Integer> userId = new Model<Integer>(0);
    private Link<Void> mailLink;
    private WebMarkupContainer mailIcon;
    private Link<Void> followLink;
    private WebMarkupContainer followIcon;
    private ProfilePanel profilePanel;
    private Link<?> backLink;
    private HobbyPanel hobbyPanel = new HobbyPanel("hobbyPanel", userId);
    private SkillSetPanel skillSetPanel = new SkillSetPanel("skillSetPanel", userId);
    // userId);

    public ProfilePage(IModel<Integer> userId) {

        super(userId);

        this.userId = userId;
        this.settings();
        // HobbyPanel hobbyPanel = new HobbyPanel("hobbyPanel", userId);
        // SkillSetPanel skillSetPanel = new SkillSetPanel("skillSetPanel",
        // userId);
        skillSetPanel.setDefaultModelObject(userId);

        hobbyPanel.setDefaultModelObject(userId);

        mailLink.add(mailIcon);
        add(mailLink);
        followLink.add(followIcon);
        add(followLink);
        add(profilePanel);
        add(backLink);
        add(hobbyPanel);
        add(skillSetPanel);
    }

    private void settings() {

        this.mailLink = new Link<Void>("mail") {
            @Override
            public void onClick() {
                setResponsePage(MailPage.class);
            }
        };
        this.mailIcon = new WebMarkupContainer("mailIcon");
        // this.mailIcon.add(new AttributeModifier("src",
        // getString("path.icon.mail")));

        this.mailIcon.add(new AttributeModifier("src", "/img/icon/mail.png"));

        this.followLink = new Link<Void>("follow") {
            @Override
            public void onClick() {
                setResponsePage(MailPage.class);
            }
        };
        this.followIcon = new WebMarkupContainer("followIcon");
        // this.followIcon.add(new AttributeModifier("src",
        // getString(followImagePath.getObject())));

        this.followIcon.add(new AttributeModifier("src", "/img/icon/follow.png"));

        this.profilePanel = new ProfilePanel("profilePanel", userId);

        backLink = new Link<Void>("backLink") {

            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }

        };
    }

}