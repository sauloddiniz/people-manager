package br.com.peoplemanager.domain.model;

import br.com.peoplemanager.domain.enums.StateEnum;
import br.com.peoplemanager.domain.exception.PersonRequestBirthDateException;
import br.com.peoplemanager.domain.exception.PersonRequestFullNameException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonTest {

    @Test
    void shouldThrowErrorWhenNameNotContainsTwoWords() {
        assertThrows(PersonRequestFullNameException.class, () -> {
            new Person(1L, "Saulo", LocalDate.parse("1986-02-07"));
        });
    }

    @Test
    void shouldThrowErrorWhenNameIsEmpty() {
        assertThrows(PersonRequestFullNameException.class, () -> {
            new Person(1L, "", LocalDate.parse("1986-02-07"));
        });
    }

    @Test
    void shouldThrowErrorWhenNameIsNull() {
        assertThrows(PersonRequestFullNameException.class, () -> {
            new Person(1L, null, LocalDate.parse("1986-02-07"));
        });
    }

    @Test
    void shouldThrowErrorWhenBirthdayIsLessThen18() {
        assertThrows(PersonRequestBirthDateException.class, () -> {
            new Person(1L, "Saulo Dias", LocalDate.parse("2020-02-07"));
        });
    }

    @Test
    void shouldThrowErrorWhenBirthdayIsNull() {
        assertThrows(PersonRequestBirthDateException.class, () -> {
            new Person(1L, "Saulo Dias", null);
        });
    }

    @Test
    void shouldGetMainAddress() {

        final String expectedStreet = "Rua B";
        final Long expectedId = 2L;

        Person person = getPerson();

        Address principalAddress = person.getPrincipalAddress();

        assertEquals(expectedStreet, principalAddress.getStreet());
        assertEquals(expectedId, principalAddress.getAddressId());
    }

    @Test
    void whenInsertNewMainAddressMustUpdatePrevious() {

        final long expectedTotalAddressPrincipal = 0;

        Person person = getPerson();

        Address newMainAddress =  new Address(3L, "Rua 3", "35170-009",
                "264", "Fabriciano", StateEnum.MG, true);

        person.changePrincipalAddress(newMainAddress);

        long totalPrincipalAddress =
                person.getAddresses().stream().filter(Address::getPrincipal).count();

        assertEquals(expectedTotalAddressPrincipal, totalPrincipalAddress);
    }

    @Test
    void whenInsertNewAddressDoNotMustUpdatePrevious() {

        final long expectedTotalAddressPrincipal = 1;

        Person person = getPerson();

        Address newAddress =  new Address(3L, "Rua 3", "35170-009",
                "264", "Fabriciano", StateEnum.MG, false);

        person.changePrincipalAddress(newAddress);

        long totalPrincipalAddress =
                person.getAddresses().stream().filter(Address::getPrincipal).count();

        assertEquals(expectedTotalAddressPrincipal, totalPrincipalAddress);
    }

    private static Person getPerson() {
        List<Address> addressList = List.of(
                new Address(1L, "Rua A", "35170-009", "264", "Fabriciano",
                        StateEnum.MG, false),
                new Address(2L, "Rua B", "35170-009", "264", "Fabriciano",
                        StateEnum.MG, true)
        );

        return new Person(1L, "Saulo Dias", LocalDate.parse("1986-02-07"), addressList);
    }
}