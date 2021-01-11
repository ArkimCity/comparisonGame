package comparisonGame.model;

import comparisonGame.model.dto.UsersEntity;
import comparisonGame.model.util.PublicCommon;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginService {
    public static Boolean login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        EntityManager em = PublicCommon.getEntityManager();
        String id = request.getParameter("id");
        String inputPassword = request.getParameter("pw");
        String password = null;
        boolean result = false;

        if (em.find(UsersEntity.class, id) != null) {
            try {
                password = em.find(UsersEntity.class, id).getPassword();
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("loginService | EntityManager 관련 오류 기록");
            } finally {
                em.close();
            }
            if (password.equals(inputPassword)) {
                session.setAttribute("id", id);
                log.warn(id + " | 로그인 성공 기록");
                result = true;
            } else {
                log.warn(id + " | '잘못된 비밀번호' | 로그인 시도 기록");
                request.setAttribute("errorMsg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
            }
        } else {
            log.warn(id + " | '가입하지 않은 아이디' | 로그인 시도 기록");
            request.setAttribute("errorMsg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
        }
        return result;
    }
}
