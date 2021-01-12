package comparison.model;

import javax.persistence.EntityManager;

import comparison.model.dto.CategoryEntity;
import comparison.model.dto.GameDataEntity;
import comparison.model.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameDataDAO {
    // 모든 게임 목록 조회하기(SELECT) | 시작
    public static ArrayList<GameDataEntity> getAllGameData(EntityManager em)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ArrayList<GameDataEntity> list = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("select * from gameData order by dataId desc");
            rset = pstmt.executeQuery();

            list = new ArrayList<GameDataEntity>();

            while (rset.next()) {
                list.add(GameDataEntity.builder().dataId(rset.getInt(1))
                        .category(em.find(CategoryEntity.class, rset.getString(2)))
                        .title(rset.getString(3))
                        .imgsrc(rset.getString(4))
                        .parameter(rset.getString(5)).build());
            }
        } finally {
            DBUtil.close(con, pstmt, rset);
        }
        return list;
    }
    // 모든 게임 목록 조회하기(SELECT) | 끝

    
    
    public static ArrayList<GameDataEntity> getGameData(String title, EntityManager em) throws SQLException {
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rset = null;
            ArrayList<GameDataEntity> list = null;
            try {
                con = DBUtil.getConnection();
                pstmt = con.prepareStatement("select * from gameData where category=?");
                pstmt.setString(1, title);
                rset = pstmt.executeQuery();

                list = new ArrayList<GameDataEntity>();

                while (rset.next()) {
                    list.add(GameDataEntity.builder().dataId(rset.getInt(1))
                            .category(em.find(CategoryEntity.class, rset.getString(2)))
                            .title(rset.getString(3))
                            .imgsrc(rset.getString(4))
                            .parameter(rset.getString(5)).build());
                }
            } finally {
                DBUtil.close(con, pstmt, rset);
            }
            return list;
    }
    
    
    
    // 새로운 게임 추가하기(INSERT) | 시작
    public static boolean addGameData(int dataId, String title, String imgsrc, String parameter)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("insert into gameData values(?,?,?,?)");
            pstmt.setInt(1, dataId);
            pstmt.setString(2, title);
            pstmt.setString(3, imgsrc);
            pstmt.setString(4, parameter);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                return true;
            }
        } finally {
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    // 새로운 게임 추가하기(INSERT) | 끝

    // 기존 게임의 정보(제목, 이미지, 파라미터) 갱신하기(UPDATE) | 시작
    public static boolean updateGameData(int dataId, String title, String imgsrc, String parameter)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("update gameData set title=? and imgsrc=? and parameter=? where dataId=?");
            pstmt.setString(1, title);
            pstmt.setString(2, imgsrc);
            pstmt.setString(3, parameter);
            pstmt.setInt(4, dataId);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                return true;
            }
        } finally {
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    // 기존 게임의 정보(제목, 이미지, 파라미터) 갱신하기(UPDATE) | 끝

    // 기존 게임(dataId; PK) 삭제하기(DELETE) | 시작
    public static boolean deleteGameData(int dataId)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("delete from gameData where dataId=?");
            pstmt.setInt(1, dataId);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                return true;
            }
        } finally {
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    // 기존 게임(dataId; PK) 삭제하기(DELETE) | 끝
}
