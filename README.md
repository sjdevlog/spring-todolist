# Todo List Web Application

Spring Boot 기반의 **Todo List 웹 애플리케이션**입니다.  
이 프로젝트는 Spring Boot의 기본 구조와 웹 애플리케이션 개발 흐름을 이해하고,  
Controller–Service–Repository 계층 구조 및 JPA 기반 데이터베이스 연동을 학습하기 위해 진행한 개인 프로젝트입니다.

---

## 1. Project Overview

이 프로젝트는 백엔드 중심의 Todo 관리 애플리케이션으로,  
할 일(Todo)을 생성·조회·수정·삭제하는 기본적인 기능을 구현하는 것을 목표로 합니다.

Spring Boot의 자동 설정(Auto Configuration)과  
계층형 아키텍처(Layered Architecture)를 직접 적용해 보며,  
유지보수성과 확장성을 고려한 코드 구조를 연습하는 데 중점을 두었습니다.

---

## 2. Tech Stack

### Backend
- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Maven**

### Frontend (Planned)
- HTML
- CSS
- JavaScript

---

## 3. Project Structure

본 프로젝트는 Spring Boot의 권장 구조를 따르며,  
각 레이어의 역할을 명확히 분리하여 구성했습니다.

src/main/java/com/sjdevlog/todolist
- controller
- service
- repository
- main
- dto
- TodolistApplication.java


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

---

## 4. Features

### Current / Planned Features
- Todo 생성
- Todo 목록 조회
- Todo 완료 / 미완료 상태 변경
- Todo 삭제

---

## 5. API Design (Planned)

| Method | Endpoint | Description |
|------|---------|------------|
| POST | /api/todos | Todo 생성 |
| GET | /api/todos | Todo 목록 조회 |
| PATCH | /api/todos/{id} | Todo 상태 변경 |
| DELETE | /api/todos/{id} | Todo 삭제 |

---

## 6. Database

- 개발 및 테스트 단계에서는 **H2 In-Memory Database**를 사용합니다.
- 별도의 설치 없이 빠르게 테스트가 가능하도록 구성했습니다.
- 추후 MySQL 등 관계형 데이터베이스로 전환할 수 있도록 설계되었습니다.

---

## 7. Getting Started

### 1) 프로젝트 실행
bash
./mvnw spring-boot:run
또는 IntelliJ에서
TodolistApplication.java 파일 실행

### 2) 접속 주소
http://localhost:8080

---

## 8. Development Environment

IDE: IntelliJ IDEA
Build Tool: Maven
JDK: Java 17

---

## 9. Notes

본 프로젝트는 학습 목적으로 진행되었습니다.

초기 단계에서는 백엔드 로직 구현에 집중하며,
프론트엔드는 추후 단계적으로 추가할 예정입니다.

코드의 가독성과 구조적 이해를 최우선으로 고려했습니다.

---

## 10. Future Improvements

- 프론트엔드 UI 구현
- REST API 테스트 코드 작성
- 데이터베이스 MySQL 전환
- 사용자 인증 및 권한 관리 기능 추가
- 예외 처리 및 로깅 구조 개선

---

## 11. Author

GitHub: https://github.com/sjdevlog
