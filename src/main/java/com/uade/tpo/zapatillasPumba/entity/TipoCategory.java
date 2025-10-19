package com.uade.tpo.zapatillasPumba.entity;

public enum TipoCategory {
    OUTDOOR("Outdoor"),
    URBAN("Urban"),
    CASUAL("Casual"),
    DEPORTIVO("Deportivo");

    private final String displayName;

    TipoCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}