package br.com.peoplemanager.infrastructure.controller;

import br.com.peoplemanager.infrastructure.persistence.entity.PersonEntity;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private PersonRepository personRepository;

    @Test
    @DisplayName("Should success when save person")
    void shouldSuccessWhenSavePerson() throws Exception {

        final String jsonPersonComplete = """
                {
                  "fullName": "Saulo Dias",
                  "birthDate": "2000-05-08"
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
                  "birthDate": "2000-05-08"
                }
                """;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/person")
                        .content(jsonPersonIncomplete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        jsonPath("$.message")
                                .value("Name cannot be empty and must contain at least two words."))
                .andExpect(
                        status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("Should success when update person")
    void shouldThrowExceptionPersonNotFound() throws Exception {

        final String jsonPersonIncomplete = """
                {
                  "fullName": "Jose de Assis",
                  "birthDate": "2000-05-08"
                }
                """;

        doReturn(Optional.empty()).when(personRepository)
                .findById(anyLong());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/person/1")
                        .content(jsonPersonIncomplete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        jsonPath("$.message")
                                .value("Person not found: 1"))
                .andExpect(
                        status().is(404))
                .andDo(print());
    }

    @Test
    @DisplayName("Should success when update person")
    void shouldSuccessWhenUpdatePerson() throws Exception {

        PersonEntity personEntity = PersonEntity.builder()
                .personId(1L)
                .fullName("Jose Assis")
                .birthDate(LocalDate.parse("2000-05-08"))
                .build();

        doReturn(Optional.of(personEntity)).when(personRepository)
                .findById(anyLong());

        final String jsonPersonIncomplete = """
                {
                  "fullName": "Jose de Assis",
                  "birthDate": "2000-05-08"
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
    @DisplayName("Should throw error when try save person only first name")
    void shouldThrowErrorWhenTrySavePersonOnlyFirstName() throws Exception {

        final String jsonPersonIncomplete = """
                {
                  "fullName": "Jose",
                  "birthDate": "2024-05-08"
                }
                """;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/person")
                        .content(jsonPersonIncomplete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        jsonPath("$.message")
                                .value("Name cannot be empty and must contain at least two words."))
                .andExpect(
                        status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("Should success when get persons")
    void shouldSuccessWhenGetPersons() throws Exception {

        executeScriptDataBases();

        String expectedResponse = """
                   [{"personId":1,"fullName":"Joe Man","birthDate":"1997-05-08","principalAddress":null},
                   {"personId":2,"fullName":"Jhow Dute","birthDate":"1998-05-08","principalAddress":null},
                   {"personId":3,"fullName":"Jose Assis","birthDate":"1999-05-08","principalAddress":null}]
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

        executeScriptDataBases();

        String expectedResponse = """
                   [{"personId":2,"fullName":"Jhow Dute","birthDate":"1998-05-08","principalAddress":null}]
                """;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/person")
                        .param("names", "Jhow")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk())
                .andExpect(
                        content().json(expectedResponse))
                .andDo(print());
    }

    @Test
    @DisplayName("Should success when filter person using name params")
    void shouldSuccessWhenGetPersonUrl() throws Exception {

        executeScriptDataBases();

        String expectedResponse = """
                   {"personId":3,"fullName":"Jose Assis","birthDate":"1999-05-08","principalAddress":null}
                """;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/person/3")
                        .param("names", "Jhow")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk())
                .andExpect(
                        content().json(expectedResponse))
                .andDo(print());
    }

    private static void executeScriptDataBases() throws SQLException {

        Connection connection = createConnection();

        final String dropPersonDatableIfExist = """
                DROP TABLE IF EXISTS PERSON CASCADE;
                """;
        final String createTableIfNotExists = """
                CREATE TABLE IF NOT EXISTS PERSON
                    (PERSON_ID NUMBER GENERATED BY DEFAULT AS IDENTITY,
                     FULL_NAME VARCHAR(255),
                     BIRTH_DATE DATE)
                """;
        final String insertPersonSql = """
                INSERT INTO PERSON(FULL_NAME, BIRTH_DATE)
                VALUES ('Joe Man', '1997-05-08');
                                    
                INSERT INTO PERSON(FULL_NAME, BIRTH_DATE)
                VALUES ('Jhow Dute', '1998-05-08');
                                    
                INSERT INTO PERSON(FULL_NAME, BIRTH_DATE)
                VALUES ('Jose Assis', '1999-05-08');
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(dropPersonDatableIfExist);
            statement.execute(createTableIfNotExists);
            statement.execute(insertPersonSql);
        }
    }

    private static Connection createConnection() throws SQLException {
        final String DB_TEST_URL = "jdbc:h2:mem:peopledb";
        return DriverManager.getConnection(DB_TEST_URL, "sa", "password");
    }
}