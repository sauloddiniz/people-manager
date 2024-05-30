package br.com.peoplemanager.infrastructure.controller;

import br.com.peoplemanager.application.usecase.person.FilterPersons;
import br.com.peoplemanager.application.usecase.person.GetPerson;
import br.com.peoplemanager.application.usecase.person.ListPersons;
import br.com.peoplemanager.application.usecase.person.SavePerson;
import br.com.peoplemanager.domain.entity.Person;

import br.com.peoplemanager.domain.entity.dto.PersonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*")
public class PersonController {
    private final SavePerson savePerson;
    private final ListPersons listPersons;
    private final FilterPersons filterPersons;
    private final GetPerson getPerson;
    public PersonController(SavePerson savePerson, ListPersons listPersons, FilterPersons filterPersons, GetPerson getPerson) {
        this.savePerson = savePerson;
        this.listPersons = listPersons;
        this.filterPersons = filterPersons;
        this.getPerson = getPerson;
    }

    @PostMapping
    public ResponseEntity<Void> createPerson(@RequestBody PersonDto personDto) {
        Person person = savePerson.execute(personDto);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/".concat(String.valueOf(person.getPersonId())))
                .buildAndExpand(person.getPersonId())
                .toUri())
                .build();
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Void> updatePerson(PersonDto personRequestDto, @PathVariable("personId") Long personId) {

        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<PersonDto>> getPersons() {
        return ResponseEntity.ok().body(listPersons.execute());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PersonDto>> filterPersons(@RequestParam("names") String names) {
        return ResponseEntity.ok().body(filterPersons.execute(names));
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("personId") Long personId) {
        return ResponseEntity.ok().body(getPerson.execute(personId));
    }
}
