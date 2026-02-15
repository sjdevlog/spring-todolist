# Todo List Web Application

Spring Boot 기반의 **Todo List 웹 애플리케이션**입니다.  
본 프로젝트는 백엔드 REST API 설계 및 구현부터
HTML/CSS/JavaScript 기반 프론트엔드 연동까지 전 과정을 직접 경험하기 위해 진행한 개인 프로젝트입니다.

Controller–Service–Repository 계층 구조와 JPA를 활용한 데이터베이스 연동,
정적 프론트엔드와의 API 통신을 통해 웹 애플리케이션의 전체 흐름을 구현하는 데 중점을 두었습니다.
---

## 1. Project Overview

이 프로젝트는 할 일(Todo)의 생성, 조회, 상태 변경, 삭제 기능을 제공하는
웹 기반 Todo 관리 애플리케이션입니다.

Spring Boot의 자동 설정(Auto Configuration)을 활용하여
REST API를 설계·구현하고,
HTML/CSS/JavaScript(fetch API)를 사용해 프론트엔드와 직접 연동함으로써
요청–응답 기반의 웹 애플리케이션 구조를 이해하는 것을 목표로 했습니다.

---

## 2. Tech Stack

### Backend
- **Java 21 (LTS)**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Maven**

### Frontend
- HTML
- CSS
- JavaScript (Fetch API)

---

## 3. Project Structure

본 프로젝트는 Spring Boot의 권장 패키지 구조를 따르며,  
각 레이어의 역할을 명확히 분리하여 구성했습니다.

```
src/main/java/com/sjdevlog/todolist
├ controller
├ service
├ repository
├ domain
├ dto
├ exception
└ TodolistApplication.java
```

### 각 패키지 역할
- **controller**  
  클라이언트의 HTTP 요청을 받아 Service 계층으로 전달하고,  
  처리 결과를 HTTP 응답으로 반환합니다.

- **service**  
  애플리케이션의 핵심 비즈니스 로직을 담당합니다.  
  데이터 검증, 처리 규칙, 트랜잭션 관리가 이루어집니다.

- **repository**  
  JPA를 사용하여 데이터베이스와 직접 통신하는 계층입니다.  
  CRUD 기능을 인터페이스 기반으로 처리합니다.

- **domain**  
  데이터베이스 테이블과 매핑되는 Entity 클래스가 위치합니다.

- **dto**  
  API 요청(Request)과 응답(Response)에 사용되는 객체로,  
  Entity를 직접 노출하지 않기 위해 사용합니다.

- **exception**

  애플리케이션 전반에서 사용되는 커스텀 예외와
  전역 예외 처리 로직(@ControllerAdvice)이 위치합니다.
  일관된 에러 응답 포맷을 제공하기 위해 사용됩니다.

---

## 4. Features

- Todo 생성 (웹 UI)
- Todo 목록 조회
- Todo 완료 / 미완료 상태 변경
- Todo 삭제
- Todo 수정 및 구현
- 입력값 검증 및 전역 예외 처리

---

## 5. API Example

### Todo 생성
POST `/api/todos`

``` json
{
  "title": "첫 할일",
  "description": "Spring Boot Todo API"
}
```

성공 응답
``` json
{
  "id": 1,
  "title": "첫 할일",
  "description": "Spring Boot Todo API",
  "completed": false,
  "createdAt": "2026-02-15T19:14:52",
  "updatedAt": "2026-02-15T19:14:52",
  "completedAt": null
}
``` 

에러 응답 예시
``` json
{
  "code": "VALIDATION_ERROR",
  "message": "title은 비어 있을 수 없습니다.",
  "status": 400,
  "path": "/api/todos",
  "timestamp": "2026-02-15T19:20:01"
}
```

---

## 6. API Design (Planned)

| Method | Endpoint | Description |
|------|---------|------------|
| POST | /api/todos | Todo 생성 |
| GET | /api/todos | Todo 목록 조회 |
| PATCH | /api/todos/{id}/toggle | Todo 완료/미완료 상태 토글 |
| DELETE | /api/todos/{id} | Todo 삭제 |

---

## 7. Database

- 개발 및 테스트 단계에서는 **H2 In-Memory Database**를 사용합니다.
- 별도의 설치 없이 빠르게 테스트가 가능하도록 구성했습니다.
- 추후 MySQL 등 관계형 데이터베이스로 전환할 수 있도록 설계되었습니다.

---

## 8. Getting Started

### 1) 프로젝트 실행
```bash
./mvnw spring-boot:run
```
또는 IntelliJ에서
TodolistApplication.java 파일 실행

### 2) 접속 주소
Web UI: http://localhost:8081/index.html
API Base URL: http://localhost:8081/api/todos

---

## 9. Development Environment

IDE: IntelliJ IDEA
Build Tool: Maven
JDK: Java 21 (LTS)

---

## 10. Technical Notes

- Controller, Service, Repository 계층을 분리하여
  요청 처리, 비즈니스 로직, 데이터 접근의 책임을 명확히 구분했습니다.

- API 응답 시 Entity를 직접 노출하지 않고 DTO를 사용하여,
  데이터베이스 모델과 외부 API 스펙을 분리했습니다.

- Service 계층에 `@Transactional`을 적용하여
  데이터 변경 작업의 원자성과 일관성을 보장했습니다.

- Todo 엔티티 내부에 상태 변경 메서드를 두어,
  완료/미완료 처리 규칙을 도메인 객체가 직접 관리하도록 설계했습니다.

- Jackson 기본 직렬화 옵션으로 인해 JSON 필드 순서가 알파벳 기준으로 정렬되는 문제를 확인했고,
  application.properties의 전역 설정을 통해 필드 정렬을 비활성화하여
  응답 구조의 가독성을 개선했습니다.

- `@ControllerAdvice`를 활용해 예외 처리를 전역으로 통합하고,
  일관된 에러 응답 포맷을 제공하도록 구현했습니다.

- `@Valid` 기반 입력값 검증을 적용하여
  잘못된 요청을 Controller 단계에서 차단하도록 구성했습니다.

---

## 11. Notes

본 프로젝트는 학습 목적으로 진행된 개인 프로젝트입니다.

백엔드 REST API 구현과 계층형 구조 설계에 집중했으며,
HTML/CSS/JavaScript 기반 프론트엔드를 직접 연동하여
전체 웹 애플리케이션 흐름을 구현했습니다.

API 예시는 구조 설명을 위한 예시이며,
실제 동작과 세부 스펙은 소스 코드 기준을 따릅니다.

---

## 12. Future Improvements

- REST API 테스트 코드 작성
- Todo 수정 API 추가
- 데이터베이스 MySQL 전환
- 사용자 인증 및 권한 관리 기능 추가
- 예외 처리 및 로깅 구조 개선
- UI/UX 개선

---

## 13. Author

GitHub: https://github.com/sjdevlog
