package br.com.peoplemanager.domain.usecase.person;

import br.com.peoplemanager.domain.gateway.PersonPersistence;
import br.com.peoplemanager.domain.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SavePersonTest {

    @InjectMocks
    private SavePerson savePerson;
    @Mock
    private PersonPersistence persistence;

    @Test
    void savePerson() {

        final Long expectedId = 1L;

        when(persistence.save(any()))
                .thenReturn(new Person(1L,"Full Name", LocalDate.parse("2000-07-07")));

        Person person =
                savePerson.execute(new Person("Full Name", LocalDate.parse("2000-07-07")));

        assertEquals(expectedId, person.getPersonId());
    }

}