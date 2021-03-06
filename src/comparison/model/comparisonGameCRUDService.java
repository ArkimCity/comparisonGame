package comparison.model;

import lombok.extern.slf4j.Slf4j;
import org.qlrm.mapper.JpaResultMapper;

import comparison.exception.MessageException;
import comparison.exception.NotExistException;
import comparison.model.dto.CategoryEntity;
import comparison.model.dto.GameDataEntity;
import comparison.model.dto.ScoreEntity;
import comparison.model.dto.UsersEntity;
import comparison.model.util.PublicCommon;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class comparisonGameCRUDService {
    // ========================= UsersEntity | CRUD | 시작 =========================
    // 새로운 사용자 추가하기 | 시작
    public static void userInsert(UsersEntity user)
            throws MessageException {
        EntityManager em = PublicCommon.getEntityManager();
        log.warn("EntityManager 생성 기록");
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.persist(user);
            tx.commit();
            log.warn(user.getUserId() + " | 생성 기록");
        } catch (Exception s) {
            tx.rollback();
            throw new MessageException(s + " | 이미 사용중이거나 탈퇴한 아이디입니다.");
        } finally {
            em.close();
            log.warn("EntityManger 종료 기록");
        }
    }
    // 새로운 사용자 추가하기 | 끝

    // 기존 사용자의 회원정보(닉네임) 갱신하기 | 시작
    public static void updateUser(String userId, String nickName)
            throws MessageException {
        EntityManager em = PublicCommon.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            UsersEntity user = em.find(UsersEntity.class, userId);
            user.setNickName(nickName);
            em.persist(user);
            tx.commit();
            log.warn(userId + " | 업데이트 기록");
        } catch (Exception s) {
            tx.rollback();
            throw new MessageException(s + " | 업데이트 오류입니다.");
        } finally {
            em.close();
            log.warn("EntityManager 종료 기록");
        }
    }
    // 기존 사용자의 회원정보(닉네임) 갱신하기 | 끝

    // 기존 사용자 삭제하기 | 시작
    public static void deleteUser(String userId)
            throws SQLException, NotExistException, MessageException {
        EntityManager em = PublicCommon.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            UsersEntity user = em.find(UsersEntity.class, userId);
            em.remove(user);
            tx.commit();
            log.warn(userId + " | 삭제 기록");
        } catch (Exception s) {
            tx.rollback();
            throw new MessageException(s + " | 삭제 오류입니다.");
        } finally {
            em.close();
        }
    }
    // 기존 사용자 삭제하기 | 끝
    // ========================= UsersEntity | CRUD | 끝 =========================

    // ========================= ScoreEntity | CRUD | 시작 =========================
    // 모든 점수(리더보드) 조회하기 | 시작
    public static ArrayList<ScoreEntity> getAllScores()
            throws SQLException, NotExistException {
        EntityManager em = PublicCommon.getEntityManager();
        List<ScoreEntity> list = new JpaResultMapper()
                .list(em.createNativeQuery("SELECT * FROM score"), ScoreEntity.class);

        if (list.size() == 0) {
            throw new NotExistException("게임을 플레이하시면 리더보드에 순위가 기재됩니다.");
        }
        log.warn("ScoreList 출력 기록");
        em.close();
        return (ArrayList<ScoreEntity>) list;
    }
    // 모든 점수(리더보드) 조회하기 | 끝

    // 새로운 점수(score) 데이터 추가하기 | 시작
    public static void addUser(ScoreEntity score)
            throws MessageException {
        EntityManager em = PublicCommon.getEntityManager();
        log.warn("EntityManager 생성 기록");
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.persist(score);
            tx.commit();
            log.warn(score.getScoreId() + " | 생성 기록");
        } catch (Exception s) {
            tx.rollback();
            throw new MessageException(s + " | 점수가 올바르지 않거나 다른 오류가 발생했습니다.");
        } finally {
            em.close();
            log.warn("EntityManger 종료 기록");
        }
    }
    // 새로운 점수(score) 데이터 추가하기 | 끝

    // 기존 점수(score) 데이터와 순위(scoreId) 갱신하기 | 시작
    public static void updateScore(int scoreId, int score)
            throws MessageException {
        EntityManager em = PublicCommon.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            ScoreEntity scores = em.find(ScoreEntity.class, scoreId);
            scores.setScore(score);
            em.persist(scores);
            tx.commit();
            log.warn(scoreId + " | 업데이트 기록");
        } catch (Exception s) {
            tx.rollback();
            throw new MessageException(s + " | 업데이트 오류입니다.");
        } finally {
            em.close();
            log.warn("EntityManager 종료 기록");
        }
    }
    // 기존 점수(score) 데이터와 순위(scoreId) 갱신하기 | 끝

    // 기존 점수 데이터 삭제하기 | 시작
    public static void deleteScore(int scoreId)
            throws SQLException, NotExistException, MessageException {
        EntityManager em = PublicCommon.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            ScoreEntity score = em.find(ScoreEntity.class, scoreId);
            em.remove(score);
            tx.commit();
            log.warn(scoreId + " | 삭제 기록");
        } catch (Exception s) {
            tx.rollback();
            throw new MessageException(s + " | 삭제 오류입니다.");
        } finally {
            em.close();
        }
    }
    // 기존 점수 데이터 삭제하기 | 끝
    // ========================= ScoreEntity | CRUD | 끝 =========================

    // ========================= CategoryEntity | CRUD | 시작 =========================
    // 새로운 카테고리 추가하기 | 시작
    public static void addCategory(CategoryEntity category)
            throws MessageException {
        EntityManager em = PublicCommon.getEntityManager();
        log.warn("EntityManager 생성 기록");
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.persist(category);
            tx.commit();
            log.warn(category.getCategory() + " | 생성 기록");
        } catch (Exception s) {
            tx.rollback();
            throw new MessageException(s + " | 카테고리가 올바르지 않거나 다른 오류가 발생했습니다.");
        } finally {
            em.close();
            log.warn("EntityManger 종료 기록");
        }
    }
    // 새로운 카테고리 추가하기 | 끝

    // 기존 카테고리 삭제하기 | 시작
    public static void deleteCategory(String category)
            throws SQLException, NotExistException, MessageException {
        EntityManager em = PublicCommon.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            CategoryEntity categories = em.find(CategoryEntity.class, category);
            em.remove(categories);
            tx.commit();
            log.warn(category + " | 삭제 기록");
        } catch (Exception s) {
            tx.rollback();
            throw new MessageException(s + " | 삭제 오류입니다.");
        } finally {
            em.close();
        }
    }
    // 기존 카테고리 삭제하기 | 끝
    // ========================= CategoryEntity | CRUD | 끝 =========================

    // ========================= GameDataEntity | CRUD | 시작 =========================
    // 모든 게임 데이타 조회하기 | 시작
    public static ArrayList<GameDataEntity> getAllGameData()
            throws SQLException, NotExistException {
        EntityManager em = PublicCommon.getEntityManager();
        List<GameDataEntity> list = new JpaResultMapper()
                .list(em.createNativeQuery("SELECT * FROM gameData"), GameDataEntity.class);

        if (list.size() == 0) {
            throw new NotExistException("게임을 추가하시면 게임 목록에 기재됩니다.");
        }
        log.warn("GameDataList 출력 기록");
        em.close();
        return (ArrayList<GameDataEntity>) list;
    }
    // 모든 게임 목록 조회하기 | 끝
    
    public static String getFirstGameImage(String Category)
    		throws SQLException, NotExistException {
    	EntityManager em = PublicCommon.getEntityManager();
    	System.out.println("SELECT * FROM gameData where category=" + "'" + Category+ "'");
    	
    	
    	
    	List<String> list = new JpaResultMapper()
    			.list(em.createNativeQuery("SELECT IMGSRC FROM gameData where rownum=1 category=" + "'" + Category+ "'"), String.class);
    	if (list.size() == 0) {
    		throw new NotExistException("게임을 추가하시면 게임 목록에 기재됩니다.");
    	}
    	log.warn("GameDataList 출력 기록");
    	em.close();
    	return list.get(0);
    }
    
    
    // 모든 게임 데이타 조회하기 | 시작
    public static ArrayList<GameDataEntity> getGameData(String Category)
    		throws SQLException, NotExistException {
    	EntityManager em = PublicCommon.getEntityManager();
    	ArrayList<GameDataEntity> list = GameDataDAO.getGameData(Category, em);
    	if (list.size() == 0) {
    		throw new NotExistException("게임을 추가하시면 게임 목록에 기재됩니다.");
    	}
    	log.warn("GameDataList 출력 기록");
    	em.close();
    	return list;
    }
    // 모든 게임 목록 조회하기 | 끝
    
    // 새로운 게임 추가하기 | 시작
    public static void addGameData(GameDataEntity gameData)
            throws MessageException {
        EntityManager em = PublicCommon.getEntityManager();
        log.warn("EntityManager 생성 기록");
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.persist(gameData);
            tx.commit();
            log.warn(gameData.getDataId() + " | 생성 기록");
        } catch (Exception s) {
            tx.rollback();
            throw new MessageException(s + " | 게임 데이터가 올바르지 않거나 다른 오류가 발생했습니다.");
        } finally {
            em.close();
            log.warn("EntityManger 종료 기록");
        }
    }
    // 새로운 게임 추가하기 | 끝

    // 기존 게임의 정보(제목, 이미지, 파라미터) 갱신하기 | 시작
    public static void updateGameData(int dataId, String title, String imgsrc, String parameter)
            throws MessageException {
        EntityManager em = PublicCommon.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            GameDataEntity gameData = em.find(GameDataEntity.class, dataId);
            gameData.setTitle(title);
            gameData.setImgsrc(imgsrc);
            gameData.setParameter(parameter);
            tx.commit();
            log.warn(dataId + " | 업데이트 기록");
        } catch (Exception s) {
            tx.rollback();
            throw new MessageException(s + " | 업데이트 오류입니다.");
        } finally {
            em.close();
            log.warn("EntityManager 종료 기록");
        }
    }
    // 기존 게임의 정보(제목, 이미지, 파라미터) 갱신하기 | 끝

    // 기존 게임(dataId; PK) 삭제하기 | 시작
    public static void deleteGameData(int dataId)
            throws SQLException, NotExistException, MessageException {
        EntityManager em = PublicCommon.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            GameDataEntity gameData = em.find(GameDataEntity.class, dataId);
            em.remove(gameData);
            tx.commit();
            log.warn(dataId + " | 삭제 기록");
        } catch (Exception s) {
            tx.rollback();
            throw new MessageException(s + " | 삭제 오류입니다.");
        } finally {
            em.close();
        }
    }
    // 기존 게임(dataId; PK) 삭제하기 | 끝
    // ========================= GameDataEntity | CRUD | 끝 =========================

	public static ArrayList<String> getCategories()
            throws SQLException, NotExistException, Exception {
		ArrayList<String> list = null;
        EntityManager em = PublicCommon.getEntityManager();
        try {
        	list = CategoryDAO.getCategories();
		} catch (Exception e) {
			e.printStackTrace();
		}
        log.warn("getCategories 출력 기록");
        em.close();
        return (ArrayList<String>) list;
	}
}
