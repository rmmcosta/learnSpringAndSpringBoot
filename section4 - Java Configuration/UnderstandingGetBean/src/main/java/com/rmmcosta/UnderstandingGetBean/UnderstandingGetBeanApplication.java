package com.rmmcosta.UnderstandingGetBean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UnderstandingGetBeanApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(UnderstandingGetBeanApplication.class, args);
		Object lion = context.getBean("lion");

		System.out.println(lion.getClass().getSimpleName());

		Tiger tiger = (Tiger) context.getBean("tiger", "Siberian");
		Tiger secondTiger = (Tiger) context.getBean("tiger", "Striped");

		System.out.println(tiger.getName());
		System.out.println(secondTiger.getName());

		String[] tigerParams = {"tiger","ui"};
		Animal animal = (Animal) context.getBean("animalFactory", tigerParams);
		System.out.println(animal.getName());
		String[] lionParams = {"lion", "xi"};
		animal = (Animal) context.getBean("animalFactory", lionParams);
		System.out.println(animal.getName());
	}

}
