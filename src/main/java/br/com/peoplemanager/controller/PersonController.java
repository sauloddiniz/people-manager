package br.com.peoplemanager.controller;

import br.com.peoplemanager.dto.person.PersonRequestDto;
import br.com.peoplemanager.dto.person.PersonResponseDto;
import br.com.peoplemanager.exception.PersonErrorException;
import br.com.peoplemanager.repository.PersonRepository;
import br.com.peoplemanager.service.PersonService;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public ResponseEntity<Void> savePerson(@RequestBody @Valid PersonRequestDto personRequestDto, BindingResult bindingResult) {
        validPersonRequest(bindingResult);
        Long idPersonSaved = personService.savePerson(personRequestDto);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/".concat(String.valueOf(idPersonSaved)))
                .buildAndExpand(idPersonSaved)
                .toUri())
                .build();
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Void> updatePerson(@RequestBody @Valid PersonRequestDto personRequestDto,
                                           @PathVariable("personId") Long personId, BindingResult bindingResult) {
        validPersonRequest(bindingResult);
        personService.updatePerson(personRequestDto, personId);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<PersonResponseDto>> ListPerson(
            @RequestParam(defaultValue = "") String names) {
        List<PersonResponseDto> listToResponse = personService.listPerson(names);
        return ResponseEntity.ok().body(listToResponse);
    }

    private void validPersonRequest(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            final String error = bindingResult.getAllErrors().get(0).getDefaultMessage();
            final String field = bindingResult.getFieldErrors().get(0).getField();
            throw new PersonErrorException(field.concat(": ").concat(Objects.requireNonNull(error)));
        }
    }

}
