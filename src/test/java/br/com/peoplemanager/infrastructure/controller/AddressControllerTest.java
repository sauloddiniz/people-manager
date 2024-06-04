package br.com.peoplemanager.infrastructure.controller;

import br.com.peoplemanager.domain.enums.StateEnum;
import br.com.peoplemanager.infrastructure.persistence.entity.AddressEntity;
import br.com.peoplemanager.infrastructure.persistence.entity.PersonEntity;
import br.com.peoplemanager.infrastructure.persistence.repository.AddressRepository;
import br.com.peoplemanager.infrastructure.persistence.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private AddressRepository addressRepository;

    @SpyBean
    private PersonRepository personRepository;

    @Test
    @DisplayName("Should success when save address")
    void shouldSuccessWhenSaveAddress() throws Exception {

        final String jsonAddressComplete = """
                {
                  "street": "Rua de algum lugar",
                  "city": "Cel Fabriciano",
                  "state": "MG",
                  "zipCode": "35170-009",
                  "principal": true,
                  "number": "987456"
                }
                """;

        doReturn(Optional.of(getPerson()))
                .when(personRepository).findById(anyLong());

        doReturn(AddressEntity.builder()
                        .principal(true)
                        .addressId(1L)
                        .city("Coronel Fabriciano")
                        .number("264")
                        .state(StateEnum.MG)
                        .zipCode("35170-009")
                        .street("Rua A")
                        .person(getPerson())
                        .build())
                .when(addressRepository).save(any());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/person/1/address")
                        .content(jsonAddressComplete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        header().exists("Location"))
                .andExpect(
                        status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("Should throw error when address incomplete")
    void shouldThrowErrorWhenAddressIncomplete() throws Exception {

        final String jsonAddressIncomplete = """
                {
                  "city": "Cel Fabriciano",
                  "state": "MG",
                  "zipCode": "35170-009",
                  "principal": true,
                  "number": "987456"
                }
                """;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/person/1/address")
                        .content(jsonAddressIncomplete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        jsonPath("$.message")
                                .value("street: must not be empty"))
                .andExpect(
                        status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("Should success when update address")
    void shouldSuccessWhenUpdateAddress() throws Exception {

        final String jsonAddressUpdate = """
                {
                  "street": "Rua de algum lugar",
                  "city": "Novo Nome",
                  "state": "MG",
                  "zipCode": "35170-009",
                  "principal": true,
                  "number": "987456"
                }
                """;

        doReturn(Optional.of(getAddress()))
                .when(addressRepository).findById(anyLong());
        doReturn(getAddressUpdated())
                .when(addressRepository).save(any());
        doReturn(Optional.of(getPerson()))
                .when(personRepository).findById(anyLong());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/person/1/address/1")
                        .content(jsonAddressUpdate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk())
                .andDo(print());
    }



    @Test
    @DisplayName("Should success when update principal address")
    void shouldSuccessWhenUpdatePrinciapAddress() throws Exception {

        final String jsonChangeMainAddress = """
                {
                  "principal": true
                }
                """;

        doReturn(Optional.of(getAddress()))
                .when(addressRepository).findById(anyLong());
        doReturn(getAddressUpdated())
                .when(addressRepository).save(any());
        doReturn(Optional.of(getPerson()))
                .when(personRepository).findById(anyLong());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .patch("/person/1/address/1")
                        .content(jsonChangeMainAddress)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Should then throw error when personId is different addressPersonId")
    void shouldThenThrowErrorWhenPersonIdIsDifferentAddressPersonId() throws Exception {

        final String jsonChangeMainAddress = """
                {
                  "principal": true
                }
                """;

        doReturn(Optional.of(getAddress()))
                .when(addressRepository).findById(anyLong());
        doReturn(Optional.of(getPerson()))
                .when(personRepository).findById(anyLong());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .patch("/person/2/address/1")
                        .content(jsonChangeMainAddress)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        jsonPath("$.message")
                                .value("Address not found: 1"))
                .andExpect(
                        status().is(404))
                .andDo(print());
    }


    private static PersonEntity getPerson() {
        return PersonEntity.builder()
                .personId(1L)
                .fullName("Jhon Dota")
                .birthDate(LocalDate.parse("2000-07-09"))
                .build();
    }

    private static AddressEntity getAddress() {
        return AddressEntity.builder()
                .principal(true)
                .addressId(1L)
                .city("Coronel Fabriciano")
                .number("264")
                .state(StateEnum.MG)
                .zipCode("35170-009")
                .street("Rua A")
                .person(getPerson())
                .build();
    }

    private static AddressEntity getAddressUpdated() {
        return AddressEntity.builder()
                .principal(true)
                .addressId(1L)
                .city("Novo Nome")
                .number("987456")
                .state(StateEnum.MG)
                .zipCode("35170-009")
                .street("Rua de algum lugar")
                .person(getPerson())
                .build();
    }
}