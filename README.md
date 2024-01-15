# 포도당

<p align="center">
    <img src="docs/image/logo.png" width="200px" height="200px" alt="logo" />
</p>

<p align="center">
  <img src = "https://img.shields.io/badge/development-in%20progress-yellow">
</p>

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.21-7F52FF?style=flat&logo=kotlin)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.1-6DB33F?style=flat&logo=spring-boot)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-3.2.1-6DB33F?style=flat&logo=spring)
![OpenFeign](https://img.shields.io/badge/Open_Feign-4.1.0-orange?style=flat)
![Kotest](https://img.shields.io/badge/Kotest-5.6.0-7F52FF?style=flat)
![Spring Cloud Contract](https://img.shields.io/badge/Spring_Cloud_Contract-4.0.1-6DB33F?style=flat&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0.33-4479A1?style=flat&logo=mysql)
![Java JWT](https://img.shields.io/badge/Java_JWT-4.4.0-000000?style=flat&logo=json-web-tokens)
![JWKS RSA](https://img.shields.io/badge/JWKS_RSA-0.22.1-000000?style=flat&logo=json-web-tokens)

## 목차

- [📝 서비스 개요](#-서비스-개요)
- [🛰️ 주요 기능](#-핵심-기능-및-사용-흐름)
- [📚 용어 사전](#-용어-사전)
- [👨‍💻 유스케이스](#-유즈케이스)
- [🏗️ 아키텍처](#-아키텍처)
- [👷🏻 구현](#-구현)
- [📝 API 문서](#-api-문서)
- [🛢️ ERD](#-erd)
- [📐 시퀀스 다이어그램](#-시퀀스-다이어그램)
- [📚 참고 자료](#-참고-자료)

## 📝 서비스 개요

많은 사람들이 일상에서 마주하는 물건, 장소, 사건 등에 대해 이야기하고 싶어하지만, 이에 대해 토론하거나 정보를 공유할 플랫폼을 찾는데 어려움이 있습니다. **포도당**은
사용자가 현재 경험하고 있는 것들에 대해 즉시 대화를 나눌 수 있도록, **사진을 업로드하면, AI를 통해 해당 이미지를 분석해 적절한 채팅방을 추천**하고 실시간으로 대화를
나눌수 있는 플랫폼입니다.

## 🛰️ 핵심 기능 및 사용 흐름

**이미지 기반 채팅방 검색**

- 검색창 이미지 업로드 버튼 → 추천 채팅방 목록 확인 → 채팅방 입장 → (로그인 팝업) → 채팅방 메시지 전송

**문장 기반 채팅 검색**

- 검색창 문장 입력 → 추천 채팅방 목록 확인 → 채팅방 입장 → (로그인 팝업) → 채팅방 메시지 전송

**채팅방 개설**

- 채팅방 개설 버튼 → 채팅방 정보 입력(제목, 설명) → 유사 채팅방 추천 → 정보기반 채팅방 이미지 자동 생성 → (추가 정보 입력) → 개설

**이미지 기반 채팅방 개설**

- 채팅방 개설 버튼 → 이미지 업로드 → 유사 채팅방 추천 →. 이미지 기반 채팅방 정보 자동 입력 → (추가 정보 입력) → 개설

## 📚 용어 사전

<details>
<summary><b>유저 (User)</b></summary>

| 한글명        | 영문명                  | 용어 설명                   |
|------------|----------------------|-------------------------|
| 게스트        | guest                | 로그인 하지 않은 사용자           |
| 유저         | user                 | 로그인 한 사용자               |
| 이름         | name                 | 유저 이름                   |
| 이메일        | email                | 유저 이메일 주소               |
| 상태메시지      | status message       | 유저 상태 메시지               |
| 가입일        | created at           | 가입일, 날짜 (UTC)           |
| 수정일        | updated at           | 수정일, 날짜 (UTC)           |
| 프로필 이미지    | picture              | 사용자 프로필 이미지             |
| 소셜 로그인 제공자 | provider             | 소셜로그인 제공자(카카오, 구글, …)   |
| 유저 로그인     | user login           | 사용자 로그인 유스케이스           |
| 유저 가입      | register user        | 사용자 회원 가입 유스케이스         |
| 유저 프로필 조회  | get user profile     | ID 값으로 사용자 프로필 조회 유스케이스 |
| 유저 탈퇴      | deactivate user      | 유저 탈퇴 유스케이스             |
| 탈퇴된 유저 정보  | deactivate user info | 탈퇴된 유저 정보               |

</details>

<details>
<summary><b>채팅 (Chat)</b></summary>

| 한글명           | 영문명                         | 용어 설명               |
|---------------|-----------------------------|---------------------|
| 채빙방 제목        | title                       | 채팅방 제목, 문자열         |
| 채팅방 설명        | description                 | 채팅방 설명, 문자열         |
| 채팅방 이미지 URL   | picture                     | 채팅방 대표 이미지 URL      |
| 채팅 보내기        | send chat message           | 새로운 채팅 전송 유스케이스     |
| 채팅방 입장        | join chat room              | 채팅방 입장 유스케이스        |
| 채팅방 생성        | create chat room            | 채팅방 생성 유스케이스        |
| 채팅방 나가기       | leave chat room             | 채팅방 나가기 유스케이스       |
| 채팅방 삭제        | delete chat room            | 채팅방 삭제 유스케이스        |
| 채팅 메세지        | chat message                | 채팅 메세지              |
| 메시지 내용        | content                     | 채팅 메시지 내용, 문자열      |
| 보낸 시각         | sent at                     | 메시지 보낸 시각, 날짜 (UTC) |
| 채팅방 유저 목록 조회  | list chat room users        | 채팅방 유저 목록 조회 유스케이스  |
| 채팅 메시지 저장     | save chat message           | 채팅 메시지 저장 유스케이스     |
| 채팅 메시지 전송     | send chat message           | 채팅 메시지 전송 유스케이스     |
| 참여중인 채팅방      | joined chat room            | 유저가 참여중인 채팅방        |
| 채팅방 유저        | chat room users             | 채팅방 유저              |
| 채팅방 이미지 기반 검색 | search chat room by image   | 채팅방 이미지 기반 검색 유스케이스 |
| 채팅방 키워드 기반 검색 | search chat room by keyword | 채팅방 키워드 기반 검        |

</details>

<details>
<summary><b>알림 (Notification)</b></summary>

| 한글명         | 영문명               | 용어 설명              |
|-------------|-------------------|--------------------|
| 받는 사람의 아이디  | recipient id      | 알림을 받는 사람의 아이디     |
| 알림 타입       | type              | 알림의 종류             |
| 알림 제목       | title             | 알림의 제목             |
| 알림 내용       | content           | 알림의 내용             |
| 읽음 상태       | is read           | 알림 읽음 상태           |
| 발송 일시       | sent at           | 알림 발송 시각, 날짜 (UTC) |
| 알림 읽음       | read notification | 알림 읽음 유스케이스        |
| 알림 발송 유스케이스 | send notification | 알림 발송 유스케이스        |
| 알림 저장 유스케이스 | save notification | 알림 저장 유스케이스        |

</details>

## 👨‍💻 유즈케이스

<details>
<summary><b>유저(User)</b></summary>

- 게스트는 로그인할 수 있다. (소셜로그인)
- 게스트는 회원가입 할 수 있다.
- 회원은 자신의 정보를 수정할 수 있다.
- 회원은 자신의 정보를 조회할 수 있다.
- 회원은 회원탈퇴할 수 있다.

</details>

<details>
<summary><b>채팅(Chat)</b></summary>

- 게스트, 유저는 키워드를 기반으로 채팅방을 추천받을 수 있다.
- 게스트, 유저는 이미지를 기반으로 채팅방을 추천받을 수 있다.
- 게스트, 유저는 채팅방의 상세 정보를 확인할 수 있다.
- 유저는 채팅방에 입장할 수 있다.
- 유저는 채팅방에 메시지를 전송할 수 있다.
- 유저는 채팅방을 나갈 수 있다.
- 유저는 자신이 참여중인 채팅방의 새로운 메시지에 대한 알림을 받을 수 있다.
- 유저는 자신이 참여중인 채팅방의 새로운 메시지에 대한 알림을 끌 수 있다.
- 유저는 다른 회원의 프로필을 확인할 수 있다.
- 유저는 채팅방을 개설할 수 있다.
- 유저는 이미지 기반으로 채팅방을 개설할 수 있다.
- 채팅방 방장은 채팅방을 삭제할 수 있다.
- 채팅방 방장은 채팅방의 정보를 수정할 수 있다.

</details>

<details>
<summary><b>알림(Notification)</b></summary>

- 회원은 참여중인 채팅방의 새로운 메시지에 대한 알림을 받을 수 있다.
- 회원은 채팅방 알림을 끌 수 있다.
- 회원은 전체 알림을 끌 수 있다.

</details>

## 🏗️ 아키텍처

### 시스템 아키텍처

![img.png](docs/image/system_architecture.png)

## 🚀 구현

### 1. 소셜 로그인 -> OIDC

**기술 선택**

- OIDC
    - OAuth 2.0의 인증 및 인가 과정을 단순화한 프로토콜.
    - OAuth 2.0의 Authorization Code Flow에 사용자 정보를 포함하고 있는 ID Token을 같이 발급받아 사용자 정보를 확인할 수 있음.
    - Access Token을 통해 사용자 정보를 Provider의 리소스 서버에 사용자 정보를 요청하는 과정이 필요 없음.
- Open Feign
    - Spring Security의 OAuth 2.0 Client도 사용할 수 있지만, Spring Security 의존성들도 포함되기 때문에, client모듈에 사용하기
      부적합
    - Open Feign은 외부 API를 호출하기 위한 HTTP Client로 어노테이션 기반으로 선언적으로 사용할 수 있어 편리함.

**구현**

- Java JWT 라이브러리를 래핑하여 프로젝트에 적용
- Open Feign을 사용해 ID Token을 발급
- 발급된 ID Token을 통해 사용자 정보를 확인
    - JSON key set의 요청은 Guava Cache를 사용해 캐싱하여 요청 횟수를 줄임

![oidc flow](docs/image/oidc_flow.png)

### 2. 이미지 분석을 통한 채팅방 추천

...Vector DB, ChatGPT API

### 3. 실시간 채팅 기능

...

## 📝 API 문서

...

## 🛢️ ERD

...

## 📐 시퀀스 다이어그램

...

## 📚 참고 자료

...
