package com.uade.tpo.zapatillasPumba.entity;

public enum PedidoStatus {
    EN_PROCESO("En proceso"),
    ENVIADO("Enviado"),
    REALIZADO("Realizado");

    private final String displayName;

    PedidoStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}