# 왼쪽? 오른쪽?(comparisonGame)⚔️
 심심하거나 우울할 때 가끔씩 찾아와 주시면 '심신의 평화'를 얻게 되실겁니다...

## 📖Content
* [1. 사용된 기술 스택(Stack)](#Stack)
* [2. 데이터 구조(Structure)](#Structure)
* [3. 사용법(How to USE?)](#How-to-use)
* [4. 마무리(Conclusion)](#Conclusion)

## 🛠️Stack
* Java
* JavaScript
* CSS
* ESLint
* OracleDB

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
|      ├──JsoupCrawlNaverRestaurants.java
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
|    └── worldCupForChecking.html
|    └── worldCupList.css
|    └── worldCupList.jsp
├── package.json
├── package-lock.json
└── index.jsp
```

</div>
</details>

## 🔰How to USE?
* 모든 기능을 이용하려면 먼저 회원가입을 해야 한다.

    <img src="https://user-images.githubusercontent.com/17983434/104255812-40581a80-54bd-11eb-8600-2d1eb127ae12.PNG" width="50%" height="50%" title="1" alt="1">

* 회원가입을 마치고 로그인 화면에서 로그인 후 원하는 게임을 선택하면 된다. 필자는 먼저 '모든 월드컵'을 클릭해 보겠다.

    <img src="https://user-images.githubusercontent.com/17983434/104256588-d9d3fc00-54be-11eb-8ed9-31f5f46230cb.PNG" width="50%" height="50%" title="2" alt="2">

* 다음 화면에서 원하는 게임을 선택하자. 필자는 '강아지 월드컵'을 클릭해 보겠다.

    <img src="https://user-images.githubusercontent.com/17983434/104259110-181fea00-54c4-11eb-931e-80e10b05d07e.PNG" width="50%" height="50%" title="3" alt="3">

* Enjoy~!

    <img src="https://user-images.githubusercontent.com/17983434/104260418-d2b0ec00-54c6-11eb-8e5a-c7374134c7a0.gif" width="50%" height="50%" title="4" alt="4">

* 이번에는 다른 게임('이따가 뭐 먹지')을 선택해 보겠다.

    <img src="https://user-images.githubusercontent.com/17983434/104260717-6387c780-54c7-11eb-9a2e-406260658941.PNG" width="50%" height="50%" title="5" alt="5">

* 점심, 저녁을 해결하기 위해서는 집밥이 최고이지만, 가끔은 외식을 하는 것도 나쁘지 않아 보인다. 필자는 '아구찜'이 먹고 싶어졌다.

    * 구글 크롬 브라우저의 **"위치 액세스"를 허용**해 놓고 '아구찜'을 검색해 보자. 그러면, 필자가 위치한 동네 주변의 음식점들을 검색하게 된다.

        <img src="https://user-images.githubusercontent.com/17983434/104261727-887d3a00-54c9-11eb-8f1e-9c2f38d85157.PNG" width="50%" height="50%" title="6_1" alt="6_1">

    * 만약 **"위치 액세스"를 허용하지 않은 상태**에서 검색을 진행할 경우, 전국의 음식점들을 검색하게 된다.

        <img src="https://user-images.githubusercontent.com/17983434/104261922-deea7880-54c9-11eb-91d9-ad3c18193347.PNG" width="50%" height="50%" title="6_2" alt="6_2">

    * Enjoy~!

        <img src="https://user-images.githubusercontent.com/17983434/104262457-fc6c1200-54ca-11eb-99cc-7a2bd1aaa0f2.gif" width="50%" height="50%" title="6_3" alt="6_3">

## 📝Conclusion
The END...