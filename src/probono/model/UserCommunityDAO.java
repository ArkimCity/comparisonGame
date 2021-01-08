/*
CREATE TABLE probono_project (
	   probono_project_id     		NUMBER(5) PRIMARY KEY,
	   probono_project_name 		VARCHAR2(50) NOT NULL,
       probono_id           			VARCHAR2(50) NOT NULL,       
       activist_id          				VARCHAR2(20) NOT NULL,
       receive_id           				VARCHAR2(20) NOT NULL, 
       project_content      			VARCHAR2(100) NOT NULL
);   */
package probono.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import probono.model.dto.CommunityEntity;
import probono.model.dto.UserEntity;
import probono.model.util.DBUtil;
import probono.model.util.PublicCommon;

public class UserCommunityDAO {
	
	// 프로보노 프로젝트 저장
	public static boolean addUserCommunity(CommunityEntity userCommunity) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con
					.prepareStatement("insert into userCommunity values(?, ?, ?)");
			pstmt.setString(1, userCommunity.getUserId().getUserId());
			pstmt.setString(2, userCommunity.getWord());
			pstmt.setInt(3, userCommunity.getThumbsUp());

			int result = pstmt.executeUpdate();

			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	public static boolean updateUserCommunity(int projectId, String activistId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement("update probono_project set activist_id=? where probono_project_id=?");
			pstmt.setString(1, activistId);
			pstmt.setInt(2, projectId);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	public static boolean deleteUserCommunity(int probonoProjectId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from probono_project where probono_project_id=?");
			pstmt.setInt(1, probonoProjectId);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 프로보노 프로젝트 id로 해당 프로보노프로젝트 검색
	public static CommunityEntity getUserCommunity(EntityManager em, int probonoProjectId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CommunityEntity probonoUser = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from community where probono_project_id=?");
			pstmt.setInt(1, probonoProjectId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				probonoUser = CommunityEntity.builder().postId(rset.getInt(1))
						.userId(em.find(UserEntity.class, rset.getString(2))).word(rset.getString(3)).thumbsUp(rset.getInt(4)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return probonoUser;
	}

	// 모든 프로보노 프로젝트 검색
	public static ArrayList<CommunityEntity> getAllComunities(EntityManager em) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<CommunityEntity> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from community order by postId desc");
			rset = pstmt.executeQuery();

			list = new ArrayList<CommunityEntity>();
			while (rset.next()) {
				list.add(CommunityEntity.builder().postId(rset.getInt(1))
						.userId(em.find(UserEntity.class, rset.getString(2))).word(rset.getString(3)).thumbsUp(rset.getInt(4)).build());
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}
