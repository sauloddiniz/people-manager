package br.com.peoplemanager.domain.usecase.person;

import br.com.peoplemanager.domain.model.Person;

public class UpdatePerson {
    private final GetPerson getPerson;
    private final SavePerson savePerson;

    public UpdatePerson(GetPerson getPerson, SavePerson savePerson) {
        this.getPerson = getPerson;
        this.savePerson = savePerson;
    }

    public Person execute(Long personId, Person person) {
        Person personSave = getPerson.execute(personId);
        person.setPersonId(personSave.getPersonId());
        return savePerson.execute(person);
    }
}
