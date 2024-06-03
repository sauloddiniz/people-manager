//package br.com.peoplemanager.service;
//
//import br.com.peoplemanager.infrastructure.persistence.entity.PersonEntity;
//import br.com.peoplemanager.infrastructure.persistence.repository.PersonRepository;
//import br.com.peoplemanager.naousar.service.PersonService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.lenient;
//
//@ExtendWith(MockitoExtension.class)
//class PersonServiceTest {
//    @InjectMocks
//    private PersonService personService;
//    @Mock
//    private PersonRepository personRepository;
//
//
//    @BeforeEach
//    void setUp() {
//        lenient().when(personRepository.findAll(Specification.where(any()))).thenReturn(createList());
//        lenient().when(personRepository.findAll()).thenReturn(createList());
//    }
//
//    @DisplayName("Must cover conditions")
//    @ParameterizedTest
//    @MethodSource("valuesProvider")
//    void mustCoverConditions(String name) {
//
//        final int expectedListSize = 2;
//
//        List<PersonDto> list = personService.listPerson(name);
//
//        assertEquals(expectedListSize, list.size());
//    }
//
//    static Stream<String> valuesProvider() {
//        return Stream.of(null, "", "Saulo, Dias", "Saulo", "Dias,", ",Diniz");
//    }
//
//    private List<PersonEntity> createList() {
//        return List.of(
//                PersonEntity.builder().fullName("Jose das Couves").birthDate(LocalDate.now()).build(),
//                PersonEntity.builder().fullName("Maria dos Tomates").birthDate(LocalDate.now()).build()
//        );
//    }
//
//}