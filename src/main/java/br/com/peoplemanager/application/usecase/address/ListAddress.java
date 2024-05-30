package br.com.peoplemanager.application.usecase.address;

import br.com.peoplemanager.application.gateway.AddressRepositoryGateway;
import br.com.peoplemanager.application.usecase.person.GetPerson;
import br.com.peoplemanager.domain.entity.dto.AddressDto;
import br.com.peoplemanager.domain.entity.dto.PersonDto;

import java.util.List;

public class ListAddress {
    private final AddressRepositoryGateway repository;
    private final GetPerson getPerson;

    public ListAddress(AddressRepositoryGateway repository, GetPerson getPerson) {
        this.repository = repository;
        this.getPerson = getPerson;
    }
    public List<AddressDto> execute(Long personId) {
        PersonDto person = getPerson.execute(personId);
        return repository.findAllByIdPerson(person.personId());
    }
}
