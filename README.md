# 왼쪽? 오른쪽?(comparisonGame)⚔️
 기존의 이상형 월드컵과 비슷한 방식의 게임과 오늘 점심, 저녁 메뉴를 책임져 주는 '이따가 뭐 먹지' 게임으로 구성되어 있는 사이트입니다.

---

## 📖Content
* [1. 사용된 기술 스택(Stack)](#Stack)
* [2. 데이터 구조(Structure)](#Structure)
* [3. 사용법(How to USE?)](#How-to-use)
* [4. 마무리(Conclusion)](#Conclusion)

---

## 🛠️Stack
* Java
* JavaScript
* CSS
* ESLint
* OracleDB

---

## 📊Structure
<details>
<summary>🗂️ ER Diagram</summary>
<div markdown="1">

![comparisonGame](https://user-images.githubusercontent.com/17983434/104094446-548cf380-52d4-11eb-8e99-1bf450969c79.png)

</div>
</details>

<details>
<summary>📂 Project Structure</summary>
<div markdown="1">

```
* 왼쪽? 오른쪽?(comparisonGame)
|
├── comparison.controller
|      └── comparisonGameController.java
├── comparison.exception
|      ├── MessageException.java
|      └── NotExistException.java
├── comparison.model
|      ├── CategoryDAO.java
|      └── comparisonGameCRUDService.java
|      ├── Crawler.java
|      ├── GameDataDAO.java
|      ├── JsoupCrawlNaverRestaurants.java
|      ├── LoginService.java
|      ├── ScoreDAO.java
|      ├── UsersDAO.java
├── comparison.model.dto
|      ├── CategoryEntity.java
|      ├── GameDataEntity.java
|      └── ScoreEntity.java
|      ├── UsersEntity.java
├── comparison.model.util
|      ├── DBUtil.java
|      └── PublicCommon.java
├── sql
|    ├── ComparisonDDL.sql
|    └── ComparisonDML.sql
├── views
|    ├── foodWorldCup.css
|    ├── foodWorldCup.jsp
|    ├── foodWorldCupPrepare.jsp
|    ├── LoginPage.jsp
|    ├── NavigationBar.jsp
|    ├── showError.jsp
|    ├── SignUp.jsp
|    ├── style.css
|    ├── sweetalert.css
|    ├── UserDetail.jsp
|    ├── UserInsert.html
|    ├── UserUpdate.jsp
|    └── worldCup.css
|    └── worldCupList.css
|    └── worldCupList.jsp
├── package.json
├── package-lock.json
└── index.jsp
```

</div>
</details>

<details>
<summary>🧩 Jsoup | 네이버 지도 및 구글 지도 크롤링(JsoupCrawlNaverRestaurants.java)
</summary>
<div markdown="1">

```java
/**
* 작성자 : 김재웅
* Github(https://github.com/ArkimCity)
* Github.io(https://arkimcity.github.io/)
*
* Jsoup | 네이버 지도 및 구글 지도 크롤링(JsoupCrawlNaverRestaurants.java)
*/
package comparisonGame.model;

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
```

</div>
</details>

---

## 🔰How to USE?
* <u>모든 기능을 이용하려면 먼저 회원가입을 해야 한다.</u>

    <img src="https://user-images.githubusercontent.com/17983434/104255812-40581a80-54bd-11eb-8600-2d1eb127ae12.PNG" width="50%" height="50%" title="1" alt="1">

* 회원가입을 마치고 로그인 화면에서 로그인 후 원하는 게임을 선택하면 된다. 필자는 먼저 '모든 월드컵'을 클릭해 보겠다.

    <img src="https://user-images.githubusercontent.com/17983434/104256588-d9d3fc00-54be-11eb-8ed9-31f5f46230cb.PNG" width="50%" height="50%" title="2" alt="2">

* 다음 화면에서 원하는 게임을 선택하자. 필자는 '강아지 월드컵'을 클릭해 보겠다.

    <img src="https://user-images.githubusercontent.com/17983434/104259110-181fea00-54c4-11eb-931e-80e10b05d07e.PNG" width="50%" height="50%" title="3" alt="3">

* **Enjoy~!**

    <img src="https://user-images.githubusercontent.com/17983434/104260418-d2b0ec00-54c6-11eb-8e5a-c7374134c7a0.gif" width="50%" height="50%" title="4" alt="4">

* 이번에는 다른 게임('이따가 뭐 먹지')을 선택해 보겠다.

    <img src="https://user-images.githubusercontent.com/17983434/104260717-6387c780-54c7-11eb-9a2e-406260658941.PNG" width="50%" height="50%" title="5" alt="5">

* 점심, 저녁을 해결하기 위해서는 집밥이 최고이지만, 가끔은 외식을 하는 것도 나쁘지 않아 보인다. 필자는 '아구찜'이 먹고 싶어졌다.

    * 구글 크롬 브라우저의 **"위치 액세스"를 허용**해 놓고 '아구찜'을 검색해 보자. 그러면, 필자가 위치한 동네 주변의 음식점들을 검색하게 된다.

        <img src="https://user-images.githubusercontent.com/17983434/104261727-887d3a00-54c9-11eb-8f1e-9c2f38d85157.PNG" width="50%" height="50%" title="6_1" alt="6_1">

    * 만약 **"위치 액세스"를 허용하지 않은 상태**에서 검색을 진행할 경우, 전국의 음식점들을 검색하게 된다.

        <img src="https://user-images.githubusercontent.com/17983434/104261922-deea7880-54c9-11eb-91d9-ad3c18193347.PNG" width="50%" height="50%" title="6_2" alt="6_2">

    * **Enjoy~!**

        <img src="https://user-images.githubusercontent.com/17983434/104262457-fc6c1200-54ca-11eb-99cc-7a2bd1aaa0f2.gif" width="50%" height="50%" title="6_3" alt="6_3">

---

## 📝Conclusion
이번 프로젝트에서도 크롤링이 사용되었습니다. '구글 지도'에서 해당 사용자의 위도와 경도를 불러온 뒤, '네이버 지도'에 위도와 경도를 대입하고, 해당 사용자 근처의 음식점을 검색하는 방식으로 구성되었습니다.

이를 잘 활용하면 지도 뿐만 아니라 여러 방면에서 유용하게 사용될 수 있을 것으로 기대됩니다(마땅한 예시가 떠오르지 않습니다).

---

**🎉 The END...**