package br.com.peoplemanager.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateConverterTest {

    @Test
    void shouldThenThrowErrorConverterDate() {
        final String invalidDate = "2024-20-20";
        try {
            DateConverter.converterStringToLocalDate(invalidDate);
            fail();
        } catch (Exception exception) {
            assertEquals(IllegalArgumentException.class, exception.getClass());
        }
    }

}