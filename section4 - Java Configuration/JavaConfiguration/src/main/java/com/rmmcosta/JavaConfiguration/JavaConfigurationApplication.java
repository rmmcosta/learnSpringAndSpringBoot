package com.rmmcosta.JavaConfiguration;

import com.rmmcosta.JavaConfiguration.controllers.DatabaseController;
import com.rmmcosta.JavaConfiguration.controllers.FactoryController;
import com.rmmcosta.JavaConfiguration.services.DatabaseFactory;
import com.rmmcosta.JavaConfiguration.services.DatabaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JavaConfigurationApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(JavaConfigurationApplication.class, args);
		DatabaseController databaseController = ctx.getBean(DatabaseController.class);
		databaseController.connect();
		/*DatabaseService databaseService = (DatabaseService) ctx.getBean("factoryController", "oracle");
		databaseService.connect();*/
	}

}
