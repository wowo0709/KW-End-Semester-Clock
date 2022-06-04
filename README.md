# 광운대학교 종강시계 어플리케이션

## 프로젝트 설명

광운대학교 학생들을 위한 광운대학교 종강시계 앱을 제작한다. 
사용자는 해당 앱을 통해 종강까지 남은 시간을 확인할 수 있음은 물론, 대학 생활에 필요한 정보를 제공받을 수 있다.

<br>

앱의 기능 정의는 다음과 같다.

<br>

**앱의 홈 화면에서 종강까지 남은 시간을 알려준다**

-	남은 시간은 사용자가 설정을 통해 변경 가능

**앱의 홈 화면에서는 4개의 버튼을 통해 다양한 정보를 제공받을 수 있다.**

-	날씨 버튼을 통해 캠퍼스 현재 날씨 또는 예보를 알 수 있다.
-	교수님 정보 버튼을 통해 교수님 연락처 정보, 연구실 정보 등을 알 수 있다.
    -	[단과 대] – [소속학과] – [해당 학과 교수님]으로 이어지는 목록의 선택을 통해 사용자가 편리하게 자신이 원하는 교수님의 정보를 알 수 있도록 한다. 성명, 직위, 교내전화, 이메일, 홈페이지의 정보가 제공된다.
-	공지사항 버튼을 통해 학사공지 및 여러 공지사항을 볼 수 있다.
    -	일반, 학사, 학생, 등록/장학 공지 등의 학교 공지 사항을 편리하게 찾아볼 수 있다.
    -	공지사항 즐겨찾기 등록을 버튼을 통해서 사용자가 원하는 공지사항만 따로 모아서 확인할 수 있으며, 이를 내 공지에서 확인할 수 있다. 
-	음식점 정보 버튼을 통해 캠퍼스 주변 음식점 정보를 알 수 있다.
    -	학생들이 미처 알지 못했던 장소의 음식점을 알 수 있다. 
    -	캠퍼스 주변 음식점들이 가시적으로 한 눈에 보여 메뉴 선택에 도움이 된다. 
    -	각 음식점의 정보를 고려함으로써 해당 음식점 방문을 결정할 수 있다. 

기존에 있던 다른 학교들의 [종강 시계]는 웹 페이지 상에 구현되어 있다. 

이를 변주하여 자대 [종강 시계]를 어플리케이션으로 제작한다. 

<br>

## 개발 내용

개발은 크게 5가지(종강까지 남은 시간, 날씨 메뉴, 교수님 정보 메뉴, 공지사항 메뉴, 날씨 메뉴)로 나뉜다.

각 메뉴에 대한 개발 내용은 다음과 같다.

<br>

1. 종강까지 남은 시간

   - 백 엔드 로직 구현

   - 사용자 UI 구현

2. 날씨 메뉴

   - 날씨 정보를 위한 API 사용

   - 백 엔드 로직 구현

   - 사용자 UI 구현

3. 교수님 정보 메뉴

   - 데이터 관리를 위한 데이터베이스

   - 데이터 수집을 위한 웹 크롤링

   - 백 엔드 로직 구현

   - 사용자 UI 구현

4. 공지사항 메뉴

   - 데이터 관리를 위한 데이터베이스

   - 데이터 수집을 위한 웹 크롤링

   - 백 엔드 로직 구현

   - 사용자 UI 구현

5. 음식점 메뉴

   - 음식점 정보를 위한 API 사용

   - 백 엔드 로직 구현

   - 사용자 UI 구현

<br>

## 해결 방법

**종강까지 남은 시간**

- 백 엔드 로직 구현
  - 종강까지 남은 시간은 설정된 종강 날짜, 시간에 현재 날짜, 시간을 빼서 남은 시간을 구한다. 계산을 통해 남은 시간을 구하게 되면 이후에 앱이 실행되는 동안 1마다 1초씩 빼면서 남은 시간을 계산한다. 

  - Default로 설정된 종강 시간은 2021년 12월 21일 정오이다. 사용자 마다 종강 날짜와 시간이 다를 수 있기 때문에 사용자가 변경 가능하도록 구현하였다. 홈 화면 오른쪽 설정 메뉴를 눌러 설정이 가능하다. 

  - 설정은 날짜 설정, 시간 설정이 있으며 각 버튼을 눌러 설정이 가능하며, 현재 설정에 대한 정보도 확인할 수 있다.

