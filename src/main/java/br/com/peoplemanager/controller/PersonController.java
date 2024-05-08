package br.com.peoplemanager.controller;

import br.com.peoplemanager.dto.person.PersonDto;
import br.com.peoplemanager.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public ResponseEntity<Void> savePerson(@RequestBody @Valid PersonDto personRequestDto, BindingResult bindingResult) {
        ValidRequest.valid(bindingResult);
        Long idPersonSaved = personService.savePerson(personRequestDto);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/".concat(String.valueOf(idPersonSaved)))
                .buildAndExpand(idPersonSaved)
                .toUri())
                .build();
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Void> updatePerson(@RequestBody @Valid PersonDto personRequestDto,
                                           @PathVariable("personId") Long personId, BindingResult bindingResult) {
        ValidRequest.valid(bindingResult);
        personService.updatePerson(personRequestDto, personId);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<PersonDto>> getPersons(
            @RequestParam(defaultValue = "") String names) {
        return ResponseEntity.ok().body(personService.listPerson(names));
    }
}
