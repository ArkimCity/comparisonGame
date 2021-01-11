package comparisonGame.model.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.extern.slf4j.Slf4j;

@Slf4j

public class PublicCommon {
	private static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("oracleDBUse");
		log.warn("DB 접속 기록");
	}
	
	public static EntityManager getEntityManager() {
		log.warn("Entity Manager 생성 기록");
		return emf.createEntityManager();
	}
	
	public static void close() {
		emf.close();
		log.warn("DB 접속 해제 기록");
	}
}
