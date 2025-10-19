package com.uade.tpo.zapatillasPumba.entity;

public enum SexoCategory {
    HOMBRE("Hombre"),
    MUJER("Mujer"),
    NINIOS("Niños");

    private final String displayName;

    SexoCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}