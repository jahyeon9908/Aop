package co.kr.mono.aop.entity;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString

public class ExamScore {
    private String grade;
    private int kor;
    private int eng;
    private int mat;
}
