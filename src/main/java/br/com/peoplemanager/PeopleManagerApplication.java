package br.com.peoplemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PeopleManagerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(PeopleManagerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(
                PeopleManagerApplication.class);
        springApplication.run(args);
    }


    @RestController
    public static class WarInitializerController {

        @GetMapping("/")
        public String handler() {
            return "Hello World";
        }
    }

}


