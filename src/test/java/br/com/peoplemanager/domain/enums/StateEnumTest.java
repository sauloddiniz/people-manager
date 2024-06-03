package br.com.peoplemanager.domain.enums;

import br.com.peoplemanager.domain.enums.StateEnum;
import br.com.peoplemanager.domain.exception.StateEnumConverterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateEnumTest {

    @Test
    @DisplayName("Should then return full name state for input abbreviation")
    void ShouldReturnFullNameStateInputAbbreviation() {
        final String expectedValue = "Minas Gerais";

        final StateEnum result = StateEnum.fromString("MG");

        assertEquals(expectedValue, result.getName());
    }

    @Test
    @DisplayName("Should then return full name state for input abbreviation")
    void ShouldReturnFullNameStateInputIgnoreCase() {
        final String expectedValue = "Minas Gerais";

        final StateEnum result = StateEnum.fromString("minas gerais");

        assertEquals(expectedValue, result.getName());
    }

    @Test
    @DisplayName("Should return the full name of the state when inputting a value without accents")
    void ShouldReturnFullNameStateWheInputtingValueWithoutAccents() {
        final String expectedValue = "Rondônia";

        final StateEnum result = StateEnum.fromString("Rondonia");

        assertEquals(expectedValue, result.getName());
    }
    @Test
    @DisplayName("Should then throw error when input invalid state")
    void ShouldThenTrowStateEnumConverterErrorWhenInputInvalidState() {
        assertThrows(StateEnumConverterException.class, () -> {
            StateEnum.fromString("Minas");
        });
    }
}