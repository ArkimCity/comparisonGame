package probono.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.qlrm.mapper.JpaResultMapper;

import lombok.extern.slf4j.Slf4j;
import probono.exception.MessageException;
import probono.exception.NotExistException;
import probono.model.dto.UserWordEntity;
import probono.model.dto.CommunityEntity;
import probono.model.dto.UserEntity;
import probono.model.util.PublicCommon;

@Slf4j

public class WorldOfWordsCRUDService {
	//------------------------------------------------------------------------------------------------
	public static void addUser(UserEntity user) throws MessageException{
		EntityManager em = PublicCommon.getEntityManger();
		log.warn("Entity Manger 생성 기록");
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(user);
			tx.commit();
			log.warn(user.getUserId() + "생성 기록");
		} catch (Exception s) {
			tx.rollback();
			throw new MessageException(s +"이미 존재하는 ID입니다 다시 시도 해주세요");
		}finally {
			em.close();
			log.warn("Entity Manger 종료 기록");
		}
	}
	 
	public static void updateUser(String userId, String nickname) throws MessageException {		
		EntityManager em = PublicCommon.getEntityManger();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			UserEntity user = em.find(UserEntity.class, userId);
			user.setNickname(nickname);
			tx.commit();
			log.warn(userId + " 업데이트 기록");
		} catch (Exception s) {
			tx.rollback();
			throw new MessageException(s +" 업데이트 오류입니다.");
		}finally {
			em.close();
			log.warn("Entity Manger 종료 기록");
		}
	}
	
	public static void deleteUser(String userId) throws SQLException, NotExistException, MessageException{
		EntityManager em = PublicCommon.getEntityManger();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try { 
			UserEntity user = em.find(UserEntity.class, userId);
			em.remove(user);
			tx.commit();
			log.warn(userId + " 삭제 기록");
		} catch (Exception s) {
			tx.rollback();
			throw new MessageException(s +"삭제 오류입니다.");
		}finally {
			em.close();
		}
	}
	
	
	//Community - CRUD
	public static void addCommunity(CommunityEntity Community) throws SQLException, MessageException{
		EntityManager em = PublicCommon.getEntityManger();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(Community);
			tx.commit();
			log.warn("Community 생성 기록");
		} catch (Exception s) {
			tx.rollback();
			throw new MessageException(s +"이미 존재하는 ID입니다 다시 시도 하세요");
		}finally {
			em.close();
		}
	}
	
	public static void deleteCommunity(String CommunityId) throws SQLException, NotExistException, MessageException{
		EntityManager em = PublicCommon.getEntityManger();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try { 
			CommunityEntity Community = em.find(CommunityEntity.class, CommunityId);
			em.remove(Community);
			tx.commit();
			log.warn(CommunityId + "삭제 기록");
		} catch (Exception s) {
			tx.rollback();
			throw new MessageException(s +"삭제 오류입니다.");
		}finally {
			em.close();
		}
	}
	
	public static CommunityEntity getCommunity(String CommunityId) throws SQLException, NotExistException{
		EntityManager em = PublicCommon.getEntityManger();
		CommunityEntity Community = em.find(CommunityEntity.class, CommunityId);
		if(Community == null){
			throw new NotExistException("검색하는 재능 수혜자가 미 존재합니다.");
		}
		log.warn(CommunityId + " 출력 기록");
		em.close();
		return Community;
	}
	
	public static ArrayList<CommunityEntity> getAllCommunities() throws SQLException, NotExistException{
		EntityManager em = PublicCommon.getEntityManger();
		List<CommunityEntity> list = new JpaResultMapper().list(em.createNativeQuery("SELECT * FROM community"), CommunityEntity.class);
		if(list.size() == 0){
			throw new NotExistException("현재 재능 기부자가 존재하지 않습니다.");
		}
		log.warn("community list 출력 기록");
		em.close();
		return (ArrayList<CommunityEntity>) list;
	}

	
	public static ArrayList<String> getUserWords(String id) throws SQLException {
		return UserWordDAO.getUserWords(id);
	}

	public static void deleteUserWord(String id, String word) throws SQLException {
		UserWordDAO.deleteUserWord(id, word);
	}

	public static void saveUserWord(String id, String userWord) throws SQLException {
		UserWordDAO.saveUserWord(id, userWord);
		
	}
}
