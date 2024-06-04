package br.com.peoplemanager.domain.usecase.person;

import br.com.peoplemanager.domain.gateway.PersonPersistence;
import br.com.peoplemanager.domain.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPersonTest {

    @InjectMocks
    GetPerson getPerson;

    @Mock
    PersonPersistence persistence;

    @Test
    void executeGetPerson() {

        Person person =
                new Person(1L, "John Doe", LocalDate.parse("2000-07-07"));

        when(persistence.findById(1L))
                .thenReturn(Optional.of(person));

        Person result = getPerson.execute(1L);

        assertEquals(person, result);
    }

}