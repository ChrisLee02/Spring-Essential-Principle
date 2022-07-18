package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("pure DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출 시 객체 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출 시 객체 생성
        MemberService memberService2 = appConfig.memberService();
        Assertions.assertThat(memberService1).isEqualTo(memberService2);
    }
    @Test
    @DisplayName("싱글톤 패턴 이용")
    void singletonServiceTest() {
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        Assertions.assertThat(instance1).isSameAs(instance2);
        
        //Same은 ==호출
        //Equal은 equal호출
    }
    @Test
    @DisplayName("spring DI 컨테이너")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //1. 조회: 호출 시 객체 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        //2. 조회: 호출 시 객체 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }


}
