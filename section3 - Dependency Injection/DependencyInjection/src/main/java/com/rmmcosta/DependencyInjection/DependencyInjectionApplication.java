package com.rmmcosta.DependencyInjection;

import com.rmmcosta.DependencyInjection.controllers.GreetingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
public class DependencyInjectionApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DependencyInjectionApplication.class, args);
		GreetingController controller = ctx.getBean(GreetingController.class);
		String msg = controller.getMessage();
		System.out.println(msg);
	}

}
