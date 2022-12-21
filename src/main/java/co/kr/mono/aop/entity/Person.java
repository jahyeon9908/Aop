package co.kr.mono.aop.entity;

import co.kr.mono.aop.ui.Printer;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString


public class Person {
 //이미 setter가 호출해줘서 autowired 필요없음
    private String name;
    private String major;
    private ArrayList<ExamScore> examList;

    @Autowired
    private Printer printer;

    public void print(){

        String str = "Person [name=" + name
                + ", major = " +major +"]\n"
                +"ExamList = "+examList+"]";
        printer.print(str);
    }

    public Map<String, Float> getTot(){
        ArrayList<ExamScore> exams = this.getExamList();

        Map<String, Float> map = exams.stream()
                .collect(Collectors.toMap(exam->exam.getGrade() //collector가 리턴해주는 map (for문, if문 필요없음)(키가 그레이드 값이 평균)
                        , exam->(float)(exam.getKor() + exam.getEng() + exam.getMat())/3));
        System.out.println("getTotal method 내부");
        return map;
    }

    public Map<String, Float> getTot(String grade){
        ArrayList<ExamScore> exams = this.getExamList();

        Map<String, Float> map = exams.stream().filter(exam -> exam.getGrade().equals(grade))
                .collect(Collectors.toMap(exam -> exam.getGrade()
                        ,exam -> (float)(exam.getKor() + exam.getMat() + exam.getEng())/3));
        // Collectors.toMap 메서드는 각각 맵의 키와 값을 생산하는 두 함수 인자를 받는다.

        return map;
    }

    public void printThrowException(){
        throw new IllegalArgumentException("=> 고의로 발생시켰음");
    }


}
