# TOEIC 학습 플랫폼: TOEICDOIT
사용자의 실력 측정부터 맞춤형 학습 경로 추천, 실시간 소통까지 지원하는 MSA 기반의 종합 토익 학습 웹 서비스입니다.

---

### 🗓️ 프로젝트 정보
* **프로젝트 기간:** 2024.07.03 ~ 2024.08.09 (총 42일)
* **팀 구성:** 총 5명 (백엔드 4명, 프론트엔드 1명)
* **GitHub Organization:** [TOEICDOIT Repositories](https://github.com/orgs/TOEICDOIT/repositories)

---

### ✨ 핵심 기능
| 기능 | 상세 내용 |
| :--- | :--- |
| **회원 관리** | 로컬 회원가입 및 Google 소셜 로그인을 지원하며, 안전한 비밀번호 암호화(BCrypt) 및 JWT 기반 인증을 제공합니다. |
| **모의고사 및 분석** | 사용자는 토익 모의고사를 응시할 수 있으며, Chart.js를 활용한 시각적 차트를 통해 성적 분석 결과를 확인할 수 있습니다. |
| **커뮤니티** | 사용자들이 자유롭게 정보를 교환할 수 있는 게시판 및 댓글 기능을 제공합니다. |
| **서비스 구독** | 결제 시스템을 연동하여 유료 구독 모델을 통해 프리미엄 학습 콘텐츠를 제공합니다. |
| **MSA 아키텍처** | 각 서비스(User, Toeic, Chat 등)를 독립적인 Microservice로 분리하여 개발 및 배포의 유연성과 확장성을 확보했습니다. |
| **CI/CD** | Jenkins, Docker, NCloud를 활용하여 빌드, 테스트, 배포 과정을 자동화하여 안정적이고 효율적인 개발 환경을 구축했습니다. |

---

### 🚀 프로젝트 관리 및 기획
* **요구사항 분석 및 기능 명세:** Notion을 활용하여 팀원들과 함께 API 명세, DB 스키마(ERD) 등 프로젝트 요구사항을 정의하고 구체화했습니다.
* **애자일 방법론 적용:** Scrum과 Kanban 보드를 활용하여 일주일 단위의 스프린트로 업무를 계획하고, 매일 진행 상황을 공유하며 이슈에 신속하게 대응했습니다.
* **일정 및 리스크 관리:** 프로젝트 전체 일정을 계획하고 각 기능별 개발 우선순위를 정하며, 잠재적 리스크를 사전에 파악하고 해결 방안을 논의했습니다.

---

### 👨‍💻 주요 역할 및 기여
저는 이번 프로젝트에서 User, Board, Reply 서비스의 백엔드 시스템 설계 및 개발을 담당했습니다. **(개인 기여도 25%)**

1.  **사용자 중심 기능 기획 및 구현**
    * **사용자 인증 시스템 설계:** 사용자 계정 관리의 핵심 로직을 설계하며, `BCrypt` 암호화와 `DTO` 설계를 통해 **사용자 개인정보 보호 정책**을 수립하고 보안을 강화했습니다.
    * **커뮤니티 검색 기능 고도화:** `QueryDSL`을 활용하여 **다양한 사용자 요구사항(카테고리, 키워드 등)에 대응**할 수 있는 유연한 검색 및 필터링 기능을 기획하고 구현했습니다.

2.  **안정적이고 확장 가능한 시스템 설계**
    * **개발 생산성 향상:** 반복되는 CRUD 로직을 `CQRS 패턴` 기반으로 모듈화하여 코드 재사용성을 높이고, 팀원들의 **개발 생산성을 향상**시키는 데 기여했습니다.
    * **안정적인 서비스 운영 설계:** `글로벌 예외 처리`를 도입하여 **예상치 못한 오류 발생 시 서비스가 중단되지 않고** 안정적으로 운영될 수 있는 기반을 마련했습니다.

---

### 🛠️ 기술 스택
| 구분 | 기술 |
| :--- | :--- |
| **Back-End** | Java 17, Spring Boot, Spring Webflux, Spring Data JPA, QueryDSL, Spring Security, JWT, OAuth 2.0 |
| **Front-End** | TypeScript, React, Next.js 14, Zustand, React-Query, Tailwind CSS, Chart.js |
| **Database** | MySQL 8.0, MongoDB, Redis, H2 |
| **MSA & Infra** | Spring Cloud Gateway, Netflix Eureka, Apache Kafka |
| **CI/CD & DevOps**| Docker, Jenkins, NCloud Kubernetes Service (NKS), NCloud Load Balancer, NCloud Object Storage |
| **Tools** | IntelliJ IDEA, Git, GitHub, Postman, Swagger, Slack, **Notion** |
