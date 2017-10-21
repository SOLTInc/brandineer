
package com.solt_inc.page.profilePage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.solt_inc.page.homePage.HomePage;
import com.solt_inc.page.mailPage.MailPage;
import com.solt_inc.page.profilePage.panel.hobby.HobbyPanel;
import com.solt_inc.page.profilePage.panel.profile.UserPanel;
import com.solt_inc.page.profilePage.panel.skillSet.SkillSetPanel;



public class ProfilePage extends WebPage {

    private IModel<Integer> userId = new Model<Integer>(0);

    private Panel userPanel;

    //private Link<?> backLink = new Link<Void>("backLink") {
    //    @Override
    //    public void onClick() {
    //        setResponsePage(HomePage.class);
    //    }
    //};

    private Panel hobbyPanel;

    private Panel skillSetPanel;
    // userId);

    public ProfilePage(IModel<Integer> userId) {

        super(userId);

        this.userId = userId;

        userPanel = new UserPanel("userPanel", this.userId);
        add(userPanel);
        hobbyPanel = new HobbyPanel("hobbyPanel", this.userId);
        add(hobbyPanel);
        skillSetPanel = new SkillSetPanel("skillSetPanel", this.userId);
        add(skillSetPanel);
    }
}
