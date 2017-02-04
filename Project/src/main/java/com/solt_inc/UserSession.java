package com.solt_inc;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.entity.UserEntity;

public class UserSession extends WebSession {
    private static final long serialVersionUID = 8771090050951362338L;
    
    private UserEntity user;
    
    public UserSession(Request request) {
        super(request);
    }
    
    public UserSession(Request request, int id) {
        super(request);
        UserDao userDao = new UserDao();
        user = userDao.getUserProfile(id);
    }
    
    public int getMyUserId() {
        return user.getId();
    }
    
    public String getMyName() {
        return user.getName();
    }
    
    public boolean isFollower() {
        return true;
    }
}
