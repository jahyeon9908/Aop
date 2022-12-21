package co.kr.mono.aop.ui;

import org.springframework.stereotype.Component;

@Component
public class MonoPrinter implements Printer {
    public MonoPrinter() {
    }

    public void print(String msg) {
        System.out.println("--- MonoPrinter Start ---");
        System.out.println(msg);
        System.out.println("--- MonoPrinter end ---");
    }
}

