package com.solt_inc;

import org.apache.wicket.request.Request;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;

public final class MyAuthenticatedWebSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = 1L;

	public MyAuthenticatedWebSession(Request request) {
		super(request);
	}

	@Override
	public boolean authenticate(final String username, final String password) {
		return username.equals("wicket") && password.equals("wicket");
	}

	@Override
	public Roles getRoles() {
		if (isSignedIn()) {
			return new Roles(Roles.USER);
		}
		return null;
	}

}