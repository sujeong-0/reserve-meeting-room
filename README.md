# 회의실 예약 시스템 - 백엔드 과제

Spring Boot 기반의 회의실 예약 API 서버입니다. 사용자는 회의실 목록을 조회하고, 예약을 생성/조회/취소할 수 있습니다. Swagger를 통해 예약 생성/조회/취소 API를 직접 호출해볼 수 있으며, Docker 환경에서 바로 실행 가능합니다.

GitHub Repository: [https://github.com/sujeong-0/reserve-meeting-room](https://github.com/sujeong-0/reserve-meeting-room)

## 구현시 가졌던 주요 상황

여러 회사가 함께 사용하는 하나의 공유 오피스 건물 내에는 공용 회의실들이 존재하며,  
각 회사의 직원들이 회의실을 자유롭게 예약하여 사용할 수 있는 환경을 가정하였습니다.

공용 공간인 만큼 중복 예약 및 혼선을 방지하기 위해  
**모든 예약은 30분 단위 슬롯으로 사전에 분할되어 관리**되며,  
사용자는 이 슬롯 단위로 원하는 시간을 선택해 예약할 수 있도록 설계하였습니다.

---

## 기술 스택

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

## 실행 방법

1. 개발 환경 클론

    ```bash
    git clone https://github.com/sujeong-0/reserve-meeting-room.git
    cd reserve-meeting-room
    ```
2. build

    ```bash
   ./gradlew clean build -x test
    ```

3. Docker 실행

    ```bash
    docker-compose up
    ```

>  최초 실행 시에는 아래 명령어를 권장합니다:
> ```bash
> docker-compose up --build
> ```

MariaDB와 Spring Boot 애플리케이션이 docker-compose를 통해 동시에 실행됩니다.
기본 포트는 `8080`입니다.

---

## Swagger 문서 접근 방법

* URL: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* OpenAPI 3.0 기반 명세 자동화

---

## Database 설계

![ERD 설계도](https://github.com/user-attachments/assets/6b3ca221-fd23-412a-b691-35d017d2ecc2)

[ERD 링크](https://www.erdcloud.com/d/v8qRNNy4bFWF2BYob)

### 테이블 요약

- **users**: 사용자 정보 (이름, 이메일, 삭제 여부 등)
- **meeting_rooms**: 회의실 정보 (이름, 위치, 삭제 여부 등)
- **reserve_slots**: 회의실별 고정 단위(30분) 예약 가능한 시간 슬롯을 저장
- **reserves**: 사용자의 예약 기록을 저장
- **reserve_times**: 하나의 예약을 통해 선택한 여러 개의 시간 슬롯 정보를 연결

---

## 교착 상태 관련 고려사항

- 슬롯 기반 예약 구조로, 사용자는 사전에 생성된 30분 단위 슬롯을 선택하여 예약을 진행합니다.
    - 슬롯 기반 예약 구조: 시간 단위를 고정된 크기의 구간(슬롯)으로 미리 나누어, 사용자가 슬롯 단위로 예약을 요청하도록 설계
- 취소 후 재예약을 통해 교착 상태를 방지합니다.

## 동시성 문제 관련 고려사항

- 사용자가 예약을 시도할 때, 예약하고자 하는 슬롯을 조회하는 시점부터 JPA의 락이 적용됩니다.
- 이를 통해 해당 슬롯에 대한 row에 락을 설정하여, 동시에 접근하려고하는 다른 트랙잭션은 대기하거나 차단되며, 동일한 예약 슬록에 대한 접근이 직렬화 됩니다.
- 중복 예약 없이 일관된 상태를 보장할 수 있습니다.
