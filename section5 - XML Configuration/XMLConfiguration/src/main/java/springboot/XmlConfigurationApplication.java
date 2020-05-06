package springboot;

import com.rmmcosta.XMLConfiguration.controllers.HelloController;
import com.rmmcosta.XMLConfiguration.services.AnimalFactory;
import com.rmmcosta.XMLConfiguration.services.DogFactory;
import com.rmmcosta.XMLConfiguration.services.HelloWorld;
import com.rmmcosta.XMLConfiguration.controllers.DatabaseController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@SpringBootApplication
@ImportResource("classpath:spring/spring-config.xml")
public class XmlConfigurationApplication {

    public static void main(String[] args) throws FileNotFoundException {
        ApplicationContext ctx = SpringApplication.run(XmlConfigurationApplication.class, args);
        HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
        helloWorld.sayHello();
        String fileName = "spring/spring-config.xml";
        File file = ResourceUtils.getFile("classpath:" + fileName);
        //File is found
        System.out.println("File Found : " + file.exists());

        DatabaseController databaseController = ctx.getBean(DatabaseController.class);
        databaseController.connect();

        AnimalFactory animalFactory = ctx.getBean(DogFactory.class);
        animalFactory.getAnimal().makeNoise();

        HelloController helloController = ctx.getBean(HelloController.class);
        System.out.println(helloController.getHello());
    }

}
