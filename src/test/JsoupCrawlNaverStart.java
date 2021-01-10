package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.extern.slf4j.Slf4j;

@Slf4j

public class JsoupCrawlNaverStart {
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

	public static ArrayList<HashMap<String, String>> crawler(String search, double lattitude, double longtitude) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Document doc = null;
		try {
			//https://m.map.naver.com/search2/search.nhn?query=한식&sm=clk&centerCoord=37.6005423:126.7663571
			doc = Jsoup.connect("https://m.map.naver.com/search2/search.nhn?query=" + search + "&sm=clk&centerCoord=" + String.valueOf(lattitude) + ":" + String.valueOf(longtitude) )
					.timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements scripts = doc.getElementsByTag("script");

		String a = null;

		for (Element element : scripts) {
			if (element.data().contains("var searchResult")) {
				Pattern pattern = Pattern.compile(".*var searchResult = ([^;]*);");
				Matcher matcher = pattern.matcher(element.data());
				if (matcher.find()) {
					a = matcher.group(1);
					break;
				} else {
					System.err.println("No match found!");
				}
				break;
			}
		}

		for (Object i : (ArrayList<Object>) jsonParser(jsonParser(a).get("site").toString()).get("list")) {
			HashMap<String, String> map = new HashMap<String, String>();
			JSONObject o = jsonParser(i.toString());
			map.put("\"id\"", "\"" + o.get("id").toString() + "\"");
			map.put("\"name\"", "\"" + o.get("name").toString() + "\"");
			map.put("\"abbrAddress\"", "\"" + o.get("abbrAddress").toString() + "\"");
			map.put("\"address\"", "\"" + o.get("address").toString() + "\"");
			map.put("\"roadAddress\"", "\"" + o.get("roadAddress").toString() + "\"");
			map.put("\"category\"", "\"" + o.get("category").toString().replace("\"", "").replace("[", "").replace("]", "") + "\"");
			try {
				map.put("\"thumUrl\"", "\"" + o.get("thumUrl").toString() + "\"");
			} catch (Exception e) {log.warn(o.get("name").toString() + " 썸네일 없음");} //없는 경우도 다수 존재하기때문에
			list.add(map);
		}
		log.warn("네이버 식당 검색 기록");
		return list;
	}
}