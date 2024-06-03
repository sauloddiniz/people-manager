//package br.com.peoplemanager.service;
//
//import br.com.peoplemanager.dto.AddressDto;
//import br.com.peoplemanager.infrastructure.persistence.entity.AddressEntity;
//import br.com.peoplemanager.infrastructure.persistence.entity.PersonEntity;
//import br.com.peoplemanager.infrastructure.persistence.repository.AddressRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyCollection;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.lenient;
//
//
//@ExtendWith(MockitoExtension.class)
//class AddressServiceTest {
//
//    @InjectMocks
//    private AddressService addressService;
//
//    @Mock
//    private AddressRepository addressRepository;
//
//    @Mock
//    private PersonService personService;
//
//    @BeforeEach
//    void setUp() {
//        lenient().when(personService.validPerson(anyLong()))
//                .thenReturn(PersonEntity.builder().build());
//        lenient().when(addressRepository.findAllByPersonPersonId(anyLong()))
//                .thenReturn(createListAddresses());
//        lenient().when(addressRepository.findAllByPersonPersonIdAndAddressIdIn(anyLong(),anyCollection())).
//                thenReturn(createListAddresses());
//    }
//
//    private List<AddressEntity> createListAddresses() {
//        return List.of(
//                AddressEntity.builder().principal(false).number("123").zipCode("35170-087").city("Fabri City").state(StateEnum.MG).street("rua").build(),
//                AddressEntity.builder().principal(true).number("148").zipCode("35170-089").city("Gothan City").state(StateEnum.AL).street("rua").build()
//        );
//    }
//
//    @DisplayName("Must cover conditions addresses")
//    @ParameterizedTest
//    @MethodSource("valuesProvider")
//    void mustCoverConditionsAddresses(String id) {
//        final int expectListSize = 2;
//
//        List<AddressDto> list = addressService.getAddresses(1L, id);
//
//        assertEquals(expectListSize, list.size());
//    }
//
//    static Stream<String> valuesProvider() {
//        return Stream.of(null, "1", "1, 2", "1", "1,");
//    }
//}