//package br.com.peoplemanager.util;
//
//import br.com.peoplemanager.naousar.util.DateConverter;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class DateConverterTest {
//
//    @Test
//    void shouldThenThrowErrorConverterDate() {
//        final String invalidDate = "2024-20-20";
//        try {
//            DateConverter.converterStringToLocalDate(invalidDate);
//            fail();
//        } catch (Exception exception) {
//            assertEquals(IllegalArgumentException.class, exception.getClass());
//        }
//    }
//
//}