package comparisonGame.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comparisonGame.model.Crawler;
import comparisonGame.model.JsoupCrawlNaverRestaurants;
import comparisonGame.model.LoginService;
import comparisonGame.model.comparisonGameCRUDService;
import comparisonGame.model.dto.CategoryEntity;
import comparisonGame.model.dto.GameDataEntity;
import comparisonGame.model.dto.UsersEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@WebServlet("/comparisonGame")
public class comparisonGameController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 선생님 코멘트 - 컨트롤러의 현재 엔티티매니저는 컨트롤러에서 사용하지 않는 것이 좋다. 추후 프로젝트 진행시에는 컨트롤러가 아니라
		// 서비스에서 사용해야함
		String command = request.getParameter("command");
		try {
			if (command.equals("login")) {
				login(request, response);
			} else if (command.equals("logout")) {
				logout(request, response);
			} else if (command.equals("userInsert")) {
				userInsert(request, response);
			} else if (command.equals("foodWorldCup")) {// 음식 월드컵 모듈과 연결
				foodWorldCup(request, response);
			} else if (command.equals("getWorldCupList")) {// 음식 월드컵 모듈과 연결
				getWorldCupList(request, response);
//			}else if(command.equals("get" +
//					"")){//음식 월드컵 모듈과 연결
//				getWorldCupList(request, response); 
			} else if (command.equals("getWorldCup")) {// 음식 월드컵 모듈과 연결
				getWorldCup(request, response);
			} else {
				request.setAttribute("errorMsg", "잘못된 명령입니다. 다시 시도해 주십시오");
				request.getRequestDispatcher("showError.jsp").forward(request, response);
			}

		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	private void getWorldCup(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, Exception {
		String url = "showError.jsp";
		ArrayList<String> results = new ArrayList<String>();
		String category = request.getParameter("category");
		try {
			ArrayList<GameDataEntity> gamedatas = comparisonGameCRUDService.getGameData(category);
			for (GameDataEntity g : gamedatas) {
				HashMap<String, String> minimap = new HashMap<String, String>();
				minimap.put("\"title\"", "\"" + g.getTitle() + "\"");
				minimap.put("\"imgsrc\"", "\"" + g.getImgsrc().replace("\"", "'") + "\"");
				minimap.put("\"parameter\"", "\"" + g.getParameter() + "\"");
				results.add(minimap.toString().replace("=", ":"));
			}
			request.setAttribute("gamedatas", results);
			request.setAttribute("category", category);
			url = "worldCup.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		log.warn(category + "로 getWorldCup 실행기록");
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void getWorldCupList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "showError.jsp";
		ArrayList<String> categories = null;
		ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
		try {
			categories = comparisonGameCRUDService.getCategories();
			for (String c : categories) {
				HashMap<String, String> minimap = new HashMap<String, String>();
				minimap.put("title", c);
				minimap.put("source", comparisonGameCRUDService.getGameData(c).get(0).getImgsrc());
				results.add(minimap);
			}
			request.setAttribute("worldCupList", results);
			url = "worldCupList.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		log.warn("getWorldCupList 실행기록");
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void foodWorldCup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "showError.jsp";
		String address = null;
		String searchKeyWords = request.getParameter("searchKeyWords");
		if (request.getParameter("lattitude").equals("")) {
			address = "";
		} else {
			address = JsoupCrawlNaverRestaurants.googleAddressFinder(Double.valueOf(request.getParameter("lattitude")),
					Double.valueOf(request.getParameter("longtitude")));
		}
		ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
		try {
			results = JsoupCrawlNaverRestaurants.crawler(searchKeyWords, address);
			request.getSession().setAttribute("address", address);
			request.setAttribute("searchKeyWords", searchKeyWords);
			request.setAttribute("restaurants", results.toString().replace("=", ":"));
			url = "foodWorldCup.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		log.warn("foodWorldCup 실행기록");
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void userInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		String name = request.getParameter("userName");
		String nickname = request.getParameter("nickName");

		if (id == null || id.length() == 0 || name == null) {
			request.setAttribute("errorMsg", "정보가 부족합니다");
		} else {
			UsersEntity user = UsersEntity.builder().userId(id).password(pw).userName(name).nickName(nickname).build();

			try {
				comparisonGameCRUDService.userInsert(user);
				request.getSession().setAttribute("userId", id);
				request.setAttribute("successMsg", "가입 완료");
				url = "index.jsp";
			} catch (Exception s) {
				request.setAttribute("errorMsg", s.getMessage());
			}
		}
		log.warn("userInsert 실행기록");
		request.getRequestDispatcher(url).forward(request, response);

	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.getSession().setAttribute("id", null);
			url = "index.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		log.warn("logout 실행기록");
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "LoginPage.jsp";
		try {
			if (LoginService.login(request, response)) {
				url = "index.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			url = "showError.jsp";
			e.printStackTrace();
		}
		log.warn("login 실행기록");
		request.getRequestDispatcher(url).forward(request, response);
	}
}
