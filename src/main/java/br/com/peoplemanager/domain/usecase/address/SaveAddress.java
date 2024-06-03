package br.com.peoplemanager.domain.usecase.address;

import br.com.peoplemanager.domain.gateway.AddressPersistence;
import br.com.peoplemanager.domain.usecase.person.GetPerson;
import br.com.peoplemanager.domain.entity.Person;
import br.com.peoplemanager.domain.entity.valueobject.Address;

public class SaveAddress {
    private final AddressPersistence repository;
    private final GetPerson getPerson;
    public SaveAddress(AddressPersistence repository, GetPerson getPerson) {
        this.repository = repository;
        this.getPerson = getPerson;
    }
    public Address execute(Long personId, Address address) {
        Person person = getPerson.execute(personId);
        person.changePrincipalAddress(address)
                .forEach(repository::save);
        address.setPerson(person);
        return repository.save(address);
    }


}
