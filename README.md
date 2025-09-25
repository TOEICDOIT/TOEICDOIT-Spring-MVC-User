TOEIC 학습 플랫폼: TOEICDOIT
사용자의 실력 측정부터 맞춤형 학습 경로 추천, 실시간 소통까지 지원하는 MSA 기반의 종합 토익 학습 웹 서비스입니다.

🗓️ 프로젝트 정보
프로젝트 기간: 2024.07.03 ~ 2024.08.09 (총 42일)

팀 구성: 총 5명 (백엔드 4명, 프론트엔드 1명)

GitHub Organization: TOEICDOIT Repositories

프로젝트 관리: Notion

✨ 핵심 기능
기능

상세 내용

회원 관리

로컬 회원가입 및 Google 소셜 로그인을 지원하며, 안전한 비밀번호 암호화(BCrypt) 및 JWT 기반 인증을 제공합니다.

모의고사 및 분석

사용자는 토익 모의고사를 응시할 수 있으며, Chart.js를 활용한 시각적 차트를 통해 성적 분석 결과를 확인할 수 있습니다.

커뮤니티

사용자들이 자유롭게 정보를 교환할 수 있는 게시판 및 댓글 기능을 제공합니다.

서비스 구독

결제 시스템을 연동하여 유료 구독 모델을 통해 프리미엄 학습 콘텐츠를 제공합니다.

MSA 아키텍처

각 서비스(User, Toeic, Chat 등)를 독립적인 Microservice로 분리하여 개발 및 배포의 유연성과 확장성을 확보했습니다.

CI/CD

Jenkins, Docker, NCloud를 활용하여 빌드, 테스트, 배포 과정을 자동화하여 안정적이고 효율적인 개발 환경을 구축했습니다.

🏛️ 시스템 아키텍처
프로젝트 포트폴리오에 포함된 아키텍처 다이어그램 이미지입니다.

🛠️ 기술 스택
구분

기술

Back-End

Java 17, Spring Boot, Spring Webflux, Spring Data JPA, QueryDSL, Spring Security, JWT, OAuth 2.0

Front-End

TypeScript, React, Next.js 14, Zustand, React-Query, Tailwind CSS, Chart.js

Database

MySQL 8.0, MongoDB, Redis, H2

MSA & Infra

Spring Cloud Gateway, Netflix Eureka, Apache Kafka

CI/CD & DevOps

Docker, Jenkins, NCloud Kubernetes Service (NKS), NCloud Load Balancer, NCloud Object Storage

Tools

IntelliJ IDEA, Git, GitHub, Postman, Swagger, Slack, Notion

👨‍💻 저의 주요 역할 및 기여
저는 이번 프로젝트에서 User, Board, Reply 서비스의 백엔드 시스템 설계 및 개발을 담당했습니다. (개인 기여도 20%)

1. 핵심 비즈니스 로직 및 API 개발 (총 21개 REST API 구현)

User Service:

회원가입, 로그아웃, 정보 수정, 회원 탈퇴 등 사용자 계정 관리의 핵심 로직을 구현했습니다.

BCrypt를 사용해 비밀번호를 안전하게 암호화하고, 민감 정보를 제외한 DTO를 설계하여 보안을 강화했습니다.

Board & Reply Service:

게시글 및 댓글의 CRUD(Create, Read, Update, Delete) 기능을 구현했습니다.

QueryDSL을 활용한 동적 쿼리를 작성하여 카테고리, 작성자, 키워드 등 다양한 조건의 검색 및 필터링 기능을 구현하고, 페이징 처리를 적용했습니다.

부모-자식 엔티티 간의 연관관계를 명확히 설정하여 데이터 정합성을 유지했습니다.

2. 안정적인 백엔드 시스템 설계

공통 기능 모듈화: 여러 서비스에서 반복적으로 사용되는 CRUD 로직을 CQRS 패턴 기반의 인터페이스로 추상화하고, 커스텀 응답 포맷(Messenger)을 통일하여 코드 재사용성과 유지보수성을 높였습니다.

글로벌 예외 처리: @RestControllerAdvice를 사용하여 비즈니스 로직에서 발생하는 예외를 일관되게 처리하고, 명확한 상태 코드와 메시지를 클라이언트에 전달하도록 설계했습니다.

DTO 및 Entity 설계: 클라이언트와의 데이터 교환 및 데이터베이스 영속성을 고려하여 User, Board, Reply 관련 DTO와 Entity를 설계했습니다.

3. MSA 환경에서의 인증/인가 구조 기여

Spring Cloud Gateway에서 JWT 토큰 검증 및 Spring Security 인증/인가 로직을 통합 처리하도록 설계에 기여했습니다.

이를 통해 각 개별 서비스(User, Board 등)는 인증 로직의 부담을 덜고 핵심 비즈니스 로직에만 집중할 수 있는 구조를 마련했습니다.

이 README는 제공된 'TOEICDOIT_프로젝트 포토폴리오.pdf' 문서를 기반으로 작성되었습니다.
