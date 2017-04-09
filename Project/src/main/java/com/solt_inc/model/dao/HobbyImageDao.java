package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.solt_inc.model.entity.HobbyImageEntity;

public class HobbyImageDao {

    public List<HobbyImageEntity> getAllHobbyImages() {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM hobby_image";

        List<HobbyImageEntity> hobbyImageList = new ArrayList<HobbyImageEntity>();

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HobbyImageEntity hobbyImage = new HobbyImageEntity();

                hobbyImage.setId(rs.getInt("ID"));
                hobbyImage.setHobbyId(rs.getInt("HOBBY_ID"));
                hobbyImage.setImageName(rs.getString("IMAGE_NAME"));

                hobbyImageList.add(hobbyImage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hobbyImageList;
    }

    public List<HobbyImageEntity> getHobbyImage(int hobbyId) {

        List<HobbyImageEntity> hobbyImageList = new ArrayList<HobbyImageEntity>();
        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM hobby_image WHERE HOBBY_ID = ?";

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, hobbyId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                HobbyImageEntity hobbyImage = new HobbyImageEntity();
                hobbyImage.setId(rs.getInt("ID"));
                hobbyImage.setHobbyId(rs.getInt("HOBBY_ID"));
                hobbyImage.setImageName(rs.getString("IMAGE_NAME"));

                hobbyImageList.add(hobbyImage);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hobbyImageList;
    }

    public boolean insert(int userId, HobbyImageEntity hobbyImage) {

        int result = 0;
        ConnectionManager cm = ConnectionManager.getInstance();

        String sql = "INSERT INTO project_db.hobby_image(HOBBY_ID, IMAGE_NAME) VALUE( ? , ? )";

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql.toString())) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, hobbyImage.getImageName());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result == 0 ? false : true;
    }
}