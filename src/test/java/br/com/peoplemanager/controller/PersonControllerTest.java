package br.com.peoplemanager.controller;

import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql(scripts = {"/schema.sql"})
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    @DisplayName("Should success save person")
    void shouldSuccessSavePerson() throws Exception {

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
    @DisplayName("Should throw error person save")
    void shouldThrowExceptionPersonError() throws Exception {

        final String jsonPersonIncomplete = """
                {
                  "fullName": "",
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
}