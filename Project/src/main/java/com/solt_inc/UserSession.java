package com.solt_inc;

import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.solt_inc.model.dao.FollowDao;
import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.entity.UserEntity;

public class UserSession extends WebSession {
    private static final long serialVersionUID = 8771090050951362338L;
    
    private IModel<UserEntity> user;
    private FollowDao followDao;
    
    public UserSession(Request request) {
        super(request);
    }
    
    public UserSession(Request request, int id) {
        super(request);
        UserDao userDao = new UserDao();
        user = userDao.getUserProfile(id);
    }
    
    public int getMyUserId() {
        return user.getObject().getId();
    }
    
    public String getMyName() {
        return user.getObject().getFirstName();
    }
    
    public boolean isFollower(IModel<Integer> followerId) {
        
        if(this.followDao == null) {
            this.followDao = new FollowDao();
        }
        return followDao.checkFollower(user.getObject().getId(), followerId.getObject());
    }
}
