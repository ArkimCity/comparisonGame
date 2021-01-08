package test;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupCrawlMangoPlate {
	public static void main(String[] args) {
		System.out.println(crawler("시골향기", "경기도 김포시 고촌읍 신곡리 447-75"));
	}

	public static JSONObject jsonParser(String content) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			Object obj = parser.parse(content);
			jsonObject = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static String crawler(String search, String address) {
		Document doc1 = null;
		Document doc2 = null;
		
		try {
			doc1 = Jsoup.connect("https://www.mangoplate.com/search/" + search)
					.timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements elements = doc1.getElementsByTag("a");
		
		String url = null;
		
		for (Element e : elements) {
			String finder = e.toString();
			if(finder.contains("href=\"/restaurants/") && finder.contains(address)) {
				url = (finder.split("\"")[3]);
				break;
			}
		}
		
		String answer;
		
		if (url==null) {
			answer = "존재하지 않습니다";
		} else {
			answer = "https://www.mangoplate.com" + url;
			try {
				doc2 = Jsoup.connect("https://www.mangoplate.com" + url).timeout(10000).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return answer;
	}
}