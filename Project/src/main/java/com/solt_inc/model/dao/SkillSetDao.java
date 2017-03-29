package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.IModel;

import com.solt_inc.model.entity.SkillSetEntity;

public class SkillSetDao {

    public List<SkillSetEntity> getAllSkillSets() {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM skillset";

        List<SkillSetEntity> skillList = new ArrayList<SkillSetEntity>();

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                SkillSetEntity skill = new SkillSetEntity();

                skill.setId(rs.getInt("ID"));
                skill.setUserId(rs.getInt("USER_ID"));
                skill.setSqlProjectStart(rs.getDate("PROJECT_START"));
                skill.setSqlProjectEnd(rs.getDate("PROJECT_END"));
                skill.setProjectName(rs.getString("PROJECT_NAME"));
                skill.setProcessStart(rs.getInt("PROCESS_START"));
                skill.setProcessEnd(rs.getInt("PROCESS_END"));
                skill.setProgrammingLanguage(rs.getString("PROGRAMMING_LANGUAGE"));
                skill.setDB(rs.getString("DB"));
                skill.setIDE(rs.getString("IDE"));

                skillList.add(skill);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skillList;
    }

    public List<SkillSetEntity> getSkillSet(IModel<Integer> userId) {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM skillset WHERE USER_ID = ?";

        List<SkillSetEntity> skillList = new ArrayList<SkillSetEntity>();

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, userId.getObject());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                SkillSetEntity skill = new SkillSetEntity();

                skill.setId(rs.getInt("ID"));
                skill.setUserId(rs.getInt("USER_ID"));
                skill.setSqlProjectStart(rs.getDate("PROJECT_START"));
                skill.setSqlProjectEnd(rs.getDate("PROJECT_END"));
                skill.setProjectName(rs.getString("PROJECT_NAME"));
                skill.setProcessStart(rs.getInt("PROCESS_START"));
                skill.setProcessEnd(rs.getInt("PROCESS_END"));
                skill.setProgrammingLanguage(rs.getString("PROGRAMMING_LANGUAGE"));
                skill.setDB(rs.getString("DB"));
                skill.setIDE(rs.getString("IDE"));

                skillList.add(skill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skillList;
    }

    public boolean insert(int userId, SkillSetEntity skillSet) {

        // UserEntity skillSet = skillSetModel.getObject();
        ConnectionManager cm = ConnectionManager.getInstance();

        StringBuffer sql = new StringBuffer();
        int count = 1;
        sql.append("INSERT INTO project_db.skillSet(" + "USER_ID,");
        if (skillSet.getProjectStart() != null) {
            sql.append("PROJECT_START,");
            count++;
        }
        if (skillSet.getProjectEnd() != null) {
            sql.append("PROJECT_END,");
            count++;
        }
        if (skillSet.getProjectName() != null) {
            sql.append("PROJECT_NAME,");
            count++;
        }
        if (skillSet.getProjectDescription() != null) {
            sql.append("PROJECT_DESCRIPTION,");
            count++;
        }
        if (skillSet.getDB() != null) {
            sql.append("DB,");
            count++;
        }
        if (skillSet.getIDE() != null) {
            sql.append("IDE,");
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
            if (skillSet.getProjectStart() != null) {
                count++;
                pstmt.setDate(count, skillSet.getSqlProjectStart());
            }
            if (skillSet.getProjectEnd() != null) {
                count++;
                pstmt.setDate(count, skillSet.getSqlProjectEnd());
            }
            if (skillSet.getProjectName() != null) {
                count++;
                pstmt.setString(count, skillSet.getProjectName());
            }
            if (skillSet.getProjectDescription() != null) {
                count++;
                pstmt.setString(count, skillSet.getProjectDescription());
            }
            if (skillSet.getDB() != null) {
                count++;
                pstmt.setString(count, skillSet.getDB());
            }
            if (skillSet.getIDE() != null) {
                count++;
                pstmt.setString(count, skillSet.getIDE());
            }

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result == 0 ? false : true;
    }

}