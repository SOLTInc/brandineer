package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.solt_inc.model.entity.HobbyEntity;

public class HobbyDao {

    public int getHobbyId(String hobbyName) {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT ID FROM hobby WHERE HOBBY_NAME = ? ORDER BY DATA_MODEFIED DESC";

        int hobbyId = -1;

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, hobbyName);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                hobbyId = rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hobbyId;
    }

    public List<HobbyEntity> getAllHobbys() {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM hobby";

        List<HobbyEntity> hobbyList = new ArrayList<HobbyEntity>();

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HobbyEntity hobby = new HobbyEntity();

                hobby.setId(rs.getInt("ID"));
                hobby.setUserId(rs.getInt("USER_ID"));
                hobby.setHobbyName(rs.getString("HOBBY_NAME"));
                hobby.setHobbyIcon(rs.getString("HOBBY_ICON"));
                hobby.setDescription(rs.getString("DESCRIPTION"));

                hobbyList.add(hobby);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hobbyList;
    }

    public List<HobbyEntity> getUserHobby(int userId) {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM hobby WHERE USER_ID = ?";

        List<HobbyEntity> hobbyList = new ArrayList<HobbyEntity>();

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HobbyEntity hobby = new HobbyEntity();

                hobby.setId(rs.getInt("ID"));
                hobby.setUserId(rs.getInt("USER_ID"));
                hobby.setHobbyName(rs.getString("HOBBY_NAME"));
                hobby.setHobbyIcon(rs.getString("HOBBY_ICON"));
                hobby.setDescription(rs.getString("DESCRIPTION"));

                hobbyList.add(hobby);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hobbyList;
    }

    public boolean insert(int userId, HobbyEntity hobby) {

        ConnectionManager cm = ConnectionManager.getInstance();

        StringBuffer sql = new StringBuffer();
        int count = 1;
        sql.append("INSERT INTO project_db.hobby(" + "USER_ID,");
        sql.append("hobby_name,");
        count++;
        if (hobby.getHobbyIcon() != null) {
            sql.append("HOBBY_ICON,");
            count++;
        }
        if (hobby.getDescription() != null) {
            sql.append("DESCRIPTION,");
            count++;
        }
        sql.delete(sql.length() - 1, sql.length());
        sql.append(") VALUES (");
        for (int i = 0; i < count; i++) {
            sql.append("?,");
        }
        sql.delete(sql.length() - 1, sql.length());
        sql.append(")");
        int result = 0;

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql.toString())) {

            pstmt.setInt(1, userId);
            count = 1;
            count++;
            pstmt.setString(count, hobby.getHobbyName());
            if (hobby.getHobbyIcon() != null) {
                count++;
                pstmt.setString(count, hobby.getHobbyIcon());
            }

            if (hobby.getDescription() != null) {
                count++;
                pstmt.setString(count, hobby.getDescription());
            }
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result == 0 ? false : true;
    }

	public boolean update(int userId, HobbyEntity hobby) {

        ConnectionManager cm = ConnectionManager.getInstance();

        String sql = "UPDATE project_db.hobby "
        	       + "SET"
                   + " HOBBY_NAME = ?,"
                   + " HOBBY_ICON = ?,"
                   + " DESCRIPTION = ?"
                   + " " 
                   + "WHERE USER_ID = ?";

        int result = 0;

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql.toString())) {

            pstmt.setString(1, hobby.getHobbyName());
            pstmt.setString(2, hobby.getHobbyIcon());
            pstmt.setString(3, hobby.getDescription());
            pstmt.setInt(4, userId);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result == 0 ? false : true;
	}
}