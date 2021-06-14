# spring reactive web 학습 저장소

참고자료: 스프링 부트 실전 활용 마스터(원제: Hacking with Spring Boot 2.3: Reactive Edition)

Spring Web Flux를 학습해봅시다.

추후, Reactive Web을 활용한 채팅 서버를 구축하는데 참고하기 위함입니다.

## 스프링 애플리케이션에서 빈을 등록하는 방식

1. 빈으로 등록될 클래스의 물리적 위치와 무관하게 환경설정 클래스(`@Configuration`) 에서 직접 하나 하나 빈으로 등록한다.
2. 빈의 존재를 플래그로 표시만 하고(`@Component`) 컴포넌트 탐색(`@ComponentScan`)을 통해 자동으로 빈을 찾아내고 등록되게 할 수도 있다.

xml로도 설정가능하지만 최근에는 그렇게 자주 사용되는 방법은 아니고, 결국엔 비슷한 방법이라 두 가지라고 표현한 듯 싶다.
