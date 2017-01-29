package com.solt_inc.viewController.profilePage;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;

import com.solt_inc.model.dao.HobbyDao;
import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.entity.HobbyEntity;
import com.solt_inc.model.entity.UserEntity;
import com.solt_inc.viewController.homePage.HomePage;

public class ProfilePage extends WebPage {
    
    private static final long serialVersionUID = 3598788934381254916L;
    
    
    private List<HobbyEntity> getUserHobbys(Model userId) {
        HobbyDao hobbyDao = new HobbyDao();
        List<HobbyEntity> userHobbyList = hobbyDao.getUserHobby(userId);
        
        return userHobbyList;
    }
    
    private Form form = new Form("form") {
        
        @Override
        protected void onSubmit() {
            HomePage home = new HomePage();
            setResponsePage(home);
        }
    };
    
//  private ListView<HobbyEntity> hobbyList = new ListView<HobbyEntity>("hobby", this.getUserHobbys(1)) {
//      @Override
//      protected void populateItem(ListItem<HobbyEntity> item) {
//          HobbyEntity hobby = (HobbyEntity) item.getModelObject();
//          item.add(new Label("hobby_image", hobby.getHobbyImage()));
//          item.add(new Label("hobby_name", hobby.getHobbyName()));
//      }
//  };
    
    public ProfilePage(HomePage page) {
        
        UserDao userDao = new UserDao();
        UserEntity user = userDao.getUserProfile(page.getUserId());
        add(form);
        form.add(new Label("name", user.getName()));	
        form.add(new Label("icon", user.getIcon()));
        form.add(new Label("age", user.getAge()));
        form.add(new Label("job_category", user.getJobCategory()));
        form.add(new Label("location", user.getLocation()));
        form.add(new Label("annual_income", user.getAnnualIncome()));
        
        ListView<HobbyEntity> hobbyList = new ListView<HobbyEntity>("hobby", this.getUserHobbys(page.getUserId())) {
            @Override
            protected void populateItem(ListItem<HobbyEntity> item) {
                HobbyEntity hobby = (HobbyEntity) item.getModelObject();
                item.add(new Label("hobby_image", hobby.getHobbyImage()));
                item.add(new Label("hobby_name", hobby.getHobbyName()));
            }
        };
        form.add(hobbyList);
    }
}