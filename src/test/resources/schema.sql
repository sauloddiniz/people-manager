INSERT INTO PERSON(FULL_NAME, BIRTH_DATE)
VALUES ('Saulo Dias', '2024-01-01');

INSERT INTO PERSON(FULL_NAME, BIRTH_DATE)
VALUES ('Jose Pereira', '2023-01-01');

INSERT INTO ADDRESS (PERSON_ID, STREET, ZIP_CODE, NUMBER, CITY, STATE, PRINCIPAL)
VALUES (1, 'Rua de algum lugar', '35170-009', '987456', 'Cel Fabriciano', 'MG', FALSE);
INSERT INTO ADDRESS (PERSON_ID, STREET, ZIP_CODE, NUMBER, CITY, STATE, PRINCIPAL)
VALUES (1, 'Rua de algum lugar dois', '35170-008', '987456', 'Cel Fabriciano', 'MG', TRUE);