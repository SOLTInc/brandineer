package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.Model;

import com.solt_inc.model.entity.HobbyEntity;

public class HobbyDao {

    public List<HobbyEntity> getHobby(int id) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    public List<HobbyEntity> getHobby() {
        
        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM hobby";
        
        List<HobbyEntity> hobbyList = new ArrayList<HobbyEntity>();
        
        try(Connection con = cm.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()) {
                
                HobbyEntity hobby = new HobbyEntity();
                
                hobby.setHobbyName(rs.getString("HOBBY_NAME"));
                hobby.setHobbyImage(rs.getString("HOBBY_IMAGE_PATH"));
                
                hobbyList.add(hobby);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hobbyList;
    }
    
    public List<HobbyEntity> getUserHobby(Model userId) {
        
        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM hobby WHERE USER_ID = ?";
        
        int userIdNo = -1;
        try {
            userIdNo = Integer.parseInt((String)userId.getObject());
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
        
        List<HobbyEntity> hobbyList = new ArrayList<HobbyEntity>();
        
        try(Connection con = cm.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, userIdNo);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                HobbyEntity hobby = new HobbyEntity();
                
                hobby.setHobbyName(rs.getString("HOBBY_NAME"));
                hobby.setHobbyImage(rs.getString("HOBBY_IMAGE_PATH"));
                
                hobbyList.add(hobby);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return hobbyList;
    }
}