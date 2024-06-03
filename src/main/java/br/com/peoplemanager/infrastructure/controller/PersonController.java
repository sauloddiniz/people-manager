package br.com.peoplemanager.infrastructure.controller;

import br.com.peoplemanager.domain.model.Person;

import br.com.peoplemanager.dto.PersonDto;
import br.com.peoplemanager.domain.usecase.person.FilterPersons;
import br.com.peoplemanager.domain.usecase.person.GetPerson;
import br.com.peoplemanager.domain.usecase.person.SavePerson;
import br.com.peoplemanager.domain.usecase.person.UpdatePerson;
import br.com.peoplemanager.dto.mapper.PersonDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {
    private final SavePerson savePerson;
    private final FilterPersons filterPersons;
    private final GetPerson getPerson;
    private final UpdatePerson updatePerson;

    public PersonController(SavePerson savePerson, FilterPersons filterPersons, GetPerson getPerson, UpdatePerson updatePerson) {
        this.savePerson = savePerson;
        this.filterPersons = filterPersons;
        this.getPerson = getPerson;
        this.updatePerson = updatePerson;
    }

    @PostMapping
    public ResponseEntity<Void> createPerson(@RequestBody PersonDto personDto) {
        Person person = savePerson.execute(PersonDtoMapper.toModel(personDto));
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/".concat(String.valueOf(person.getPersonId())))
                        .buildAndExpand(person.getPersonId())
                        .toUri())
                .build();
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Void> updatePerson(
            @RequestBody PersonDto personRequest,
            @PathVariable("personId") Long personId) {
        updatePerson.execute(personId, PersonDtoMapper.toModel(personRequest));
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<PersonDto>> filterPersons(
            @RequestParam(value = "names", defaultValue = "", required = false) String names) {
        List<Person> personList = filterPersons.execute(names);
        return ResponseEntity.ok().body(personList
                .stream()
                .map(PersonDtoMapper::toDto)
                .toList());
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("personId") Long personId) {
        return ResponseEntity.ok().body(PersonDtoMapper.toDto(getPerson.execute(personId)));
    }
}
