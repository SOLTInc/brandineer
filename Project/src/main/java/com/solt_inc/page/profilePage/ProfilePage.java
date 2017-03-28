
package com.solt_inc.page.profilePage;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Session;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
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
    private IModel<Integer> userId;
    private IModel<String> followImagePath;
    private Link<Void> mailLink;
    private WebMarkupContainer mailIcon;
    private Link<Void> followLink;
    private WebMarkupContainer followIcon;
    private ProfilePanel profilePanel;
    private AjaxTabbedPanel<ITab> tabsPanel;
    private List<ITab> tabs;
    private Button backButton;
    private Link<?> backLink;

    public ProfilePage(IModel<Integer> userId) {

        super(userId);

        this.userId = userId;
        this.settings();

        mailLink.add(mailIcon);
        add(mailLink);
        followLink.add(followIcon);
        add(followLink);
        add(profilePanel);
        add(tabsPanel);
        add(backLink);
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

        this.followImagePath = new Model<String>() {
            @Override
            public String getObject() {

                String followIcon;
                session = (UserSession) Session.get();
                if (session.isFollower(userId)) {
                    followIcon = "path.icon.follow";
                } else {
                    followIcon = "path.icon.unfollow";
                }
                return followIcon;

            }
        };
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
        this.tabs = new ArrayList<ITab>();
        tabs.add(new ProfileTab(new Model<String>("hobby"), userId) {
            @Override
            public Panel getPanel(String panelId) {
                return new HobbyPanel(panelId, userId);
            }
        });
        tabs.add(new ProfileTab(new Model<String>("SillSet"), userId) {
            @Override
            public Panel getPanel(String panelId) {
                return new SkillSetPanel(panelId, userId);
            }
        });
        this.tabsPanel = new AjaxTabbedPanel<ITab>("tabs", tabs);

        backLink = new Link<Void>("backLink") {

            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }

        };
    }

    public abstract class ProfileTab implements ITab {

        private IModel<String> title;
        private IModel<Integer> userId;

        public ProfileTab(IModel<String> title, IModel<Integer> userId) {
            this.title = title;
            this.userId = userId;
        }

        @Override
        public IModel<String> getTitle() {
            // TODO 自動生成されたメソッド・スタブ
            return title;
        }

        @Override
        abstract public WebMarkupContainer getPanel(String containerId);

        @Override
        public boolean isVisible() {
            // TODO 自動生成されたメソッド・スタブ
            return true;
        }
    }
}