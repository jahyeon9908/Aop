package co.kr.mono.aop.ui;

import org.springframework.stereotype.Component;

@Component
public class ColorPrinter implements Printer {
    public ColorPrinter() {
    }

    public void print(String msg) {
        System.out.println("--- ColorPrint Start ---");
        System.out.println(msg);
        System.out.println("--- ColorPrint end ---");
    }
}
