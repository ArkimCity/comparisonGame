package comparisonGame.model;

import comparisonGame.model.dto.CategoryEntity;
import comparisonGame.model.dto.ScoreEntity;
import comparisonGame.model.dto.UsersEntity;
import comparisonGame.model.util.DBUtil;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScoreDAO {
    // 모든 점수(리더보드) 조회하기(SELECT) | 시작
    public static ArrayList<ScoreEntity> getAllScores(EntityManager em)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ArrayList<ScoreEntity> list = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("select * from score order by scoreId desc");
            rset = pstmt.executeQuery();
            
            list = new ArrayList<ScoreEntity>();

            while (rset.next()) {
                list.add(ScoreEntity.builder().scoreId(rset.getInt(1))
                        .userId(em.find(UsersEntity.class, rset.getString(2)))
                        .category(em.find(CategoryEntity.class, rset.getString(3)))
                        .score(rset.getInt(4)).build());
            }
        } finally {
            DBUtil.close(con, pstmt, rset);
        }
        return list;
    }
    // 모든 점수(리더보드) 데이터 조회하기(SELECT) | 끝

    // 새로운 점수(score) 데이터 추가하기(INSERT) | 시작
    public static boolean addScore(int scoreId, int score)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("insert into score values(?,?)");
            pstmt.setInt(1, scoreId);
            pstmt.setInt(2, score);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                return true;
            }
        } finally {
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    // 새로운 점수(score) 데이터 추가하기(INSERT) | 끝

    // 기존 점수(score) 데이터와 순위(scoreId) 갱신하기(UPDATE) | 시작
    public static boolean updateScore(int scoreId, int score)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("update score set score=? where scoreId=?");
            pstmt.setInt(1, score);
            pstmt.setInt(2, scoreId);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                return true;
            }
        } finally {
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    // 기존 점수(score) 데이터와 순위(scoreId) 갱신하기(UPDATE) | 시작

    // 기존 점수(score) 데이터 삭제하기(DELETE) | 시작
    public static boolean deleteScore(int scoreId)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("delete from score where scoreId=?");
            pstmt.setInt(1, scoreId);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                return true;
            }
        } finally {
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    // 기존 점수(score) 데이터 삭제하기(DELETE) | 끝
}
