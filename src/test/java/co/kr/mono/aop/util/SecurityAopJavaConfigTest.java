package co.kr.mono.aop.util;
import co.kr.mono.aop.config.Config;
import co.kr.mono.aop.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class) //이미 config()에 들려서 person(홍길동) 정보를 가지고 있다
class SecurityAopTest {

    @Autowired
    private ApplicationContext context;
    private Person person;

    @BeforeEach
    public void setUp() {
        String[] ctx = context.getBeanDefinitionNames();
        System.out.println("무슨 빈을 가졌나 : "+ Arrays.toString(ctx));
        System.out.println("배열 크기 : "+ctx.length);
        person = context.getBean(Person.class);

    }

    @Test
    @DisplayName("App-JavaConfig 정상실행: 클라이언트 person.print() 호출")
    public void aopNoParamTest() {
        System.out.println("\n### STEP 0 : 클라이언트 person.getTot() 호출");
        person.print(); //객체를 부를 때마다 프록시 발생
        //person.print();
    }

    @Test
    @DisplayName("App-JavaConfig 정상실행: 클라이언트 person.print(1) 호출")
    public void aopParamTest() {
        System.out.println("\n### STEP 0 : 클라이언트 person.getTot(1) 호출");
        Set<Map.Entry<String, Float>> entries = person.getTot("1").entrySet();//set 안에 map 넣기
        System.out.println("\n### Client 결과 : "+entries);

    }

    @Test
    @DisplayName("Aop-javaconfig 클라이언트 printThrowException() 호출")

    public void aopExceptionTest(){
        System.out.println("\n### STEP0 : 클라이언트 printThrowException() 호출");
        person.printThrowException();

    }










}