- 사용자 UI 구현
  * 종강까지 남은 시간은 홈 화면에 TextView를 사용하여 보여준다. 1초마다 데이터가 업데이트 되면 업데이트 된 내용을 TextView에 적용하여 사용자가 확인할 수 있다.
  * 홈 화면 상단에 설정 메뉴는 버튼을 사용해 구현하였으며, 설정 버튼 누르면 설정 화면을 보여준다. 설정 화면에는 현재 설정 및 날짜 설정, 시간 설정 버튼이 있다. 
  * 현재 설정은 TextView를 통해 보여주고 날짜 설정, 시간 설정 버튼은 캘린더 뷰를 사용해 구현하였다. 설정이 바뀌면 바뀐 설정을 현재 설정으로 보여주고, 남은 시간을 재 계산해서 홈화면에 남은 시간을 보여준다.

<br>

**날씨 메뉴**

- 날씨 정보를 위한 API 사용
  * 날씨 API는 크게 공공데이터포털 사이트와 OpenWeather 사이트가 있다. 공공데이터포털 사이트는 API를 이용할 수 있는 키의 만료기간이 길지 않고 코드가 제대로 작동되지 않는 문제점이 있어 OpenWeather 사이트의 API를 사용하였다. OpenWheather 사이트에 로그인을 한 후 계정에 날씨 API를 신청해 키값을 받아 코드에 입력을 한다.
  * 코드에 입력된 OpenWeather 사이트의 주소와 키값은 OpenWeather 사이트에서 제공하는 날씨 데이터를 이용할 수 있도록 해준다.

- 백 엔드 로직 구현
  * 광운대학교의 주소를 좌표로 바꾼 값을 호도법에서 10진수로 바꾸어 OpenWeather 사이트이 API가 광운대학교의 날씨 정보를 가져올 수 있도록 했다. 
  * 데이터 통신에 유용한 라이브러리인 레트로핏을 이용해 날씨 데이터를 저장할 수 있는 클래스를 구성해 날씨 데이터를 활용할 수 있도록 했다. 
  * 현재온도, 최고온도, 최저온도, 습도에 따라 UI에 표현될 텍스트의 내용이 다르도록 기능을 구현했다.

- 사용자 UI 구현
  * 중앙에 커다란 글씨로 TextView를 사용해 광운대학교 위치에서의 현재온도, 최고온도, 최저온도가 나타나도록 한다. 그리고 온도와 습도가 얼마인가에 따라서 화면 상단에 우아한 형제들 컨셉으로 출력될 텍스트가 다르게 나타나게 된다.

<br>

**교수님 정보 메뉴**

- 데이터 관리를 위한 데이터베이스
  * 데이터베이스는 Room 라이브러리를 사용하여 구현했다. 쿼리문은 데이터를 삽입하는 Insert와 데이터를 삭제하는 delete, 모든 데이터를 삭제하는 clearAll이 있으며, getCategoryData 함수를 통해 동일한 category 값을 갖는 데이터를 list로 반환하도록 작성했다.

- 데이터 수집을 위한 웹 크롤링
  * 크롤링은 java의 HTML Parser인 Jsoup 라이브러리를 사용하여 구현했다.
  * 데이터 수집을 위한 웹사이트는 학교 홈페이지 www.kw.ac.kr을 통해 사용할 수 있었으며, 교육 -> 대학 탭에서 각 단대 홈페이지를 통해 각 과의 내용을 확인했다.
  * 크롤링된 데이터는 ProfessorItem으로 만들어져 Insert함수를 통해 데이터베이스에 삽입된다. 데이터는 category:학과, name:성명, position:직위, telNum:교내전화, email:이메일, homepage:홈페이지 이며, 데이터를 item으로 만들어 삽입된다.

- 백 엔드 로직 구현
  * 데이터를 각 단대, 각 학과별로 보여주기 위해 단대에 대한 스피너, 스피너를 통해 단대가 설정되면 학과에 대한 스피너가 update 된다. 학과에 대한 스피너가 설정되면 스피너 리스너를 통해 선택된 과에 대해서 과에 대한 데이터들을 load한다.
  * getCategoryData 함수를 통해 동일한 category에 대한 데이터를 list로 반환하는데 여기서 category는 ‘학과’로 리스너로 선택된 학과 정보를 받아올 수 있다.

