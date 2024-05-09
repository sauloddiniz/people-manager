package br.com.peoplemanager.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql(scripts = {"/schema.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    @DisplayName("Should success when save person")
    void shouldSuccessWhenSavePerson() throws Exception {

        final String jsonPersonComplete = """
                {
                  "fullName": "Saulo Dias",
                  "birthDate": "2024-05-08"
                }
                """;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/person")
                        .content(jsonPersonComplete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        header().exists("Location"))
                .andExpect(
                        status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("Should throw error when person save incomplete")
    void shouldThrowErrorWhenPersonSaveIncomplete() throws Exception {

        final String jsonPersonIncomplete = """
                {
                  "birthDate": "2024-05-08"
                }
                """;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/person")
                        .content(jsonPersonIncomplete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        jsonPath("$.message")
                        .value("fullName: must not be empty"))
                .andExpect(
                        status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("Should success when update person")
    void shouldSuccessWhenUpdatePerson() throws Exception {

        final String jsonPersonIncomplete = """
                {
                  "fullName": "Saulo",
                  "birthDate": "2024-05-08"
                }
                """;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/person/1")
                        .content(jsonPersonIncomplete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Should success when get persons")
    void shouldSuccessWhenGetPersons() throws Exception {

        String expectedResponse = """
            [{"fullName":"Saulo Dias","birthDate":"2024-01-01"},
             {"fullName":"Jose Pereira","birthDate":"2023-01-01"}]
         """;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/person")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk())
                .andExpect(
                        content().json(expectedResponse))
                .andDo(print());
    }

    @Test
    @DisplayName("Should success when filter person using name params")
    void shouldSuccessWhenFilterPersonUsingNameParams() throws Exception {

        String expectedResponse = """
            [{"fullName":"Saulo Dias","birthDate":"2024-01-01"}]
         """;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/person")
                        .param("names", "Dias")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk())
                .andExpect(
                        content().json(expectedResponse))
                .andDo(print());
    }
}