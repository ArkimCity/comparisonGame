package test;

import java.io.IOException;

import probono.model.JsoupCrawlNaverRestaurants;

public class StarController {
	public static void main(String[] args) throws Exception {
		JsoupCrawlNaverRestaurants.crawler("시골향기", "김포시 고촌읍");
		//네이버 주소중에 하나를 골라 id를 받아오고 나머지는 그 주소값과 식당이름으로 다른 곳에 검색을 시작한다고 하자
		
	}
}
