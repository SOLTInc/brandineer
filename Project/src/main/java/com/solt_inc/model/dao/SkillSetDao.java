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

    public int getSkillSetId(String skillSetName) {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT"
        		+ " ID"
        		+ " "
        		+ "FROM"
        		+ " skillset"
        		+ " "
        		+ "WHERE"
        		+ " PROJECT_NAME = ?"
        		+ " and "
        		+ " (DELETE_FLG != 1"
        		+ " or"
        		+ " DELETE_FLG is null)"
        		+ " "
        		+ "ORDER BY"
        		+ " DATA_MODEFIED DESC";

        int skillSetId = -1;

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, skillSetName);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                skillSetId = rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skillSetId;
    }

    public List<SkillSetEntity> getAllSkillSets() {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT"
        		+ " *"
        		+ " "
        		+ "FROM"
        		+ " skillset"
        		+ " "
        		+ "WHERE"
        		+ " (DELETE_FLG != 1"
        		+ " or"
        		+ " DELETE_FLG is null)";

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

    public List<SkillSetEntity> getSkillSet(int userId) {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT"
        		+ " *"
        		+ " "
        		+ "FROM"
        		+ " skillset"
        		+ " "
        		+ "WHERE"
        		+ " USER_ID = ?"
        		+ " and "
        		+ " (DELETE_FLG != 1"
        		+ " or"
        		+ " DELETE_FLG is null)";

        List<SkillSetEntity> skillList = new ArrayList<SkillSetEntity>();

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                SkillSetEntity skill = new SkillSetEntity();

                skill.setId(rs.getInt("ID"));
                skill.setUserId(rs.getInt("USER_ID"));
                skill.setSqlProjectStart(rs.getDate("PROJECT_START"));
                skill.setSqlProjectEnd(rs.getDate("PROJECT_END"));
                skill.setProjectName(rs.getString("PROJECT_NAME"));
                skill.setProjectDescription(rs.getString("PROJECT_DESCRIPTION"));
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
        if (skillSet.getProcessStart() != -1) {
            sql.append("PROCESS_START,");
            count++;
        }
        if (skillSet.getProcessEnd() != -1) {
            sql.append("PROCESS_END,");
            count++;
        }
        if (skillSet.getProgrammingLanguage() != null) {
            sql.append("PROGRAMMING_LANGUAGE,");
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
            if (skillSet.getProcessStart() != -1) {
                count++;
                pstmt.setInt(count, skillSet.getProcessStart());
            }
            if (skillSet.getProcessEnd() != -1) {
                count++;
                pstmt.setInt(count, skillSet.getProcessEnd());
            }
            if (skillSet.getProgrammingLanguage() != null) {
                count++;
                pstmt.setString(count, skillSet.getProgrammingLanguage());
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

	public boolean update(int skillsetId, SkillSetEntity skillSet) {
	
        // UserEntity skillSet = skillSetModel.getObject();
        ConnectionManager cm = ConnectionManager.getInstance();

        String sql = "UPDATE project_db.skillSet "
        		+ "SET"
                + " PROJECT_START = ?,"
                + " PROJECT_END = ?,"
                + " PROJECT_NAME = ?,"
                + " PROJECT_DESCRIPTION = ?,"
                + " PROCESS_START = ?,"
                + " PROCESS_END = ?,"
                + " PROGRAMMING_LANGUAGE = ?,"
                + " DB = ?,"
                + " IDE = ?,"
                + " DELETE_FLG = 0"
                + " "
                + "WHERE ID = ?";

        int result = 0;

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setDate(1, skillSet.getSqlProjectStart());
            pstmt.setDate(2, skillSet.getSqlProjectEnd());
            pstmt.setString(3, skillSet.getProjectName());
            pstmt.setString(4, skillSet.getProjectDescription());
            pstmt.setInt(5, skillSet.getProcessStart());
            pstmt.setInt(6, skillSet.getProcessEnd());
            pstmt.setString(7, skillSet.getProgrammingLanguage());
            pstmt.setString(8, skillSet.getDB());
            pstmt.setString(9, skillSet.getIDE());
            pstmt.setInt(10, skillsetId);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result == 0 ? false : true;
	}

	public boolean delete(int userId) {
	        // UserEntity skillSet = skillSetModel.getObject();
        ConnectionManager cm = ConnectionManager.getInstance();

        String sql = "UPDATE project_db.skillSet "
        		+ "SET"
                + " DELETE_FLG = 1"
                + " "
                + "WHERE USER_ID = ?";

        int result = 0;

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result == 0 ? false : true;
	}
}