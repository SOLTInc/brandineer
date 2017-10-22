package com.solt_inc.page.profilePage.panel.mind;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.solt_inc.model.dao.FollowDao;
import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.entity.FollowEntity;
import com.solt_inc.model.entity.UserEntity;
import com.solt_inc.page.profilePage.ProfilePage;

public class MindPanel extends Panel {
    private static final long serialVersionUID = 7514416342722447820L;

    private IModel<Integer> userId = new Model<Integer>(0);

    private IModel<UserEntity> userModel = new Model<UserEntity>();

    private Link<Void> followLink = new Link<Void>("follow") {
        @Override
        public void onClick() {
		boolean updateFlg = true;

            if (updateFlg) {
            	System.out.println("updateFollow");
            	updateFlg = updateFollow(userId.getObject());
            	System.out.println(updateFlg);
            }

            if (updateFlg) {
           	 ProfilePage profilePage = new ProfilePage(userId);
           	 setResponsePage(profilePage);
            } else {
                error("insert error");
            }
        }
    };

    public MindPanel(String id, IModel<Integer> userId) {

        super(id, userId);

                this.userId = userId;

        UserDao userDao = new UserDao();
        UserEntity userEntity = userDao.getUser(userId.getObject());
        this.userModel.setObject(userEntity);

        add(followLink);

    }



    private boolean updateFollow(int userId) {
    	boolean updateFlg = true;

    	FollowDao followDao = new FollowDao();
    	IModel<java.lang.Integer> myUserId = Model.of((Integer)Session.get().getAttribute("loginUser"));
    	FollowEntity followEntity = new FollowEntity();
    	followEntity.setFollowId(userId);
    	followEntity.setUserId(myUserId.getObject());

    	//USER_ID,FOLLOW_IDをキーに検索を行い、検索結果が存在する場合にUPDATE、存在しない場合INSERT

    	if (followDao.checkFollower(followEntity.getUserId(),followEntity.getFollowId())) {
    		//UPDATE
    		updateFlg = followDao.update(followEntity,1);
    	}else {
    		//INSERT
    		updateFlg = followDao.insert(followEntity,1);
    	}



    	return updateFlg;
    }

}
