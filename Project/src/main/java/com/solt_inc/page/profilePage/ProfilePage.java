
package com.solt_inc.page.profilePage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.solt_inc.page.profilePage.panel.hobby.HobbyPanel;
import com.solt_inc.page.profilePage.panel.like.LikePanel;
import com.solt_inc.page.profilePage.panel.mind.MindPanel;
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

    private Panel MindPanel;

    private Panel LikePanel;

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
        MindPanel = new MindPanel("MindPanel", this.userId);
        add(MindPanel);
        LikePanel = new LikePanel("LikePanel", this.userId);
        add(LikePanel);
    }
}
