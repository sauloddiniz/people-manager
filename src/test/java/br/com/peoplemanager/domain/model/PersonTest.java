package br.com.peoplemanager.domain.model;

import br.com.peoplemanager.domain.enums.StateEnum;
import br.com.peoplemanager.domain.exception.PersonRequestBirthDateException;
import br.com.peoplemanager.domain.exception.PersonRequestFullNameException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void shouldSuccessCreatePersonOnlyTwoParameters() {
        Person person = new Person("Saulo Dias", LocalDate.parse("2000-07-07"));

        assertNotNull(person);
    }

    @Test
    void shouldSuccessCreatePersonOnlyThreeParameters() {
        Person person = new Person(1L,"Saulo Dias", LocalDate.parse("2000-07-07"));

        assertNotNull(person);
    }
    @Test
    void shouldThrowErrorWhenNameNotContainsTwoWords() {
        try {
            new Person("Saulo", LocalDate.parse("1986-02-07"));
            fail();
        } catch (Exception exception) {
            assertEquals(PersonRequestFullNameException.class, exception.getClass());
        }
    }

    @Test
    void shouldThrowErrorWhenNameIsEmpty() {
        try {
            new Person(1L, "", LocalDate.parse("1986-02-07"));
            fail();
        } catch (Exception exception) {
            assertEquals(PersonRequestFullNameException.class, exception.getClass());
        }
    }

    @Test
    void shouldThrowErrorWhenNameIsNull() {
        try {
            new Person(1L, null, LocalDate.parse("1986-02-07"));
            fail();
        } catch (Exception exception) {
            assertEquals(PersonRequestFullNameException.class, exception.getClass());
        }
    }

    @Test
    void shouldThrowErrorWhenBirthdayIsLessThen18() {
        try {
            new Person(1L, "Saulo Dias", LocalDate.parse("2020-02-07"));
            fail();
        } catch (Exception exception) {
            assertEquals(PersonRequestBirthDateException.class, exception.getClass());
        }
    }

    @Test
    void shouldThrowErrorWhenBirthdayIsGreaterDate() {
        try {
            new Person(1L, "Saulo Dias", LocalDate.parse("2040-02-07"));
            fail();
        } catch (Exception exception) {
            assertEquals(PersonRequestBirthDateException.class, exception.getClass());
        }
    }

    @Test
    void shouldThrowErrorWhenBirthdayIsNull() {
        try {
            new Person(1L, "Saulo Dias", null);
            fail();
        } catch (Exception exception) {
            assertEquals(PersonRequestBirthDateException.class, exception.getClass());
        }
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
    void shouldGetEmptyListNotContainsPrincipalAddress() {

        Person person = new Person(1L, "Full Name",
                LocalDate.parse("2000-07-07"), List.of());

        Address address = new Address(3L, "Rua 3", "35170-009",
                "264", "Fabriciano", StateEnum.MG, true);

        List<Address> addresses = person.changePrincipalAddress(address);

        assertEquals(0, addresses.size());
    }

    @Test
    void shouldReturnNullWhenNotContainsMainAddressWhenListEmpty() {
        Person person = new Person(1L, "Full Name",
                LocalDate.parse("2000-07-07"), new ArrayList<>());

        assertNull(person.getPrincipalAddress());
    }

    @Test
    void shouldReturnNullWhenNotContainsMainAddress() {
        Person person = new Person(1L, "Full Name",
                LocalDate.parse("2000-07-07"), List.of(new Address(3L, "Rua 3", "35170-009",
                "264", "Fabriciano", StateEnum.MG, false)));

        assertNull(person.getPrincipalAddress());
    }

    @Test
    void whenInsertNewMainAddressMustUpdatePrevious() {

        final long expectedTotalAddressPrincipal = 0;

        Person person = getPerson();

        Address newMainAddress = new Address(3L, "Rua 3", "35170-009",
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

        Address newAddress = new Address(3L, "Rua 3", "35170-009",
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