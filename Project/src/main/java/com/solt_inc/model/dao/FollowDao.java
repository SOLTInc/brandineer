package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.solt_inc.model.entity.FollowEntity;


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

    public boolean insert(FollowEntity follow, int iFollowLevel) {

        // UserEntity user = userModel.getObject();
        ConnectionManager cm = ConnectionManager.getInstance();

        StringBuffer sql = new StringBuffer();
        int count = 0;
        sql.append("INSERT INTO project_db.follow(");

            sql.append("USER_ID,");
            count++;

            sql.append("FOLLOW_ID,");
            count++;

            sql.append("FOLLOW_LEVEL,");
            count++;

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
                count++;
                pstmt.setInt(count, follow.getUserId());


                count++;
                pstmt.setInt(count, follow.getFollowId());

                count++;
                pstmt.setInt(count, iFollowLevel);


            result = pstmt.executeUpdate();

        } catch (SQLException e) {
      //      e.printStackTrace();
		throw new Error(e);

        }

        return result == 0 ? false : true;
    }

    public boolean update(FollowEntity follow, int iFollowLevel) {

        // UserEntity user = userModel.getObject();
        ConnectionManager cm = ConnectionManager.getInstance();

        StringBuffer sql = new StringBuffer();
        int count = 0;
        sql.append("UPDATE project_db.follow SET FOLLOW_LEVEL = ? WHERE USER_ID = ? AND FOLLOW_ID = ?");

        int result = 0;

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql.toString())) {

        	pstmt.setInt(1, iFollowLevel);
        	pstmt.setInt(2, follow.getUserId());
            pstmt.setInt(3, follow.getFollowId());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
      //      e.printStackTrace();
		throw new Error(e);

        }

        return result == 0 ? false : true;
    }

}
