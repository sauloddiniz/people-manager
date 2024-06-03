package br.com.peoplemanager.infrastructure.persistence.entity;

import br.com.peoplemanager.domain.entity.Person;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    public PersonEntity(Person person) {
        this.personId = person.getPersonId();
        this.fullName = person.getFullName();
        this.birthDate = person.getBirthDate();
    }
    public Person toModel() {
        return new Person(this.personId, this.fullName, this.birthDate,
                Optional.ofNullable(this.addresses)
                        .stream()
                        .flatMap(Collection::stream)
                        .map(AddressEntity::toModel)
                        .toList());

    }

    public Person toModelNoAddress() {
        return new Person(this.personId, this.fullName, this.birthDate);
    }
}
