package co.kr.mono.aop.advice;

import co.kr.mono.aop.util.UtilLib;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect //aop의 공통기능 클래스 //@Before 등 어드바이스에는 @Aspect가 써있어야함
@Component //반드시 Component 등의 Spring Bean으로 등록해야 Aspect가 제대로 적용됨
public class SecurityAop {
    public void displayLine() {
        System.out.println(UtilLib.lpad("ㅁ", 50, "-"));
    }

    @Before("execution(public * co.kr.mono.aop.entity.Person.*(..))") //점이 1개면 파라미터 1개 2개면 몇 개든 상관 없음 3개면 3개
    public void logBefore(JoinPoint joinPoint) {

        String signatureStr = joinPoint.getSignature().toString();

        displayLine() ;
        System.out.println("### => 공통코드 실행중");
        System.out.println("### STEP1 : <aop:before> : 메서드 실행 전에 적용되는 어드바이스");
        System.out.println("### STEP1 : " + signatureStr + " 시작전");
        //void co.kr.mono.aop.entity.Person.print()
        //Map co.kr.mono.aop.entity.Person.getTot(String)
        displayLine();
    }


    @Around("execution(public * co.kr.mono.aop.entity.Person.*(..))") //public 옆에 *은 파라미터의 데이터타입이 상관없다
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable { //프록시가 대신 실행할 객체의 권한이 넘어온다 //proceedingJoinPoint가 권한을 위임받아서 타겟 호출
        String signatureStr = joinPoint.getSignature().toString();
        displayLine();
        System.out.println("### STEP2 : <aop:around> : 메서드 호출 이전, 이후, 예외 발생 등 모든 시점에 적용 가능한 어드바이스 정의");
        System.out.println("### STEP2 : " + signatureStr + " around 시작.");
        //signatureStr : void co.kr.mono.aop.entity.Person.print()
        //public * co.kr.mono.aop.entity.Person.*(..))이 안에 있는 메소드가 실행될 때 발동되는 건데
        //그 안에 여러가지 메소드가 있잖아
        //그것을 구별해서 보여주는 게 getSignature() : //겟시그니처: 어떤 메소드한테 정보를 보내려던건지 캐치함
        //joinpoint에는 target의 모든 메소드가 들어있고 그 중 어떤 메소드인지 getSignature()가 잡아옴

        //joinPoint : caller가 원래 메소드를 호출할 때의 정보를 받는 곳
        //어드바이스는 값을 외부에선 받을 수 있지만 리턴x 종속성이 생기면 안 되니까


        long timeMilli = new Date().getTime();
        System.out.println("어디에서");

        try {
            // 핵심기능
            System.out.println("비포로 가는가");
            Object result =  joinPoint.proceed();  //joinPoint.proceed()가 권한을 넘겨주면 타겟 메소드 실행하고 result에 리턴값 담김
            //권한 주기 (조인포인트가 타겟의 메소드를 실행할 수 있는 권한)
            // joinpoint를 실행(타겟의 메소드) //근데 타겟 메소드 전에 before를 실행하니까 before로 가는 거
            //Object result = null; //권한을 받아서 아무것도 실행 안 시키기 //어디서 준거지 proceed를 안 했는데
            System.out.println("여기 전에?"+result); //여기서 @Before로 넘어감
            return result;
        } finally {
            System.out.println("??");
            System.out.println(String.format("실행시간 : %d ms",new Date().getTime() - timeMilli));
            System.out.println("### STEP2 : " + signatureStr + " around 종료.");
            displayLine();
        }
    }

    @AfterReturning(pointcut = "execution(public * co.kr.mono.aop.entity.Person.*(..))", returning = "retVal") //리터닝과 리턴값의 변수 이름이 같아야한 다(retVal)
    public void logAfterReturning(JoinPoint joinPoint, Object retVal) { //Object retVal 리턴값을 받음 무슨 타입인지 모르므로 오브젝트 나중에 형변환해서 써라

        String signatureStr = joinPoint.getSignature().toString();
        displayLine();

        System.out.println("### STEP3 : " + "<aop:after-returning> : 메서드가 정상적으로 실행된 후에 " +
                "적용되는 어드바이스 정의");
        System.out.println("### STEP3 : " + signatureStr + "\n### : 실행 결과 [" + retVal+ "]");
        displayLine();
    }

    @AfterThrowing(pointcut = "execution(public * co.kr.mono.aop.entity.Person.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint ,Exception ex) {

        String signatureStr = joinPoint.getSignature().toString();
        System.out.println("JOINPOINT : "+signatureStr);
        displayLine();

        System.out.println("### STEP4 : <aop:after-throwing> : 메서드가 예외를 발생시킬 때 " +
                "적용되는 어드바이스를 정의");
        System.out.println("### STEP4 : 예외발생  => " + ex.toString());
        displayLine();
    }

    @After("execution(public * co.kr.mono.aop.entity.Person.*(..))") //위에서 오류가 나더라도 항상 실행되는 after
    public void logAfter(JoinPoint joinPoint) {
        String signatureStr = joinPoint.getSignature().toString();
        displayLine();

        System.out.println("### STEP5 : <aop:after> : 메서드가 정상적으로 실행되는지 " +
                "또는 에외를 발생시키는지 여부에 상관없이 실행하는어드바이스를 정의");
        System.out.println("### STEP5 : " + signatureStr + " 종료.");
        displayLine();
    }

}