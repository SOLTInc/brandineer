package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.solt_inc.model.entity.SkillSetImageEntity;

public class SkillsetImageDao {

    public List<SkillSetImageEntity> getAllSkillSetImages() {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM skillSet_image";

        List<SkillSetImageEntity> skillSetImageList = new ArrayList<SkillSetImageEntity>();

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                SkillSetImageEntity skillSetImage = new SkillSetImageEntity();

                skillSetImage.setId(rs.getInt("ID"));
                skillSetImage.setSkillSetId(rs.getInt("SKILLSET_ID"));
                skillSetImage.setImageName(rs.getString("IMAGE_NAME"));

                skillSetImageList.add(skillSetImage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skillSetImageList;
    }

    public List<SkillSetImageEntity> getSkillSetImage(int skillSetId) {

        List<SkillSetImageEntity> skillSetImageList = new ArrayList<SkillSetImageEntity>();
        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM skillSet_image WHERE SKILLSET_ID = ?";

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, skillSetId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                SkillSetImageEntity skillSetImage = new SkillSetImageEntity();
                skillSetImage.setId(rs.getInt("ID"));
                skillSetImage.setSkillSetId(rs.getInt("SKILLSET_ID"));
                skillSetImage.setImageName(rs.getString("IMAGE_NAME"));

                skillSetImageList.add(skillSetImage);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skillSetImageList;
    }

    public boolean insert(int userId, SkillSetImageEntity skillSetImage) {

        int result = 0;
        ConnectionManager cm = ConnectionManager.getInstance();

        String sql = "INSERT INTO project_db.skillSet_image(SKILLSET_ID, IMAGE_NAME) VALUE( ? , ? )";

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql.toString())) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, skillSetImage.getImageName());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result == 0 ? false : true;
    }
}