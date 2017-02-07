
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
import org.apache.wicket.markup.html.form.Form;
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
    
    protected UserSession       session;
    private Form<?>             form;
    private IModel<String>      followImagePath;
    private Link<Void>          mailLink;
    private WebMarkupContainer  mailIcon;
    private Link<Void>          followLink;
    private WebMarkupContainer  followIcon;
    private ProfilePanel        profile;
    private List<ITab>          tabs;
    private Button              backButton;
    
    public ProfilePage(IModel<Integer> userId) {
        
        super(userId);
        this.settings(userId);
        
        mailLink.add(mailIcon);
        form.add(mailLink);
        followLink.add(followIcon);
        form.add(followLink);
        form.add(profile);
        form.add(new AjaxTabbedPanel<ITab>("tabs", tabs));
        form.add(backButton);
        add(form);
    }
    
    private void settings(IModel<Integer> userId) {
        
        this.form = new Form("form");
        
        this.mailLink = new Link<Void>("mail") {
            @Override
            public void onClick() {
                setResponsePage(MailPage.class);
            }
         };
        this.mailIcon = new WebMarkupContainer("mailIcon");
        this.mailIcon.add(new AttributeModifier("src", getString("path.icon.mail")));
        
        this.followImagePath = new Model<String>() {
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
        this.followLink = new Link<Void>("follow") {
            @Override
            public void onClick() {
                setResponsePage(MailPage.class);
            }
        };
        this.followIcon = new WebMarkupContainer("followIcon");
        this.followIcon.add(new AttributeModifier("src", getString(followImagePath.getObject())));
        
        this.profile = new ProfilePanel("profile", userId);
        
        this.tabs = new ArrayList<ITab>();
        tabs.add(new ProfileTab(new Model<String>("hobby"), userId) {
            @Override
            public Panel getPanel(String panelId) {
                return new HobbyPanel(panelId, this.getUser());
            }
        });
        tabs.add(new ProfileTab(new Model<String>("portfolio"), userId) {
            @Override
            public Panel getPanel(String panelId) {
                return new SkillSetPanel(panelId,this.getUser());
            }
        });
        
        this.backButton = new Button("back") {
            
            @Override
            public void onSubmit() {
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
        
        public IModel<Integer> getUser() {
            return this.userId;
        }
        
        public void setUser(IModel<Integer> userId) {
            this.userId = userId;
        }
        
    }
}