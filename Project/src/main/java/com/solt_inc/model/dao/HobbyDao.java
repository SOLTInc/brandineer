package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.IModel;

import com.solt_inc.model.entity.HobbyEntity;

public class HobbyDao {

    public List<HobbyEntity> getHobby(int id) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
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
                hobby.setDescription(rs.getString("DESCRIPTION"));

                hobbyList.add(hobby);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hobbyList;
    }

    public List<HobbyEntity> getUserHobby(IModel<Integer> userId) {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM hobby WHERE USER_ID = ?";

        List<HobbyEntity> hobbyList = new ArrayList<HobbyEntity>();

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, userId.getObject());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HobbyEntity hobby = new HobbyEntity();

                hobby.setId(rs.getInt("ID"));
                hobby.setUserId(rs.getInt("USER_ID"));
                hobby.setHobbyName(rs.getString("HOBBY_NAME"));
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
        if (hobby.getHobbyName() != null) {
            sql.append("hobby_name,");
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
            if (hobby.getHobbyName() != null) {
                count++;
                pstmt.setString(count, hobby.getHobbyName());
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
}