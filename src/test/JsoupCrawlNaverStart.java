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

public class JsoupCrawlNaverStart {
	public static void main(String[] args) {
		for (Object string : crawler("시골향기")) {
			System.out.println(string);
		}
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

	public static ArrayList<HashMap<String, String>> crawler(String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Document doc = null;
		try {
			doc = Jsoup.connect("https://m.map.naver.com/search2/search.nhn?query=" + search + "&sm=hty&style=v5")
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
			map.put("id", o.get("id").toString());
			map.put("name", o.get("name").toString());
			map.put("abbrAddress", o.get("abbrAddress").toString());
			map.put("address", o.get("address").toString());
			map.put("roadAddress", o.get("roadAddress").toString());
			map.put("category", o.get("category").toString());
			
			list.add(map);
		}
		
		return list;
	}
}