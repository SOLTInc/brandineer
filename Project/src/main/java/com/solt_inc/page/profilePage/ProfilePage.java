
package com.solt_inc.page.profilePage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.solt_inc.UserSession;
import com.solt_inc.page.homePage.HomePage;
import com.solt_inc.page.mailPage.MailPage;
import com.solt_inc.page.profilePage.panel.hobby.HobbyPanel;
import com.solt_inc.page.profilePage.panel.profile.UserPanel;
import com.solt_inc.page.profilePage.panel.skillSet.SkillSetPanel;

public class ProfilePage extends WebPage {

    protected UserSession session;
    private IModel<Integer> userId = new Model<Integer>(0);

    private Link<Void> mailLink = new Link<Void>("mail") {
        @Override
        public void onClick() {
            setResponsePage(MailPage.class);
        }
    };

    private WebMarkupContainer mailIcon = new WebMarkupContainer("mailIcon");

    private Link<Void> followLink = new Link<Void>("follow") {
        @Override
        public void onClick() {
            setResponsePage(MailPage.class);
        }
    };

    private WebMarkupContainer followIcon = new WebMarkupContainer("followIcon");

    private Panel userPanel;

    private Link<?> backLink = new Link<Void>("backLink") {
        @Override
        public void onClick() {
            setResponsePage(HomePage.class);
        }
    };

    private Panel hobbyPanel;

    private Panel skillSetPanel;
    // userId);

    public ProfilePage(IModel<Integer> userId) {

        super(userId);

        this.userId = userId;

        mailLink.add(mailIcon);
        mailIcon.add(new AttributeModifier("src", "/img/icon/mail.png"));
        add(mailLink);
        followIcon.add(new AttributeModifier("src", "/img/icon/follow.png"));
        followLink.add(followIcon);
        add(followLink);
        userPanel = new UserPanel("userPanel", this.userId);
        add(userPanel);
        add(backLink);
        hobbyPanel = new HobbyPanel("hobbyPanel", this.userId);
        add(hobbyPanel);
        skillSetPanel = new SkillSetPanel("skillSetPanel", this.userId);
        add(skillSetPanel);
    }

}