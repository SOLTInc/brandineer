package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.solt_inc.model.entity.UserEntity;

public class UserDao {

    public IModel<UserEntity> getUserProfile(int id) {
        
        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM project_db.user WHERE ID = ?";
        
        UserEntity user = new UserEntity();
        IModel<UserEntity> userModel;
        
        try(Connection con = cm.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()) {
                user.setId(rs.getInt("ID"));
                user.setFirstName(rs.getString("FIRST_NAME"));
                user.setLastName(rs.getString("LAST_NAME"));
                user.setPhotoName(rs.getString("ICON_PATH"));
                user.setAge(rs.getString("AGE"));
                user.setJobCategory(rs.getString("JOB_CATEGORY"));
                user.setLocation(rs.getString("LOCATION"));
                user.setAnnualIncome(rs.getString("ANNUAL_INCOME"));				
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userModel = new CompoundPropertyModel<UserEntity>(user);
        return userModel;
    }
        
    public int insert(UserEntity user) {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "INSERT INTO project_db.user"
                + " (FIRST_NAME, LAST_NAME, ICON_PATH, AGE, JOB_CATEGORY, LOCATION, ANNUAL_INCOME)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;
        
        try(Connection con = cm.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getPhotoName());
            pstmt.setString(4, user.getAge());
            pstmt.setString(5, user.getJobCategory());
            pstmt.setString(6, user.getLocation());
            pstmt.setString(7, user.getAnnualIncome());
            result = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public List<Integer> getAllUserId() {
        
        List<Integer> userIdList = new ArrayList<Integer>();
        
        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT ID FROM project_db.user";
        
        try(Connection con = cm.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                int userId = rs.getInt("ID");
                userIdList.add(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIdList;
    }

}