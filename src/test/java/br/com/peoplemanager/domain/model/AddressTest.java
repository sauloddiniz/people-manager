package br.com.peoplemanager.domain.model;

import br.com.peoplemanager.domain.enums.StateEnum;
import br.com.peoplemanager.domain.exception.AddressInvalidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AddressTest {

    @Test
    void shouldThrowErrorWhenCreateAddressWithStreetIsEmpty() {
        try {
            new Address(1L, "", "35170-009", "264",
                    "Coronel Fabriciano", StateEnum.MG, true);
            fail();
        } catch (Exception exception) {
            assertEquals(AddressInvalidException.class, exception.getClass());
        }
    }

    @Test
    void shouldThrowErrorWhenCreateAddressWithStreetIsNull() {
        try {
            new Address(1L, null, "35170-009", "264",
                    "Coronel Fabriciano", StateEnum.MG, true);
            fail();
        } catch (Exception exception) {
            assertEquals(AddressInvalidException.class, exception.getClass());
        }
    }

    @Test
    void shouldThrowErrorWhenCreateAddressWithCityIsNull() {
        try {
            new Address(1L, "Rua A", "35170-009", "264",
                    null, StateEnum.MG, true);
            fail();
        } catch (Exception exception) {
            assertEquals(AddressInvalidException.class, exception.getClass());
        }
    }

    @Test
    void shouldThrowErrorWhenCreateAddressWithCityIsEmpty() {
        try {
            new Address(1L, "Rua A", "35170-009", "264",
                    "", StateEnum.MG, true);
            fail();
        } catch (Exception exception) {
            assertEquals(AddressInvalidException.class, exception.getClass());
        }
    }

    @Test
    void shouldThrowErrorWhenCreateAddressWithZipCodeIsNull() {
        try {
            new Address(1L, "Rua A", null, "264",
                    "Coronel Fabriciano", StateEnum.MG, true);
            fail();
        } catch (Exception exception) {
            assertEquals(AddressInvalidException.class, exception.getClass());
        }
    }

    @Test
    void shouldThrowErrorWhenCreateAddressWithZipCodeIsEmpty() {
        try {
            new Address(1L, "Rua A", "", "264",
                    "Coronel Fabriciano", StateEnum.MG, true);
            fail();
        } catch (Exception exception) {
            assertEquals(AddressInvalidException.class, exception.getClass());
        }
    }

    @Test
    void shouldThrowErrorWhenCreateAddressWithNumberIsNull() {
        try {
            new Address(1L, "Rua A", "35170-000", null,
                    "Coronel Fabriciano", StateEnum.MG, true);
            fail();
        } catch (Exception exception) {
            assertEquals(AddressInvalidException.class, exception.getClass());
        }
    }

    @Test
    void shouldThrowErrorWhenCreateAddressWithNumberIsEmpty() {
        try {
            new Address(1L, "Rua A", "35170-000", "",
                    "Coronel Fabriciano", StateEnum.MG, true);
            fail();
        } catch (Exception exception) {
            assertEquals(AddressInvalidException.class, exception.getClass());
        }
    }

}