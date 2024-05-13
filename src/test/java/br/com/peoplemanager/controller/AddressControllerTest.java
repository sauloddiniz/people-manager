package br.com.peoplemanager.controller;

import br.com.peoplemanager.entity.Address;
import br.com.peoplemanager.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql(scripts = {"/insert_tables.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    AddressRepository addressRepository;
    @BeforeEach
    void setUp() {
        doReturn(Address.builder().addressId(1L)
                .city("city").street("street").build())
                .when(addressRepository).save(any(Address.class));
    }

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

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/person/1/addresses")
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
                        .post("/person/1/addresses")
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

        final String jsonAddressComplete = """
                {
                  "street": "Rua de algum lugar",
                  "city": "Novo Nome",
                  "state": "MG",
                  "zipCode": "35170-009",
                  "principal": true,
                  "number": "987456"
                }
                """;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/person/1/addresses/1")
                        .content(jsonAddressComplete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Should success when update principal address")
    void shouldSuccessWhenUpdatePrinciapAddress() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                        .patch("/person/1/addresses/1/principal")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("should success when get addresses")
    void shouldSuccessWhenGetAddresses() throws Exception {

        String expectedResponse = """
            [
                {"id":"1","street":"Rua de algum lugar","zipCode":"35170-009","number":"987456","city":"Cel Fabriciano","state":"Minas Gerais","principal":false},
                {"id":"2","street":"Rua de algum lugar dois","zipCode":"35170-008","number":"987456","city":"Cel Fabriciano","state":"Minas Gerais","principal":true}
            ]
        """;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/person/1/addresses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk())
                .andExpect(
                        content().json(expectedResponse))
                .andDo(print());
    }

    @Test
    @DisplayName("Should success when filter address using id params")
    void shouldSuccessWhenFilterAddressUsingIdParams() throws Exception {

        String expectedResponse = """
            [{"id":"1","street":"Rua de algum lugar","zipCode":"35170-009","number":"987456","city":"Cel Fabriciano","state":"Minas Gerais","principal":false}]
        """;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/person/1/addresses")
                        .param("ids", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk())
                .andExpect(
                        content().json(expectedResponse))
                .andDo(print());
    }
}