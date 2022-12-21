package co.kr.mono.aop.ui;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Primary
@Component
public class BluePrinter implements Printer {
    public BluePrinter() {
    }

    public void print(String msg) {
        System.out.println("--- BluePrinter Start ---");
        System.out.println(msg);
        System.out.println("--- BluePrinter end ---");
    }
}