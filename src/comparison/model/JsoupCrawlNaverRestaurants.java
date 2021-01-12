package comparison.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

public class JsoupCrawlNaverRestaurants {
	
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

	public static String googleAddressFinder(Double lattitude, Double longtitude) {
		log.warn("위도 : " + lattitude + ", 경도 : " + longtitude + " 확인 기록");
		Document doc = null;
		String address = null;
		try {
			String url = "https://www.google.co.kr/maps/place/" + String.valueOf(lattitude) + "N+" + String.valueOf(longtitude) + "E";
			doc = Jsoup.connect(url).timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements scripts = doc.getElementsByTag("meta");
		for (Element e : scripts) {
			if (e.toString().contains("description")) {
				address = e.toString();
				break;
			}
		}
		String longAddress = address.split("\"")[1];
		log.warn(longAddress + "로 주소 변환 완료 기록");
		return longAddress.split(" ")[1] + " " + longAddress.split(" ")[2];
	}
	
	public static ArrayList<HashMap<String, String>> crawler(String search, String address) throws Exception {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Document doc = null;
		try {
			String url = "https://m.map.naver.com/search2/search.nhn?&query=" + address + " " + search;
			doc = Jsoup.connect(url).timeout(10000).get();
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
			String thumburl = null;
			map.put("\"id\"", "\"" + o.get("id").toString() + "\"");
			map.put("\"name\"", "\"" + o.get("name").toString() + "\"");
			map.put("\"abbrAddress\"", "\"" + o.get("abbrAddress").toString() + "\"");
			map.put("\"address\"", "\"" + o.get("address").toString() + "\"");
			map.put("\"roadAddress\"", "\"" + o.get("roadAddress").toString() + "\"");
			map.put("\"category\"", "\"" + o.get("category").toString().replace("\"", "").replace("[", "").replace("]", "") + "\"");
			try {
				map.put("\"thumUrl\"", "\"" + o.get("thumUrl").toString() + "\"");
			} catch (Exception e) {
				for (HashMap<String, String> source : Crawler.googleImageCrawler(o.get("name").toString())) {
					if(source.get("forFoodsSource").startsWith("data")) {
//					} else if(source.get("forFoodsSource").startsWith("https://encrypted")) {
//					} else if(source.get("forFoodsSource").startsWith("http://blogfiles")) {
					} else {
//						System.out.println(source.get("forFoodsSource"));
						map.put("\"thumUrl\"", "\"" + source.get("forFoodsSource") + "\"");
						break;
					}
				}
				log.warn(o.get("name").toString() + " 썸네일 없음 구글에서 검색");
			} // 썸네일이 없는 경우도 다수 존재하기때문에
			list.add(map);
		}//https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZJP1W1a3exma6cx4TzNEonRQVX2aGF5RO7Q&amp;usqp=CAU
		log.warn("검색어 " + search + "(으)로 네이버 지도 검색 기록");
		return list;
	}
}