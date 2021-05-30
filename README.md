Developer Diary는 다음과 같이 개발되었습니다.

Backend : Spring MVC 5.2.2 + Spring JDBC + MySql
Frontend : Bootstrap 4.1 Template + Javascript(Jquery)

Backend는 모든 부분이 새롭게 개발되었고, Bootstrap은 기존의 template을 그대로 이용하였습니다. 
Template Engine으로는 Handlebar를 사용하고 Javascript로 이벤트 핸들링만 처리하였습니다


Developer Diary에서는 다음 기능이 이용가능합니다


1.Home
- 메인 페이지로 이동합니다
- Logout은 logout이후 signin 페이지로 리다이렉트하고 GitHub을 누르면 본 프로젝트의 소스코드 저장소로 이동합니다.

2. Development History
- 그 동안의 개발 내역을 조회, 삭제, 수정할 수 있습니다
- 상단의 Development History 탭을 누르면 최신 날짜 순서대로 개발 내역을 볼 수 있습니다.
- 카드를 누르면 상세 내용을 확인할 수 있고, 작성자와 로그인한 유저가 같으면 수정 및 삭제 가능합니다.
- 단 admin으로 로그인한 유저는 모든 수정 권한을 가집니다

3. Algorithm
- 그 동안 풀었던 algorithm 문제의 코드를 파일 업로드합니다
- 상단의 Algorithm 탭을 누르면 최신 날짜 순서대로 업로드했던 문제 내역을 볼 수 있습니다
- Algorithm 풀이 코드를 업로드 및 다운로드 기능을 구현하였습니다
