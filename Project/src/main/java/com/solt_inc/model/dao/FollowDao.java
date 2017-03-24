package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowDao {

    public boolean checkFollower(int myId, int followerId) {
        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT FOLLOW_ID FROM project_db.follow WHERE USER_ID = ? AND FOLLOW_ID = ?";
        int followId = 0;

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, myId);
            pstmt.setInt(2, followerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                followId = rs.getInt("FOLLOW_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return followId == 0 ? false : true;

    }

}
