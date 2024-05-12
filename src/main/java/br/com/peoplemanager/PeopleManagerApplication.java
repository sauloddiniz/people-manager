package br.com.peoplemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PeopleManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PeopleManagerApplication.class, args);
    }

}
