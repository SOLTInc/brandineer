package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.IModel;

import com.solt_inc.model.entity.HobbyEntity;
import com.solt_inc.model.entity.SkillSetEntity;

public class SkillSetDao {

    public List<HobbyEntity> getHobby(int id) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    public List<SkillSetEntity> getSkillSet() {
        
        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM skill";
        
        List<SkillSetEntity> skillList = new ArrayList<SkillSetEntity>();
        
        try(Connection con = cm.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                
                SkillSetEntity skill = new SkillSetEntity();
                
                skill.setSkillName(rs.getString("SKILL_NAME"));
                skill.setSkillImage(rs.getString("SKILL_IMAGE_PATH"));
                
                skillList.add(skill);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skillList;
    }
    
    public List<SkillSetEntity> getUserSkillSet(IModel<Integer> userId) {
        
        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM skill WHERE USER_ID = ?";
        
        List<SkillSetEntity> skillList = new ArrayList<SkillSetEntity>();
        
        try(Connection con = cm.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, userId.getObject());
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                SkillSetEntity skill = new SkillSetEntity();
                
                skill.setSkillName(rs.getString("SKILL_NAME"));
                skill.setSkillImage(rs.getString("SKILL_IMAGE_PATH"));
                
                skillList.add(skill);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return skillList;
    }
}