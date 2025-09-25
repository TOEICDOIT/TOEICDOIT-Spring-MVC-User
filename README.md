# TOEICDOIT - 효과적인 토익 학습 웹 서비스

TOEICDOIT는 실력 측정 및 분석, 효율적인 학습, 실시간 채팅 커뮤니케이션을 통해 효과적인 TOEIC 학습을 지원하는 웹 서비스입니다.

- **프로젝트 GitHub:** [TOEICDOIT Organization](https://github.com/orgs/TOEICDOIT/repositories)
- **개인 기여 Repository:** [TOEICDOIT-Spring-MVC-User](https://github.com/TOEICDOIT/TOEICDOIT-Spring-MVC-User)

---

## 📅 프로젝트 개요

* **기간:** 2024.07.03 ~ 2024.08.09 (총 42일)
* **인원:** 총 5명 (프론트엔드 1명, 백엔드 4명)

---

## ✨ 핵심 기능

* **MSA 아키텍처 기반 서비스:** 확장성과 유지보수성을 고려한 마이크로서비스 아키텍처를 기반으로 서비스를 구축했습니다.
* **소셜 로그인:** Google 소셜 로그인 기능을 구현하여 사용자의 접근성을 높였습니다.
* **토익 모의고사 및 결과 분석:** 토익 모의고사 기능을 제공하며, 차트를 통해 학습 결과를 직관적으로 분석할 수 있습니다.
* **구독 서비스:** 결제를 통해 서비스를 구독하고 모든 기능을 이용할 수 있습니다.

---

## 🛠️ 사용 기술

| 구분 | 기술 스택 |
| :--- | :--- |
| **`Backend`** | `Java 17`, `Spring Boot`, `Spring Data JPA`, `Spring Cloud Gateway`, `Spring Netflix Eureka`, `QueryDSL`, `JPQL` |
| **`Frontend`** | `Typescript`, `React`, `Next.js 14`, `Tailwind CSS`, `Zustand`, `Chart.js` |
| **`Database & Messaging`** | `MySQL`, `H2`, `MongoDB`, `Redis`, `Apache Kafka` |
| **`CI/CD & Deployment`** | `Docker`, `Jenkins`, `NCloud Kubernetes Service`, `NCloud Server` |
| **`Tools & Communication`** | `IntelliJ IDEA`, `Swagger`, `Postman`, `Git`, `GitHub`, `Slack`, `Notion` |

---

## 👨‍💻 개인 기여 (20%)

백엔드 개발자로서 **User, Board, Reply 서비스**의 개발을 담당했습니다.

### **1. API 및 핵심 로직 구현**
* 유저, 게시판, 댓글 관련 **총 21개의 REST API를 설계하고 구현**했습니다.
* 유저(회원가입, 로그아웃, 수정, 탈퇴) 및 게시판/댓글의 CRUD 내부 로직을 구현했습니다.
* `User`, `Board`, `Reply`의 DTO와 Entity를 설계했습니다.
* 회원가입 시 **BCrypt 암호화 알고리즘**을 사용하여 비밀번호를 안전하게 저장했습니다.
* 회원 탈퇴 시 `CascadeType` 설정을 통해 연관된 게시글과 댓글 데이터가 함께 삭제되도록 하여 데이터 무결성을 유지했습니다.

### **2. 동적 쿼리 및 데이터베이스 최적화**
* **QueryDSL**을 활용하여 카테고리, 작성자, 키워드 등 다양한 조건에 따른 게시판 동적 검색 필터링 로직을 작성했습니다.
* `StringPath` 타입을 활용하여 특정 키워드에 따라 지정된 컬럼을 동적으로 업데이트하는 쿼리를 구현했습니다.
* `Pageable`을 사용하여 조회된 데이터에 페이지네이션을 적용했습니다.

### **3. 코드 표준화 및 예외 처리**
* **CQRS 패턴**을 활용하여 공통된 CRUD 기능을 인터페이스로 추상화하여 유지보수성과 재사용성을 높였습니다.
* API의 반환 데이터를 커스텀한 `Messenger` 형식으로 통일하여 일관성을 확보했습니다.
* **`@RestControllerAdvice`**를 사용하여 `UserException`과 같은 커스텀 예외를 처리하는 글로벌 예외 핸들러를 구현했습니다.

---

## 🚀 프로젝트 관리 및 설계

### **ERD (Entity-Relation Diagram)**
- 데이터베이스 모델링은 ERD Cloud를 사용하여 설계하고 팀원들과 실시간으로 공유했습니다.
- **[ERD Cloud 바로가기](https://www.erdcloud.com/d/D2eGzvEpMJhtfJhfE)**

### **일정 관리**
- **Scrum:** 일주일 단위의 스프린트를 적용하고, 칸반 보드를 통해 프로젝트 진행 상황(진행 전, 진행 중, 완료 등)을 시각화하여 관리했습니다.
- **Notion:** 프로젝트 관련 모든 문서(일정, API 규칙, 자료 공유 등)를 체계적으로 관리하기 위해 Notion을 적극적으로 활용했습니다.
- **[프로젝트 Notion 바로가기](https://6whistle.notion.site/TOEICDOIT-Project-8b0c5b625a4e49918c42f1dee097b446?pvs=4)**

---

## 🏛️ 시스템 아키텍처

![TOEICDOIT 프로젝트 아키텍처 다이어그램](https://raw.githubusercontent.com/seunghochoi9/resume/f64c7bd6421280a122676592eb4310752d3affd4/project%20daieogeulaem.png)

---

## 🎥 프로젝트 시연 영상

* **최종 발표 영상:** [시청하기](https://www.youtube.com/live/JMVk6B-iCn4?si=jFd5NMhS20ytcQho)
* **프로젝트 결과물 시연 영상:** [시청하기](https://youtu.be/eEsG4DDDx54)
* **개인 프로토타입 시연 영상:** [시청하기](https://youtu.be/reSqeeXBx6o?si=DU0wP9qAvUBj9MFs)
