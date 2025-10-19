package com.uade.tpo.zapatillasPumba.entity;

public enum TalleEnum {
    TALLE_38(38),
    TALLE_39(39),
    TALLE_40(40),
    TALLE_41(41),
    TALLE_42(42),
    TALLE_43(43),
    TALLE_44(44),
    TALLE_45(45),
    TALLE_46(46),
    TALLE_47(47);

    private final int numero;

    TalleEnum(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
}