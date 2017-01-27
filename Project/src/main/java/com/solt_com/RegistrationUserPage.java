package com.solt_com;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

public class RegistrationUserPage extends WebPage {
	
	public Model name = new Model();
	public Model icon = new Model();
	public Model age = new Model();
	public Model jobCategory = new Model();
	public Model location = new Model();
	public Model annualIncome = new Model();
	
	private Form user = new Form("user") {
		@Override
		protected void onSubmit() {
			
			UserDao uDao = new UserDao();
			uDao.insert((RegistrationUserPage)this.getParent());
			
			HomePage home = new HomePage();
			setResponsePage(home);
		}
	};
	
	public RegistrationUserPage() {
		
		add(user);
		user.add(new Label("name", this.name));
		user.add(new Label("icon", this.icon));
		user.add(new Label("age", this.age));
		user.add(new Label("job_category", this.jobCategory));
		user.add(new Label("location", this.location));
		user.add(new Label("annual_income", this.annualIncome));
		
	}
}
