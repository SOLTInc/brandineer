package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowDao {

    public boolean checkFollower(int myId, int followerId) {
        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT FOLLOW_FLG FROM project_db.follow WHERE MY_ID = ? FOLLOW_ID = ?";
        boolean followFlg = false;
        
        try(Connection con = cm.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setInt(1, myId);
            pstmt.setInt(2, followerId);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()) {
                followFlg = rs.getBoolean("FOLLOW_FLG");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("followFlg : " + followFlg);
        return followFlg;
    }

}
