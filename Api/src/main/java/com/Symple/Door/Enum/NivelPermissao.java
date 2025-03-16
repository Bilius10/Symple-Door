package com.Symple.Door.Enum;

public enum NivelPermissao {

    ADMIN("admin"),
    PORTEIRO("porteiro");

    private String nivel;

    NivelPermissao(String nivel) {
        this.nivel = nivel;
    }

    public String getNivel() {
        return nivel;
    }
}
