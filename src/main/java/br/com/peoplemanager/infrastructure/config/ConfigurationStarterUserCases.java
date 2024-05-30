package br.com.peoplemanager.infrastructure.config;

import br.com.peoplemanager.application.gateway.AddressRepositoryGateway;
import br.com.peoplemanager.application.gateway.PersonRepositoryGateway;
import br.com.peoplemanager.application.usecase.address.ListAddress;
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
    public SavePerson savePersonUseCase(PersonRepositoryGateway repositoryGateway) {
        return new SavePerson(repositoryGateway);
    }
    @Bean
    public ListPersons listPersonUseCase(PersonRepositoryGateway repositoryGateway) {
        return new ListPersons(repositoryGateway);
    }
    @Bean
    public FilterPersons filterPersonsUseCase(PersonRepositoryGateway repositoryGateway) {
        return new FilterPersons(repositoryGateway);
    }
    @Bean
    public GetPerson getPersonsUseCase(PersonRepositoryGateway repositoryGateway) {
        return new GetPerson(repositoryGateway);
    }
    @Bean
    public SaveAddress saveAddressUseCase(AddressRepositoryGateway repositoryGateway, GetPerson getPerson) {
        return new SaveAddress(repositoryGateway, getPerson);
    }
    @Bean
    public ListAddress listAddressUseCase(AddressRepositoryGateway repositoryGateway,
                                          GetPerson getPerson) {
        return new ListAddress(repositoryGateway, getPerson);
    }
}
