Developer Diary는 다음과 같이 개발되었습니다.

- Backend : Spring MVC 5.2.2 + Spring JDBC + MySQL
- Frontend : Bootstrap 4.1 Template + Handlebar + Javascript(Jquery)

< 프로젝트 수행 방법 >
1. Database 생성
  - root 권한으로 MySQL 접속 (sudo mysql -uroot)
  - source db.sql 로 user 및 database 생성
  - system mysql -useung -p 로 user 변경하고 use diary 로 database 선택
  - source table.sql로 테이블 생성
2. Tomcat 8.5로 배포 (TOMCAT 8.5가 설치된 경로가 $TOMCAT_HOME)
  - target/diary.war를 $TOMCAT_HOME/webapps/ 로 복사
  - mv ROOT ROOT_backup 으로 ROOT 디렉토리 이름 변경
  - $TOMCAT_HOME/bin 의 startup.sh 실행
  - http://localhost:8080/diary/ 접속

< 구현 기능 >
1. Login
- 시작할 때에 회원 가입 및 로그인을 할 수 있습니다.
- Spring Security로 인증을 하고 있고, 회원 삭제 또한 가능합니다.
- 일반 회원은 USER로서 ROLE을 가지고, 운영자는 USER와 ADMIN의 2가지 ROLE을 가집니다.

2. Home
- 메인 페이지로 이동합니다
- Logout은 logout이후 signin 페이지로 리다이렉트하고 GitHub을 누르면 본 프로젝트의 소스코드 저장소로 이동합니다.

3. Development History
- 그 동안의 개발 내역을 조회, 삭제, 수정할 수 있습니다
- 상단의 Development History 탭을 누르면 최신 날짜 순서대로 개발 내역을 볼 수 있습니다.
- 카드를 누르면 상세 내용을 확인할 수 있고, 작성자와 로그인한 유저가 같으면 수정 및 삭제 가능합니다.
- 단 admin으로 로그인한 유저는 모든 수정 권한을 가집니다

4. Algorithm
- 그 동안 풀었던 algorithm 문제의 코드를 파일 업로드합니다
- 상단의 Algorithm 탭을 누르면 최신 날짜 순서대로 업로드했던 문제 내역을 볼 수 있습니다
- Algorithm 풀이 코드를 업로드 및 다운로드 기능을 구현하였습니다
