package br.com.peoplemanager.domain.usecase.person;

import br.com.peoplemanager.domain.gateway.PersonPersistence;
import br.com.peoplemanager.domain.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilterPersonsTest {

    @InjectMocks
    FilterPersons filterPersons;

    @Mock
    PersonPersistence persistence;

    @Test
    void executeListPerson() {

        List<Person> expectedPersons =
                new ArrayList<>();
        expectedPersons.add(new Person(1L,"Jhon Doe", LocalDate.parse("2000-01-01")));
        expectedPersons.add(new Person(2L,"Jhon Fine", LocalDate.parse("1998-01-01")));
        expectedPersons.add(new Person(3L,"Jhon Low", LocalDate.parse("1999-01-01")));

        when(persistence.filterPerson(List.of("")))
                .thenReturn(expectedPersons);

        List<Person> actualPersons = filterPersons.execute("");

        assertEquals(expectedPersons, actualPersons);
    }

}