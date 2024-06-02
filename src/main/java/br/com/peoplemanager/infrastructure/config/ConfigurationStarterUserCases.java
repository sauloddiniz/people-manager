package br.com.peoplemanager.infrastructure.config;

import br.com.peoplemanager.application.gateway.AddressPersistence;
import br.com.peoplemanager.application.gateway.PersonPersistence;
import br.com.peoplemanager.application.usecase.address.ListAddressByIdPerson;
import br.com.peoplemanager.application.usecase.address.SaveAddress;
import br.com.peoplemanager.application.usecase.person.FilterPersons;
import br.com.peoplemanager.application.usecase.person.GetPerson;
import br.com.peoplemanager.application.usecase.person.ListPersons;
import br.com.peoplemanager.application.usecase.person.SavePerson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationStarterUserCases {
    @Bean
    public SavePerson savePersonUseCase(PersonPersistence repositoryGateway) {
        return new SavePerson(repositoryGateway);
    }

    @Bean
    public ListPersons listPersonUseCase(PersonPersistence repositoryGateway) {
        return new ListPersons(repositoryGateway);
    }

    @Bean
    public FilterPersons filterPersonsUseCase(PersonPersistence repositoryGateway) {
        return new FilterPersons(repositoryGateway);
    }

    @Bean
    public GetPerson getPersonsUseCase(PersonPersistence repositoryGateway) {
        return new GetPerson(repositoryGateway);
    }

    @Bean
    public SaveAddress saveAddressUseCase(AddressPersistence repositoryGateway, GetPerson getPerson) {
        return new SaveAddress(repositoryGateway, getPerson);
    }

    @Bean
    public ListAddressByIdPerson listAddressUseCase(AddressPersistence persistence) {
        return new ListAddressByIdPerson(persistence);
    }
}
