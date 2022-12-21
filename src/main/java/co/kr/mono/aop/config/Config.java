package co.kr.mono.aop.config;


import co.kr.mono.aop.entity.ExamScore;
import co.kr.mono.aop.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;

@Configuration
@ComponentScan(basePackages = "co.kr.mono.aop") //4개의 어노테이션 붙은 거 다 등록해줌
@EnableAspectJAutoProxy //프록시를 만들어주는 어노테이션
public class Config {

    @Bean
    Person person(){
        System.out.println("프록시가 언제인가");
        ArrayList<ExamScore> examScoreList = new ArrayList<>();
        examScoreList.add(new ExamScore("1", 100, 97, 80));
        examScoreList.add(new ExamScore("2", 70, 93, 90));
        examScoreList.add(new ExamScore("3", 86, 90, 50));

        Person person = new Person();
        person.setName("홍길동");
        person.setMajor("영문학");
        person.setExamList(examScoreList);

        return person; //person이란 이름의 빈 만들어짐  그 안에는 홍길동의 정보가 있겠지
    }

}
