< 기술 스택 >
- Backend : Spring MVC 5.2.2 + Spring JDBC + MySQL
- Frontend : Bootstrap 4.1 Template + Handlebar + Javascript(Jquery)

< 프로젝트 확인 방법 >
- AWS(EC2)를 이용하여 간단히 배포하였습니다.
- http://13.209.0.140:8080/diary 를 방문해주세요.

< 구현 기능 >
1. Login
- 시작할 때에 회원 가입 및 로그인을 할 수 있습니다.
- Spring Security로 인증을 하고 있고, 회원 삭제 또한 가능합니다.
- 일반 회원은 USER로서 ROLE을 가지고, 운영자는 USER와 ADMIN의 2가지 ROLE을 가집니다.

2. Home
- 메인 페이지입니다.
- Logout은 logout이후 signin 페이지로 리다이렉트합니다.

3. Development History
- 개발자들이 자신의 개발 내역을 조회, 삭제, 수정할 수 있습니다
- 상단의 Development History 탭을 눌러서 최신 날짜 순서대로 개발 내역을 볼 수 있습니다.
- 카드를 누르면 상세 내용을 확인할 수 있고, 작성자와 로그인한 유저가 같으면 수정 및 삭제 가능합니다.
- 단 admin으로 로그인한 유저는 모든 수정 권한을 가집니다

4. Algorithm
- 개발자들이 자신이 풀었던 알고리즘 문제 코드의 파일 업로드 및 다운로드 기능을 제공합니다.
- 상단의 Algorithm 탭을 누르면 최신 날짜 순서대로 업로드했던 문제 내역을 볼 수 있습니다
