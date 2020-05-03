package com.rmmcosta.HelloWorld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class HelloWorldApplication {
    private static ApplicationContext ctx;

    public static void main(String[] args) {
        ctx = SpringApplication.run(HelloWorldApplication.class, args);
        int option;
        while (true) {
            option = acquireOption();
            switch (option) {
                case 1:
                    greet(HelloWorld.class, "Ana");
                    break;
                case 2:
                    greet(HelloSimple.class, "Ana");
                    break;
                case 0:
                    System.out.println("GoodBye...");
                    System.exit(0);
                default:
                    System.out.println("Not a valid option!");
            }
        }

    }

    private static int acquireOption() {
        System.out.println("Please tell us your option:\n 1-Hello World\n 2-Simple Hello\n 0-Exit");
        System.out.print("#:");
        Scanner scanner = new Scanner(System.in);
        try {
            int option = scanner.nextInt();
            return option;
        } catch (Exception e) {
            return -1;
        }
    }

    private static void greet(Class<? extends GreetingService> greetingService, String who) {
        GreetingService greeting = ctx.getBean(greetingService);
        greeting.greet(who);
    }

}
