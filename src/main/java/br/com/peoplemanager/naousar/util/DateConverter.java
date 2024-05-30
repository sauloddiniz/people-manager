package br.com.peoplemanager.naousar.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConverter {

    private DateConverter(){}
    public static LocalDate converterStringToLocalDate(String date) {
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida: " + date);
        }
    }

    public static String converterLocalDateToString(LocalDate data) {
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return data.format(formatter);
    }
}
