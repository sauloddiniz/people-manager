package br.com.peoplemanager.service;

import br.com.peoplemanager.dto.person.PersonDto;
import br.com.peoplemanager.entity.Person;
import br.com.peoplemanager.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Sql(scripts = {"/schema.sql"})
@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    void create() {
      List<PersonDto> person = personService.listPerson("");
      System.out.println("");
    }
}