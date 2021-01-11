package comparisonGame.model;

import comparisonGame.model.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersDAO {
    // 새로운 사용자 추가하기(INSERT) | 시작
    public static boolean addUser(String userId, String password, String userName, String nickName)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("insert into users values(?,?,?,?)");
            pstmt.setString(1, userId);
            pstmt.setString(2, password);
            pstmt.setString(3, userName);
            pstmt.setString(4, nickName);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                return true;
            }
        } finally {
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    // 새로운 사용자 추가하기(INSERT) | 끝

    // 기존 사용자의 회원정보(닉네임) 갱신하기(UPDATE) | 시작
    public static boolean updateUser(String userId, String nickName)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("update users set nickName=? where userId=?");
            pstmt.setString(1, nickName);
            pstmt.setString(2, userId);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                return true;
            }
        } finally {
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    // 기존 사용자의 회원정보(닉네임) 갱신하기(UPDATE) | 끝

    // 기존 사용자 삭제하기(DELETE) | 시작
    public static boolean deleteUser(String userId)
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("delete from users where userId=?");
            pstmt.setString(1, userId);

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
