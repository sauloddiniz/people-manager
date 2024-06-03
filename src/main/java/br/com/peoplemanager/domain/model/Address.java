package br.com.peoplemanager.domain.model;

import br.com.peoplemanager.domain.enums.StateEnum;
import br.com.peoplemanager.domain.exception.AddressInvalidException;

public class Address {
    private Long addressId;
    private String street;
    private String zipCode;
    private String number;
    private String city;
    private StateEnum state;
    private boolean principal;
    private Person person;

    public Address(String street, String zipCode, String number, String city, StateEnum state, Boolean principal, Person person) {
        validStreet(street);
        validZipCode(zipCode);
        validNumber(number);
        validCity(city);
        this.street = street;
        this.zipCode = zipCode;
        this.number = number;
        this.city = city;
        this.state = state;
        this.principal = principal;
        this.person = person;
    }

    public Address(Long addressId, String street, String zipCode, String number, String city, StateEnum state, Boolean principal) {
        validStreet(street);
        validZipCode(zipCode);
        validNumber(number);
        validCity(city);
        this.addressId = addressId;
        this.street = street;
        this.zipCode = zipCode;
        this.number = number;
        this.city = city;
        this.state = state;
        this.principal = principal;
    }

    public Address(Long addressId, String street, String zipCode, String number, String city, StateEnum state,
                   Boolean principal, Person person) {
        validStreet(street);
        validCity(city);
        validZipCode(zipCode);
        validNumber(number);
        this.addressId = addressId;
        this.street = street;
        this.zipCode = zipCode;
        this.number = number;
        this.city = city;
        this.state = state;
        this.principal = principal;
        this.person = person;
    }

    private void validCity(String city) {
        if (city == null || city.isBlank()) {
            throw new AddressInvalidException("City not empty");
        }
    }

    private void validNumber(String number) {
        if (number == null || number.isBlank()) {
            throw new AddressInvalidException("Number not empty");
        }
    }
    private void validZipCode(String zipCode) {
        if (zipCode == null || zipCode.isBlank()) {
            throw new AddressInvalidException("Zipcode not empty");
        }
    }
    private void validStreet(String street) {
        if (street == null || street.isBlank()) {
            throw new AddressInvalidException("Street not empty");
        }
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", state=" + state +
                ", principal=" + principal +
                '}';
    }
}
