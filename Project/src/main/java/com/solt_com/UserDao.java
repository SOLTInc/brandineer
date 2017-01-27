package com.solt_com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.wicket.model.Model;

public class UserDao {

	public UserEntity getUserProfile(Model userId) {
		
		ConnectionManager cm = ConnectionManager.getInstance();
		String sql = "SELECT * FROM user WHERE USER_ID = ?";
		
		int userIdNo = -1;
		try {
			userIdNo = Integer.parseInt((String)userId.getObject());
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		UserEntity user = new UserEntity();
		
		try(Connection con = cm.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, userIdNo);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				user.setName(rs.getString("NAME"));
				user.setIcon(rs.getString("ICON_PATH"));
				user.setAge(rs.getString("AGE"));
				user.setJobCategory(rs.getString("JOB_CATEGORY"));
				user.setLocation(rs.getString("LOCATION"));
				user.setAnnualIncome(rs.getString("ANNUAL_INCOME"));				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	// TEST用
	public UserEntity getUserProfile(int userId) {
		
		ConnectionManager cm = ConnectionManager.getInstance();
		String sql = "SELECT * FROM user WHERE USER_ID = ?";
		
		UserEntity user = new UserEntity();
		
		try(Connection con = cm.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				user.setName(rs.getString("NAME"));
				user.setIcon(rs.getString("ICON_PATH"));
				user.setAge(rs.getString("AGE"));
				user.setJobCategory(rs.getString("JOB_CATEGORY"));
				user.setLocation(rs.getString("LOCATION"));
				user.setAnnualIncome(rs.getString("ANNUAL_INCOME"));				
			}
			
			if(user.getName() == null) {
				System.exit(0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public int insert(RegistrationUserPage user) {

		ConnectionManager cm = ConnectionManager.getInstance();
		String sql = "INSERT INTO testdb.user"
				+ " (NAME, ICON_PATH, AGE, JOB_CATEGORY, LOCATION, ANNUAL_INCOME)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		int result = 0;

		try(Connection con = cm.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, (String)user.name.getObject());
			pstmt.setString(2, (String)user.icon.getObject());
			pstmt.setString(3, (String)user.age.getObject());
			pstmt.setString(4, (String)user.jobCategory.getObject());
			pstmt.setString(5, (String)user.location.getObject());
			pstmt.setString(6, (String)user.annualIncome.getObject());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
