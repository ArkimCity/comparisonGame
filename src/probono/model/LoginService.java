package probono.model;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import probono.model.dto.UserEntity;
import probono.model.util.PublicCommon;

@Slf4j

public class LoginService {
	
	public static Boolean login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EntityManager em = PublicCommon.getEntityManger();
		String id = request.getParameter("id");
		String inputPassword = request.getParameter("pw");
		String password = null;
		Boolean result = false;
		if (em.find(UserEntity.class, id)!=null) {
			
			try {
				password = em.find(UserEntity.class, id).getPassword();
			} catch (Exception e) {
				e.printStackTrace();
				log.warn("loginservice에서 엔티티 매니저 관련 오류 기록");
			} finally {
				em.close();
			}
			if (password.equals(inputPassword)) {
				session.setAttribute("id", id);
				log.warn(id + " 로그인 성공 기록");
				result = true;
			} else {
				log.warn(id + " 로 틀린 비밀번호시도 기록");
				request.setAttribute("errorMsg", "로그인에 실패하였습니다. 아이디 혹은 비밀번호를 다시 확인해 주십시오");
			}
		}else {
			log.warn(id + " 로 없는 아이디 로그인 시도 기록");
			request.setAttribute("errorMsg", "로그인에 실패하였습니다. 아이디 혹은 비밀번호를 다시 확인해 주십시오");
		}
		return result;
	}

}
