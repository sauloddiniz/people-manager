package br.com.peoplemanager.domain.usecase.person;

import br.com.peoplemanager.domain.model.Person;
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
class UpdatePersonTest {

    @InjectMocks
    UpdatePerson updatePerson;
    @Mock
    GetPerson getPerson;
    @Mock
    SavePerson savePerson;

    @Test
    void executeUpdatePerson() {

        Person expectedPerson =
                new Person(1L, "John Doe", LocalDate.parse("1999-01-01"));

        Person PersonSave =
                new Person(1L, "John Tramontine", LocalDate.parse("2000-01-01"));

        when(getPerson.execute(1L))
                .thenReturn(PersonSave);
        when(savePerson.execute(any(Person.class)))
                .thenReturn(expectedPerson);

        Person updatedPerson = updatePerson.execute(1L, expectedPerson);

        assertEquals(expectedPerson.getPersonId(), updatedPerson.getPersonId());
        assertEquals(expectedPerson.getFullName(), updatedPerson.getFullName());
        assertEquals(expectedPerson.getBirthDate(), updatedPerson.getBirthDate());
    }
}