# 회의실 예약 시스템 - 백엔드 과제

Spring Boot 기반의 회의실 예약 API 서버입니다. 사용자는 회의실 목록을 조회하고, 예약을 생성/조회/취소할 수 있습니다. Swagger를 통해 예약 생성/조회/취소 API를 직접 호출해볼 수 있으며, Docker 환경에서 바로 실행 가능합니다.

🔗 GitHub Repository: [https://github.com/sujeong-0/reserve-meeting-room](https://github.com/sujeong-0/reserve-meeting-room)

---

## 📌 기술 스택

* **Language**: Java 17
* **Framework**: Spring Boot 3.3.11
* **Build Tool**: Gradle
* **Database**: MariaDB (Docker)
* **ORM**: Spring Data JPA + QueryDSL 5
* **API Docs**: springdoc-openapi 2.6.0 (Swagger UI)
* **Validation**: javax / jakarta Bean Validation
* **Testing**: JUnit 5
* **DevOps**: Docker, Docker Compose

---

## 🚀 실행 방법

### 1. 개발 환경 클론

```bash
git clone https://github.com/sujeong-0/reserve-meeting-room.git
cd reserve-meeting-room
```

### 2. Docker 실행

```bash
docker-compose up
```

> 💡 최초 실행 시에는 아래 명령어를 권장합니다:
> ```bash
> docker-compose up --build
> ```

MariaDB와 Spring Boot 애플리케이션이 docker-compose를 통해 동시에 실행됩니다.
기본 포트는 `8080`입니다.

---

## 📘 Swagger 문서 접근 방법

* URL: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* OpenAPI 3.0 기반 명세 자동화

---

## 📂 Database 설계

![ERD 설계도](https://github.com/user-attachments/assets/a72e4430-2dcc-47fa-bcdb-5a287c2366ae)

[ERD 링크](https://www.erdcloud.com/d/QvqLtqkQN2YpWdodJ)

### 테이블 요약

- **users**: 사용자 정보 (이름, 이메일, 삭제 여부 등)
- **meeting_rooms**: 회의실 정보 (이름, 위치, 삭제 여부 등)
- **reserves**: 예약 정보 (사용자, 회의실, 예약 시작/종료 시간 등)