package com.uade.tpo.zapatillasPumba.entity;

public enum ColorEnum {
    NEGRO("Negro"),
    BLANCO("Blanco"),
    ROJO("Rojo"),
    VERDE("Verde"),
    AZUL("Azul"),
    GRIS("Gris"),
    MARRON("Marrón");

    private final String displayName;

    ColorEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}