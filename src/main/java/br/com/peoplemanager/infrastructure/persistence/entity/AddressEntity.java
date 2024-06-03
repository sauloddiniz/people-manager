package br.com.peoplemanager.infrastructure.persistence.entity;

import br.com.peoplemanager.domain.entity.valueobject.Address;
import br.com.peoplemanager.domain.enums.StateEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long addressId;
    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity person;
    @Column(name = "STREET")
    private String street;
    @Column(name = "ZIP_CODE")
    private String zipCode;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private StateEnum state;
    @Column(name = "PRINCIPAL")
    private Boolean principal;

    public AddressEntity(Address address) {
        this.addressId = address.getAddressId();
        this.person = new PersonEntity(address.getPerson());
        this.street = address.getStreet();
        this.zipCode = address.getZipCode();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.state = address.getState();
        this.principal = address.getPrincipal();
    }

    public Address toModel() {
        return new Address(this.addressId, this.street, this.zipCode,
                this.number, this.city, this.state, this.principal, this.person.toModelNoAddress());
    }
}
