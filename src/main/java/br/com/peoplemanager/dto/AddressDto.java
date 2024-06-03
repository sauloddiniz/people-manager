package br.com.peoplemanager.dto;
public record AddressDto(Long id, String street, String zipCode, String number,
                         String city, String state, Boolean principal) {}
