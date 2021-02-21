package com.credit.simulation.models.util;

public class Constants {
    public static final String RESULT_OK = "EXITOSO";
    public static final String ERROR_DNI_NOT_FOUND = "ERROR - DNI DE CLIENTE NO EXISTE";
    public static final String ERROR_CARD_NOT_FOUND = "ERROR - TIPO DE TARJETA NO EXISTE [Clasica, Oro, Black]";
    public static final String ERROR_MONEY_NOT_FOUND = "ERROR - TIPO DE MONEDA NO EXISTE [S/]";
    public static final String ERROR_FEE_NOT_FOUND = "ERROR - TIPO DE CUOTA NO PARAMETRIZADA [1 a 36]";
    public static final String ERROR_RATE_NOT_FOUND = "ERROR - TIPO DE TASA NO PARAMETRIZADA [90.90%, 95.90%, 99.90%]";
    public static final String ERROR_PAYDAY_NOT_FOUND = "ERROR - DIA DE PAGO NO PARAMETRIZADA [5 y 20]";

    public static final String MONEY_NSOL = "S/";
    public static final String URI_PERSON_BY_DNI = "http://localhost:8080/persona/dni/";
    public static final String URI_PARAMETER_BY_PRODUCT = "http://localhost:8080/parametro/producto/";

    public static final String FAMILY_CARDS = "Tarjetas";
    public static final String FAMILY_FEE = "Cuotas";
    public static final String FAMILY_PAYDAY = "DiasPago";
    public static final String FAMILY_RATE = "TEA";





}
