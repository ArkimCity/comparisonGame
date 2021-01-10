package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import probono.model.JsoupCrawlNaverRestaurants;

public class JsoupCrawlKakao {
	public static void main(String[] args) throws IOException {
		HashMap<String, String> map = JsoupCrawlNaverRestaurants.crawler("시골향기", 37.6005423, 126.7663571).get(0);
		System.out.println(crawler(map.get("name"), map.get("roadAddress")));
		System.out.println(JsoupCrawlMangoPlate.crawler(map.get("name"), map.get("roadAddress")));
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

	public static ArrayList<String> crawler(String search, String address) {
		ArrayList<String> resultlist = new ArrayList<String>();
		Document doc = null;
		try {
			doc = Jsoup.connect("https://m.map.kakao.com/actions/searchView?q="+ address +"&wxEnc=LVSOTP&wyEnc=QNLTTMN&lvl=4")
					.timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements scripts2 = doc.getElementsByTag("span");
		for (Element e : scripts2) {
			String finder = e.toString();
			if(finder.contains(search) && finder.contains("info_result")) {
				resultlist.add(finder);
				break;
			}
		}
		return resultlist;
	}
}