package dev.amir.resourceprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "dev.amir.resourceprocessor.framework",
        "dev.amir.resourceprocessor.application",
        "dev.amir.resourceprocessor.domain"
})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
