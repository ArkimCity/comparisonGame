package probono.controller;

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

import lombok.extern.slf4j.Slf4j;
import probono.model.LoginService;
import probono.model.Crawler;
import probono.model.JsoupCrawlNaverRestaurants;
import probono.model.WorldOfWordsCRUDService;
import probono.model.dto.UserEntity;

@Slf4j

@WebServlet("/worldofwords")
public class WorldOfWordsController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//선생님 코멘트 - 컨트롤러의 현재 엔티티매니저는 컨트롤러에서 사용하지 않는 것이 좋다. 추후 프로젝트 진행시에는  컨트롤러가 아니라 서비스에서 사용해야함
		String command = request.getParameter("command");
		try{
			if(command.equals("login")){
				login(request, response);
			}else if(command.equals("logout")){
				logout(request, response);
			}else if(command.equals("userInsert")){
				userInsert(request, response);
			}else if(command.equals("userCommunityInsert")){
				communityInsert(request, response);
			}else if(command.equals("userCommunityAll")){
				communityAll(request, response);
			}else if(command.equals("saveUserWord")){//단어리스트에 단어 보내주는 역할
				saveUserWord(request, response); 
			}else if(command.equals("deleteUserWord")){//단어리스트에 단어 보내주는 역할
				deleteUserWord(request, response); 
			}else if(command.equals("getWordList")){//단어리스트에 단어 보내주는 역할
				getWordList(request, response); 
			}else if(command.equals("brainStorm")){//단어리스트에 단어 보내주는 역할
				brainStorm(request, response); 
			}else if(command.equals("brainStormResult")){//단어리스트에 단어 보내주는 역할
				brainStormResult(request, response); 
			}else if(command.equals("foodWorldCup")){//음식 월드컵 모듈과 연결
				foodWorldCup(request, response); 
			}else {
				request.setAttribute("errorMsg", "잘못된 명령입니다. 다시 시도해 주십시오");
				request.getRequestDispatcher("showError.jsp").forward(request, response);
			}
			
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			s.printStackTrace();
		}
	}

	private void foodWorldCup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "showError.jsp";
		String address = null;
		String searchKeyWords = request.getParameter("searchKeyWords");
		if (request.getParameter("lattitude").equals("")) {
			address = "";
		}else {
			address = JsoupCrawlNaverRestaurants.googleAddressFinder(Double.valueOf(request.getParameter("lattitude")),Double.valueOf(request.getParameter("longtitude")));
		}
		ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
		try {
			results = JsoupCrawlNaverRestaurants.crawler(searchKeyWords, address);
			request.getSession().setAttribute("address", address);
			request.setAttribute("searchKeyWords", searchKeyWords);
			request.setAttribute("restaurants", results.toString().replace("=",":"));
			url = "worldCup.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void brainStormResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		ArrayList<HashMap<String, ArrayList<String>>> relatedWordMap = new ArrayList<HashMap<String, ArrayList<String>>>();
		ArrayList<HashMap<String, ArrayList<String>>> relatedWordMap2 = new ArrayList<HashMap<String, ArrayList<String>>>();
		ArrayList<HashMap<String, String>> imageBigList = new ArrayList<HashMap<String, String>>();
		try {
			getUserWords(request, response);
			for (String word : (ArrayList<String>) request.getAttribute("userwords")) {
				
				for (HashMap<String, String> image : Crawler.googleImageCrawler(word)) {
					imageBigList.add(image);
				}
				
				HashMap<String, ArrayList<String>> minimap = new HashMap<String, ArrayList<String>>();
				minimap.put(word, Crawler.relatedNaverCrawler(word));
				relatedWordMap.add(minimap);
				
				HashMap<String, ArrayList<String>> minimap2 = new HashMap<String, ArrayList<String>>();
				minimap2.put(word, Crawler.relatedGoogleCrawler(word));
				relatedWordMap2.add(minimap2);
			}
			request.setAttribute("imageBigList", imageBigList);
			Collections.shuffle(imageBigList);
			request.setAttribute("imageRandomBigList", imageBigList);
			request.setAttribute("relatedWordMap", relatedWordMap);
			request.setAttribute("relatedWordMap2", relatedWordMap2);
			url = "brainStormResult.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void brainStorm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			url = "brainStorm.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void saveUserWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		String id = (String) request.getSession().getAttribute("id");
		String word = request.getParameter("word");

		if (id == null || id.length() == 0) {
			request.setAttribute("errorMsg", "로그인 하셔야 저장이 가능합니다!");
		} else if (word == null || word.length() == 0) {
			request.setAttribute("errorMsg", "단어를 적어주셔야죠!");
		} else {
			try {
				WorldOfWordsCRUDService.saveUserWord(id, word);
				getUserWords(request, response);
				url = "wordList.jsp";
			} catch (Exception s) {
				request.setAttribute("errorMsg", s.getMessage());
			}
		}
		request.getRequestDispatcher(url).forward(request, response); 
	}

	private void deleteUserWord(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String url = "showError.jsp";
		String id = (String) request.getSession().getAttribute("id");
		String word = request.getParameter("word");
		try {
			WorldOfWordsCRUDService.deleteUserWord(id, word);
			getUserWords(request, response);
			url = "wordList.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void getUserWords(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<String> userwords = new ArrayList<String>();
		if (request.getSession().getAttribute("id") == null) {
			request.setAttribute("noWordsMsg", "아직 저장하신 단어가 없네요!");
		} else {
			try {
				String id = (String)request.getSession().getAttribute("id");
				userwords = WorldOfWordsCRUDService.getUserWords(id);
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				e.printStackTrace();
			}
		}
		request.setAttribute("userwords", userwords);
	}

	private void userInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showError.jsp";
		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		String name = request.getParameter("userName");
		String nickname = request.getParameter("nickname");

		if (id == null || id.length() == 0 || name == null) {
			request.setAttribute("errorMsg", "정보가 부족합니다");
		} else {
			UserEntity user = UserEntity.builder().userId(id).password(pw).userName(name).nickname(nickname).build();
			try {
				WorldOfWordsCRUDService.addUser(user);
				request.getSession().setAttribute("id", id);
				request.setAttribute("successMsg", "가입 완료");
				url = "activistDetail.jsp";
			} catch (Exception s) {
				request.setAttribute("errorMsg", s.getMessage());
			}
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

	private void communityInsert(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void communityAll(HttpServletRequest request, HttpServletResponse response) {

	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.getSession().setAttribute("id", null);
			url = "wordList.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "login-page.jsp";
		try {
			if (LoginService.login(request, response)) {
				getUserWords(request, response);
				url = "wordList.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			url = "showError.jsp";
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

	public void getWordList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<HashMap<String, String>> words = new ArrayList<HashMap<String, String>>();
		String inputnumber = request.getParameter("inputnumber");
		String url = "showError.jsp";
		if (inputnumber == null) {
			words = Crawler.crawler("10");
			url = "wordList.jsp";
		} else {
			words = Crawler.crawler(inputnumber);
			url = "wordList.jsp";
		}
		getUserWords(request, response);
		request.getSession().setAttribute("words", words);
		request.getRequestDispatcher(url).forward(request, response);
	}
}
