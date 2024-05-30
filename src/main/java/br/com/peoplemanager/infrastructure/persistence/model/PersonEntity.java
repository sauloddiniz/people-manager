package br.com.peoplemanager.infrastructure.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSON_ID")
    private Long personId;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<AddressEntity> addresses;
}
