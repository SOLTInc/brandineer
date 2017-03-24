package com.solt_inc;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.solt_inc.model.dao.FollowDao;
import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.entity.UserEntity;

public class UserSession extends WebSession {
    private static final long serialVersionUID = 8771090050951362338L;

    private IModel<UserEntity> userModel;
    private IModel<Integer> userId;
    private FollowDao followDao;

    public UserSession(Request request) {
        super(request);
    }

    public UserSession(Request request, IModel<Integer> userId) {
        super(request);
        this.userId = userId;
        UserDao userDao = new UserDao();
        userModel = new Model<UserEntity>(userDao.getUser(userId.getObject()));
    }

    public int getMyUserId() {
        return this.userId.getObject();
    }

    public String getMyName() {
        return userModel.getObject().getFirstName();
    }

    public boolean isFollower(IModel<Integer> followerId) {

        if (this.followDao == null) {
            this.followDao = new FollowDao();
        }
        return followDao.checkFollower(this.userId.getObject(), followerId.getObject());
    }
}
