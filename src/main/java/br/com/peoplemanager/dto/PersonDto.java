package br.com.peoplemanager.dto;

import java.time.LocalDate;

public record PersonDto(Long personId, String fullName, LocalDate birthDate,
                        AddressDto principalAddress) {}
