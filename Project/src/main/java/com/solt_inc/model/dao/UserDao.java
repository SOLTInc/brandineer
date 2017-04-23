package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.solt_inc.model.entity.UserEntity;

public class UserDao {

    public UserEntity getUser(int userId) {

        UserEntity user = new UserEntity();

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM project_db.user WHERE ID = ?";

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                user.setId(rs.getInt("ID"));
                user.setFirstName(rs.getString("FIRST_NAME"));
                user.setLastName(rs.getString("LAST_NAME"));
                user.setPhotoName(rs.getString("PHOTO_NAME"));
                user.setSqlBirthday(rs.getDate("BIRTHDAY"));
                user.setJobCategory(rs.getString("JOB_CATEGORY"));
                user.setLocation(rs.getString("LOCATION"));
                user.setCompany(rs.getString("COMPANY"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public List<UserEntity> getUserList() {

    	List<UserEntity> userList = new ArrayList<UserEntity>();

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM project_db.user";

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {

                UserEntity user = new UserEntity();
                user.setId(rs.getInt("ID"));
                user.setFirstName(rs.getString("FIRST_NAME"));
                user.setLastName(rs.getString("LAST_NAME"));
                user.setPhotoName(rs.getString("PHOTO_NAME"));
                user.setSqlBirthday(rs.getDate("BIRTHDAY"));
                user.setJobCategory(rs.getString("JOB_CATEGORY"));
                user.setLocation(rs.getString("LOCATION"));
                user.setCompany(rs.getString("COMPANY"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    public boolean insert(UserEntity user) {

        // UserEntity user = userModel.getObject();
        ConnectionManager cm = ConnectionManager.getInstance();

        StringBuffer sql = new StringBuffer();
        int count = 0;
        sql.append("INSERT INTO project_db.user(");
        if (user.getFirstName() != null) {
            sql.append("FIRST_NAME,");
            count++;
        }
        if (user.getLastName() != null) {
            sql.append("LAST_NAME,");
            count++;
        }
        if (user.getPhotoName() != null) {
            sql.append("PHOTO_NAME,");
            count++;
        }
        if (user.getBirthday() != null) {
            sql.append("BIRTHDAY,");
            count++;
        }
        if (user.getCompany() != null) {
            sql.append("COMPANY,");
            count++;
        }
        if (user.getJobCategory() != null) {
            sql.append("JOB_CATEGORY,");
            count++;
        }
        if (user.getLocation() != null) {
            sql.append("LOCATION,");
            count++;
        }
        sql.delete(sql.length() - 1, sql.length());
        sql.append(") VALUES (");
        for (int i = 0; i < count; i++) {
            sql.append("?,");
        }
        sql.delete(sql.length() - 1, sql.length());
        sql.append(")");
        // String sql = "INSERT INTO project_db.user"
        // + " (FIRST_NAME, LAST_NAME, PHOTO_NAME, BIRTHDAY, COMPANY,
        // JOB_CATEGORY, LOCATION)"
        // + " VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql.toString())) {

            // pstmt.setString(1, user.getFirstName());
            // pstmt.setString(2, user.getLastName());
            // pstmt.setString(3, user.getPhotoName());
            // pstmt.setDate(4, user.getSqlBirthday());
            // pstmt.setString(5, user.getCompany());
            // pstmt.setString(6, user.getJobCategory());
            // pstmt.setString(7, user.getLocation());
            count = 0;
            if (user.getFirstName() != null) {
                count++;
                pstmt.setString(count, user.getFirstName());
            }
            if (user.getLastName() != null) {
                count++;
                pstmt.setString(count, user.getLastName());
            }
            if (user.getPhotoName() != null) {
                count++;
                pstmt.setString(count, user.getPhotoName());
            }
            if (user.getBirthday() != null) {
                count++;
                pstmt.setDate(count, user.getSqlBirthday());
            }
            if (user.getCompany() != null) {
                count++;
                pstmt.setString(count, user.getCompany());
            }
            if (user.getJobCategory() != null) {
                count++;
                pstmt.setString(count, user.getJobCategory());
            }
            if (user.getLocation() != null) {
                count++;
                pstmt.setString(count, user.getLocation());
            }

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result == 0 ? false : true;
    }

    public List<Integer> getAllUsersId() {

        List<Integer> userIdList = new ArrayList<Integer>();

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT ID FROM project_db.user";

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("ID");
                userIdList.add(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIdList;
    }
 public List<UserEntity> getAllUsers() {
 	

        List<Integer> userIdList = getAllUsersId();
		List<UserEntity> res = new ArrayList<UserEntity>();
		for (int i = 0; i < userIdList.size(); i++) {
			res.add(getUser(userIdList.get(i)));
		}
		return res;
    }

    public int getUserId(String firstName) {

        int userId = 0;

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT Id FROM project_db.user WHERE FIRST_NAME = ?";

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, firstName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userId = rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

	public boolean update(int userId, UserEntity user) {

        ConnectionManager cm = ConnectionManager.getInstance();

        String sql = "UPDATE project_db.user "
        		+ "SET "
        		+ " FIRST_NAME = ?,"
                + " LAST_NAME = ?,"
                + " PHOTO_NAME = ?,"
                + " BIRTHDAY = ?,"
                + " COMPANY = ?,"
                + " JOB_CATEGORY = ?,"
                + " LOCATION = ?"
                +" "
                + "WHERE ID = ?";

        int result = 0;

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.setString(1, user.getFirstName());
                pstmt.setString(2, user.getLastName());
                pstmt.setString(3, user.getPhotoName());
                pstmt.setDate(4, user.getSqlBirthday());
                pstmt.setString(5, user.getCompany());
                pstmt.setString(6, user.getJobCategory());
                pstmt.setString(7, user.getLocation());
                pstmt.setInt(8, userId);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result == 0 ? false : true;
    }

}