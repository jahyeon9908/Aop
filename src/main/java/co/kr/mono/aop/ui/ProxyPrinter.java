package co.kr.mono.aop.ui;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

    public class ProxyPrinter implements Printer {

        Printer printer;

        public ProxyPrinter(Printer printer){
            this.printer = printer;
        }


        @Override
        public void print(String msg) {
            System.out.println("===========Before===========");
            System.out.println("===========Around Start===========");
            printer.print(msg);
            System.out.println("===========Around End===========");
            System.out.println("===========After============");
        }
    }

