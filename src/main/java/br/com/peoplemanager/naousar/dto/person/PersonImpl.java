package br.com.peoplemanager.naousar.dto.person;

public interface PersonImpl {
    default void imprimirDefault() {
        System.out.println("ola sou metodo default");
    }

    void imprimirAbs();

    static void imprimiStatic() {
        System.out.println("sou metodo static");
    }
}
