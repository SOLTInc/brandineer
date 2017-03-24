package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.solt_inc.model.entity.DevelopmentProcessEntity;

public class DevelopmentProcessDao {

    public List<DevelopmentProcessEntity> getProcessList() {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM development_process";

        List<DevelopmentProcessEntity> processList = new ArrayList<DevelopmentProcessEntity>();

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                DevelopmentProcessEntity process = new DevelopmentProcessEntity();

                process.setId(rs.getInt("ID"));
                process.setProcessName(rs.getString("PROCESS_NAME"));

                processList.add(process);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return processList;
    }

    public static DevelopmentProcessEntity getProcess(int processStart) {

        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT * FROM development_process WHERE = ?";

        DevelopmentProcessEntity processEntity = new DevelopmentProcessEntity();

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                processEntity.setId(rs.getInt("ID"));
                processEntity.setProcessName(rs.getString("PROCESS_NAME"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return processEntity;
    }

}
