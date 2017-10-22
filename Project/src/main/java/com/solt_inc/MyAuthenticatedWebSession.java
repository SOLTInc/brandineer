package com.solt_inc;

import org.apache.wicket.request.Request;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import com.solt_inc.model.dao.*;
import com.solt_inc.model.dto.*;
import com.solt_inc.model.entity.*;
import org.apache.wicket.Session;


public final class MyAuthenticatedWebSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = 1L;

	public MyAuthenticatedWebSession(Request request) {
		super(request);
	}

	@Override
	public boolean authenticate(final String username, final String password) {
        UserDao userDao = new UserDao();
        UserEntity userEntity = userDao.getUser(username, password);
        if (userEntity == null ) {
        	userEntity = new UserEntity();
        	userEntity.setUserName(username);
        	userEntity.setPassword(password);
        	userDao.insert(userEntity);
        	userEntity = userDao.getUser(username, password);
        }
        Session.get().setAttribute("loginUser",userEntity.getId());
		return true;
	}

	@Override
	public Roles getRoles() {
		if (isSignedIn()) {
			return new Roles(Roles.USER);
		}
		return null;
	}

}