package br.com.peoplemanager.config;

import br.com.peoplemanager.domain.gateway.AddressPersistence;
import br.com.peoplemanager.domain.gateway.PersonPersistence;
import br.com.peoplemanager.domain.usecase.address.*;
import br.com.peoplemanager.domain.usecase.person.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigUserCases {

    @Bean
    public SavePerson savePerson(PersonPersistence repositoryGateway) {
        return new SavePerson(repositoryGateway);
    }

    @Bean
    public UpdatePerson updatePerson(GetPerson getPerson, SavePerson savePerson) {
        return new UpdatePerson(getPerson, savePerson);
    }

    @Bean
    public FilterPersons filterPersons(PersonPersistence repositoryGateway) {
        return new FilterPersons(repositoryGateway);
    }

    @Bean
    public GetPerson getPerson(PersonPersistence repositoryGateway) {
        return new GetPerson(repositoryGateway);
    }

    @Bean
    public SaveAddress saveAddress(AddressPersistence repositoryGateway, GetPerson getPerson) {
        return new SaveAddress(repositoryGateway, getPerson);
    }

    @Bean
    public ListAddressByIdPerson listAddressByIdPerson(AddressPersistence persistence) {
        return new ListAddressByIdPerson(persistence);
    }

    @Bean
    public UpdateAddress updateAddress(GetAddress getAddress, SaveAddress saveAddress) {
        return new UpdateAddress(getAddress, saveAddress);
    }

    @Bean
    public GetAddress getAddress(AddressPersistence persistence) {
        return new GetAddress(persistence);
    }

    @Bean
    public UpdatePrincipalAddress updatePrincipalAddress(GetAddress getAddress, SaveAddress saveAddress) {
        return new UpdatePrincipalAddress(getAddress, saveAddress);
    }
}
