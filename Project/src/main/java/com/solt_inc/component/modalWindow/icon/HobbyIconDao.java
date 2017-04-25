package com.solt_inc.component.modalWindow.icon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.solt_inc.model.dao.ConnectionManager;

public class HobbyIconDao {

    public List<HobbyIconEntity> getIconList(String test) {
        List<HobbyIconEntity> iconList = new ArrayList<HobbyIconEntity>();
        HobbyIconEntity iconEntity = new HobbyIconEntity();
        iconEntity.setId(1);
        iconEntity.setIconName("SAMPLE");
        iconEntity.setIconFileName("sample.png");
        iconList.add(iconEntity);
        HobbyIconEntity iconEntity2 = new HobbyIconEntity();
        iconEntity2.setId(2);
        iconEntity2.setIconName("SAMPLE1");
        iconEntity2.setIconFileName("sample1.jpg");
        iconList.add(iconEntity2);

        HobbyIconEntity iconEntity3 = new HobbyIconEntity();
        iconEntity3.setId(3);
        iconEntity3.setIconName("SAMPLE2");
        iconEntity3.setIconFileName("sample2.png");
        iconList.add(iconEntity3);
        HobbyIconEntity iconEntity4 = new HobbyIconEntity();
        iconEntity4.setId(4);
        iconEntity4.setIconName("SAMPLE3");
        iconEntity4.setIconFileName("sample3.png");
        iconList.add(iconEntity4);

        return iconList;
    }

    public List<HobbyIconEntity> getIconList() {

        List<HobbyIconEntity> iconList = new ArrayList<HobbyIconEntity>();
        ConnectionManager cm = ConnectionManager.getInstance();
        String sql = "SELECT"
                + " *"
                + " "
                + "FROM"
                + " hobby_icon";

        try (Connection con = cm.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HobbyIconEntity iconEntity = new HobbyIconEntity();

                iconEntity.setId(rs.getInt("ID"));
                iconEntity.setIconName(rs.getString("NAME"));
                iconEntity.setIconFileName(rs.getString("FILENAME"));

                iconList.add(iconEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return iconList;
    }

}