- 사용자 UI 구현
  * 각 단대, 각 학과를 위한 스피너가 있으며, 스피너를 통해 학과가 선택되었을 때 load된 데이터를 출력하기 위해 RecyclerView를 사용했다. professor_recycler_item을 사용해 data를 삽입하고, 데이터가 삽입된 item을 RecyclerView로 보여줄 수 있도록 구현했다.

<br>

**공지사항 메뉴**

- 데이터 관리를 위한 데이터베이스
  * 데이터베이스는 Room 라이브러리를 사용하여 구현했다. 쿼리문은 데이터를 삽입하는 Insert와 데이터를 삭제하는 delete, 모든 데이터를 삭제하는 clearAll이 있으며, getCategoryData 함수를 통해 동일한 category 값을 갖는 데이터를 list로 반환하도록 작성했다. 
  * 또한 즐겨찾기 공지를 위한 함수로 getFavoriteData함수를 통해 즐겨찾기가 활성화(favorite==true)된 데이터를 list로 반환하게 작성했다.

- 데이터 수집을 위한 웹 크롤링
  * 크롤링은 java의 HTML Parser인 Jsoup 라이브러리를 사용하여 구현했다.
  * 데이터 수집을 위한 웹사이트는 학교 홈페이지 www.kw.ac.kr을 통해 사용할 수 있었으며, KW-LIFE -> 공지사항 탭에서 공지사항을 확인했다. 공지사항은 일반, 학사, 학생, 등록/장학의 공지사항 내용을 사용했다.
  * 크롤링된 데이터는 NoticeItem으로 만들어져 Insert함수를 통해 데이터베이스에 삽입된다. 데이터는 category:공지사항 카테고리, url:공지사항 내용 url, title:제목, info:공지사항 정보, favorite:즐겨찾기 이며, 데이터를 item으로 만들어 삽입된다.

- 백 엔드 로직 구현
  * 데이터를 각 카테고리 별로 보여주기 위해 탭을 사용했다. 카테고리에 대한 탭이 선택되면 탭 리스너를 통해 선택된 카테고리에 데이터들을 load한다.
  * getCategoryData 함수를 통해 동일한 category에 대한 데이터를 list로 반환하는데 리스트를 RecyclerView를 통해 보여준다. 또한 공지사항이 선택되면 해당 공지사항에 해당하는 url이 연결되어 공지사항에 대한 내용을 보여준다.
  * 공지사항 즐겨찾기 선택은 공지사항 왼쪽의 별 모양 버튼을 통해 설정할 수 있는데, 즐겨찾기가 설정되면 별모양의 버튼이 노란색으로 되면서 Toast알림이 뜬다. 즐겨찾기 해제도 마찬가지로 버튼을 누르면 노란색의 별모양 버튼이 회색으로 바뀌며 해제되었다는 Toast알림이 뜬다. 즐겨찾기 선택이 되면 item의 favorite이 true가 되고, 해제되면 false가 된다. 

- 사용자 UI 구현
  * 각 카테고리를 위한 탭이 있으며, 탭을 통해 카테고리가 선택되었을 때 load된 데이터를 출력하기 위해 RecyclerView를 사용했다. notice_recycler_item을 사용해 data를 삽입하고, 데이터가 삽입된 item을 RecyclerView로 보여줄 수 있도록 구현했다.

<br>

**음식점 메뉴**

