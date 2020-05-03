package com.rmmcosta.UnderstandingGetBean.config;

import com.rmmcosta.UnderstandingGetBean.Animal;
import com.rmmcosta.UnderstandingGetBean.AnimalFactory;
import com.rmmcosta.UnderstandingGetBean.Lion;
import com.rmmcosta.UnderstandingGetBean.Tiger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
class AnnotationConfig {

    @Bean(name = {"tiger", "kitty"})
    @Scope(value = "prototype")
    Tiger getTiger(String name) {
        return new Tiger(name);
    }

    @Bean(name = "animalFactory")
    @Scope(value = "prototype")
    Animal getAnimal(String animalType, String name) {
        return new AnimalFactory(animalType, name).getAnimal();
    }

    @Bean(name = "lion")
    Lion getLion() {
        return new Lion("Hardcoded lion name");
    }
}
