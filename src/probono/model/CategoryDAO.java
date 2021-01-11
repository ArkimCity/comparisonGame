package comparisonGame.model;

import comparisonGame.model.dto.CategoryEntity;
import comparisonGame.model.dto.GameDataEntity;
import comparisonGame.model.util.DBUtil;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {
    // 새로운 카테고리 추가하기(INSERT) | 시작
    public static boolean addCategory(String category)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("insert into category values(?)");
            pstmt.setString(1, category);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                return true;
            }
        } finally {
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    // 새로운 카테고리 추가하기(INSERT) | 끝

    // 기존 카테고리 삭제하기(DELETE) | 시작
    public static boolean deleteCategory(String category)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("delete from category where category=?");
            pstmt.setString(1, category);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                return true;
            }
        } finally {
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    // 기존 사용자 삭제하기(DELETE) | 끝
}
