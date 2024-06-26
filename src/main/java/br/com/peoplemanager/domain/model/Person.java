package br.com.peoplemanager.domain.model;

import br.com.peoplemanager.domain.exception.PersonRequestBirthDateException;
import br.com.peoplemanager.domain.exception.PersonRequestFullNameException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private Long personId;
    private String fullName;
    private LocalDate birthDate;
    private List<Address> addresses;

    public Person(String fullName, LocalDate birthDate) {
        validFullName(fullName);
        validBirthDate(birthDate);
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public Person(Long personId, String fullName, LocalDate birthDate, List<Address> addresses) {
        validFullName(fullName);
        validBirthDate(birthDate);
        this.addresses = addresses;
        this.personId = personId;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public Person(Long personId, String fullName, LocalDate birthDate) {
        validFullName(fullName);
        validBirthDate(birthDate);
        this.personId = personId;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    private void validBirthDate(LocalDate birthDate) {
        validBirthDateIsNotNull(birthDate);
        validateAgeIsAtLeast18(birthDate);
    }

    private static void validBirthDateIsNotNull(LocalDate birthDate) {
        if (birthDate == null) {
            throw new PersonRequestBirthDateException("Birth date cannot be null");
        }
    }

    private static void validateAgeIsAtLeast18(LocalDate birthDate) {
        final LocalDate currentDate = LocalDate.now();
        final int year = Period.between(birthDate, currentDate).getYears();
        if (birthDate.isAfter(currentDate) || year < 18) {
            throw new PersonRequestBirthDateException("The person must be at least 18 years old. BirthDate: " + year);
        }
    }

    private void validFullName(String fullName) {
        if (fullName == null || fullName.isBlank() || !fullName.matches("^\\w+\\s+\\w+.*$")) {
            throw new PersonRequestFullNameException("Name cannot be empty and must contain at least two words.");
        }
    }

    public List<Address> changePrincipalAddress(Address address) {
        List<Address> principalAddress = new ArrayList<>();
        if (!this.addresses.isEmpty() && Boolean.TRUE.equals(address.getPrincipal())) {
            principalAddress = this.addresses
                    .stream()
                    .filter(Address::getPrincipal).toList();
            turnFalsePrincipalAddress(principalAddress);
        }
        return principalAddress;
    }

    private void turnFalsePrincipalAddress(List<Address> principalAddress) {
        principalAddress.forEach(addr -> {
            addr.setPerson(this);
            addr.setPrincipal(false);
        });
    }

    public Address getPrincipalAddress() {
       for (Address address : this.addresses) {
           if (Boolean.TRUE.equals(address.getPrincipal())) {
               return address;
           }
       }
       return null;
    }

}
