package br.com.peoplemanager.infrastructure.persistence.entity;

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
}