- 음식점 정보를 위한 API 사용
  * 광운대 근처의 음식점 정보를 가져오기 위해 서울 열린 데이터 광장(https://data.seoul.go.kr/)의 노원구 음식점 데이터(https://data.seoul.go.kr/dataList/OA-18662/S/1/datasetView.do)를 사용. 
  * API 키를 발급받은 후 xml과 json 파일 포맷으로 데이터를 이용할 수 있다.

- 백 엔드 로직 구현
  * 우선 json 포맷의 공공 데이터를 복사한다. 안드로이드 스튜디오는 json 파일을 바로 코틀린 클래스로 변환해주는 [Kotlin data class from JSON file] 플러그인을 제공한다. 해당 플러그인을 다운로드 받고 클래스 생성을 할 때 복사한 json 파일을 붙여넣기 하면 해당 json 파일의 구조에 맞게 몇 개의 데이터 클래스를 생성해준다. 
  * 데이터 클래스 생성 후에는 공공 API와 통신하기 위해 인터페이스를 생성한다. 이 인터페이스에는 도메인 주소, API 키와 통신 메서드 등이 선언되어 있다. 
  * 본격적으로 통신을 이용해 데이터를 가져오고, 이를 출력해주는 코드를 작성한다. 100페이지씩 140페이지, 총 14000개의 총 데이터를 가져온다. 노원구 음식점 데이터의 위경도 정보는 kt 좌표계로 선언되어 있기 때문에 이를 java의 라이브러리를 이용하여 WGS 좌표계로 변환한다. 이는 코틀린의 지도 메서드들은 위경도 정보로 WGS 좌표계로 나타내어진 좌표를 필요로 하기 때문이다. 
  * 데이터를 가져왔으면 구글 맵에 마커로 음식점을 표시해준다. 

- 사용자 UI 구현
  * 사용자 UI로는 Google Map을 사용한다. 
  * 구글 맵의 사용자 위치를 광운대학교 위치로 고정시키고, 주변 음식점들의 위치를 마커를 이용해 표시한다. 
  * 마커를 클릭하면 해당 음식점의 이름을 볼 수 있다. 

<br>

## 최종 결과

![image](https://user-images.githubusercontent.com/70505378/171989952-83df0be4-be93-4986-a03d-59b92910f504.png)

![image](https://user-images.githubusercontent.com/70505378/171989962-0a916551-c968-4840-8d4c-3fe066a3d2ce.png)

![image](https://user-images.githubusercontent.com/70505378/171989964-a58dc051-f205-461f-95ed-706da9e1e209.png)

![image](https://user-images.githubusercontent.com/70505378/171989966-55c7d99d-7594-4ce0-8ccd-536c9ca5b73c.png)

![image](https://user-images.githubusercontent.com/70505378/171989970-4ff928b9-2950-46df-bb87-3920e9606726.png)

![image](https://user-images.githubusercontent.com/70505378/171989974-ba402201-b84e-419f-84ab-ee9afc480778.png)



<br>

## 참고 자료

타대학 종강시계 

1.	https://c17an.netlify.app/blog/essay/%ED%95%AD%EA%B3%B5%EB%8C%80-%EC%A2%85%EA%B0%95%EC%8B%9C%EA%B3%84-%EC%A0%9C%EC%9E%91%EA%B8%B0/article/
2.	https://maxkim-j.github.io/posts/hufs-semester-clock

날짜 및 시간 설정

1.	https://developer.android.com/reference/android/app/DatePickerDialog 
2.	https://developer.android.com/reference/android/app/TimePickerDialog 

웹 크롤링

1.	https://medium.com/%EC%8A%AC%EA%B8%B0%EB%A1%9C%EC%9A%B4-%EA%B0%9C%EB%B0%9C%EC%83%9D%ED%99%9C/%EC%98%88%EC%A0%9C-jsoup%EC%9C%BC%EB%A1%9C-%EC%9B%B9-%ED%81%AC%EB%A1%A4%EB%A7%81%ED%95%98%EA%B8%B0-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%BD%94%ED%8B%80%EB%A6%B0-cec9e728031a 
2.	https://velog.io/@terry960302/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%BD%94%ED%8B%80%EB%A6%B0-%EB%8C%80%ED%95%99%EA%B5%90-%ED%95%99%EC%8B%9D-%EB%A9%94%EB%89%B4-%EC%9B%B9-%ED%81%AC%EB%A1%A4%EB%A7%81%ED%95%B4%EB%B3%B4%EA%B8%B01-2sjx2t17o7 

안드로이드 UI 관련

1.	https://developer.android.com/guide/topics/ui/controls/spinner 
2.	https://redmuffler.tistory.com/425 
3.	https://youtu.be/ngmjy5DFu8E 
4.	https://developer.android.com/guide/topics/ui/controls/button 

retrofit 통신

1.	https://m.blog.naver.com/cosmosjs/221959349303 
2.	https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=mym0404&logNo=221602533010 

OpenAPI

1.	https://home.openweathermap.org/ 
2.	https://data.seoul.go.kr/dataList/OA-18662/S/1/datasetView.do 

Google Map

1.	https://webnautes.tistory.com/647 
2.	https://imleaf.tistory.com/16 

