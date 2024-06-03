package br.com.peoplemanager.domain.enums;

import br.com.peoplemanager.domain.exception.StateEnumConverterException;
import java.text.Normalizer;

public enum StateEnum {
    AC("AC", "Acre"),
    AL("AL", "Alagoas"),
    AP("AP", "Amapá"),
    AM("AM", "Amazonas"),
    BA("BA", "Bahia"),
    CE("CE", "Ceará"),
    DF("DF", "Distrito Federal"),
    ES("ES", "Espírito Santo"),
    GO("GO", "Goiás"),
    MA("MA", "Maranhão"),
    MT("MT", "Mato Grosso"),
    MS("MS", "Mato Grosso do Sul"),
    MG("MG", "Minas Gerais"),
    PA("PA", "Pará"),
    PB("PB", "Paraíba"),
    PR("PR", "Paraná"),
    PE("PE", "Pernambuco"),
    PI("PI", "Piauí"),
    RJ("RJ", "Rio de Janeiro"),
    RN("RN", "Rio Grande do Norte"),
    RS("RS", "Rio Grande do Sul"),
    RO("RO", "Rondônia"),
    RR("RR", "Roraima"),
    SC("SC", "Santa Catarina"),
    SP("SP", "São Paulo"),
    SE("SE", "Sergipe"),
    TO("TO", "Tocantins");
    public static final String NON_ASCII_REGEX = "[^\\p{ASCII}]";
    public static final String REPLACEMENT = "";
    private final String code;
    private final String name;

    StateEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }

    public static StateEnum fromString(String input) {
        String normalizedInput = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll(NON_ASCII_REGEX, REPLACEMENT)
                .toLowerCase();
        for (StateEnum state : StateEnum.values()) {
            String normalizedCode = Normalizer.normalize(state.code, Normalizer.Form.NFD)
                    .replaceAll(NON_ASCII_REGEX, REPLACEMENT)
                    .toLowerCase();
            String normalizedName = Normalizer.normalize(state.name, Normalizer.Form.NFD)
                    .replaceAll(NON_ASCII_REGEX, REPLACEMENT)
                    .toLowerCase();
            if (normalizedCode.equals(normalizedInput) || normalizedName.equals(normalizedInput)) {
                return state;
            }
        }
        throw new StateEnumConverterException("No constant with state " + input + " found");
    }
}
