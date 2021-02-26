package com.credit.simulation.business;

import com.credit.simulation.models.entity.Parametro;
import com.credit.simulation.models.entity.Persona;
import com.credit.simulation.models.entity.SimuladorRq;
import com.credit.simulation.models.entity.SimuladorRs;
import com.credit.simulation.models.util.Constants;
import com.credit.simulation.models.util.ResultPersonType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class SimulationService {
    public SimuladorRs creditSimulation (SimuladorRq request) {
        SimuladorRs response = new SimuladorRs();
        String moneda = request.getMoneda();

        if (validRequest(request, response)){
            Double pagoMensual = getPagoMensual(request.getMonto(), request.getCuota(), request.getTea());
            pagoMensual = Math.round(pagoMensual*100.00)/100.00;

            Date fechaCompra = null;
            try {
                fechaCompra = new SimpleDateFormat(Constants.FORMAT_DATE).parse(request.getFechaComrpa());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar fechaPrimeraCuota = Calendar.getInstance();
            fechaPrimeraCuota.setTime(fechaCompra);
            fechaPrimeraCuota.add(Calendar.MONTH, 1);
            fechaPrimeraCuota.set( Calendar.DAY_OF_MONTH, Integer.parseInt(request.getDiaPago()));

            response.setEstado(Constants.RESULT_OK);
            response.setMoneda(moneda);
            response.setCuota(String.valueOf(pagoMensual) );
            response.setPrimeraCuota(formatearCalendar(fechaPrimeraCuota));
        }
        return response;
    }

    private String formatearCalendar(Calendar c) {
        Date fecha = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.FORMAT_DATE);
        String fechaTexto = formatter.format(fecha);
        return fechaTexto;
    }

    private Double getPagoMensual(Double montoInicial, int plazoMeses, String tasaAnualS) {
        Double tasaTEA = Double.parseDouble(tasaAnualS.replace("%", ""));
        tasaTEA = tasaTEA / 100.00;
        Double tasaTEM =  Math.pow(1 + tasaTEA, 30.0/360.0) - 1;
        Double factorFRC = (tasaTEM * Math.pow(1 + tasaTEM, plazoMeses)) / (Math.pow(1 + tasaTEM, plazoMeses) - 1);
        Double pagoMensual = montoInicial * factorFRC;
        return pagoMensual;
    }

    private boolean validRequest(SimuladorRq request, SimuladorRs response) {

        //1. Validamos el Tipo de Tarjeta
        if (!validParametro(request.getTarjeta(), Constants.FAMILY_CARDS)) {
            response.setEstado(Constants.ERROR_CARD_NOT_FOUND);
            return false;
        }

        //2. Validar que el Cliente Exista en la BD
        ResultPersonType resultPersonType = validPersona(request.getDni(), request.getTarjeta());
        if (resultPersonType == ResultPersonType.ERROR_CARD) {
            response.setEstado(Constants.ERROR_DNI_CARD_NOT_VALID);
            return false;
        } else if (resultPersonType == ResultPersonType.ERROR_DNI) {
            response.setEstado(Constants.ERROR_DNI_NOT_FOUND);
            return false;
        }

        //3. Validamos Moneda
        if (!(request.getMoneda().equals(Constants.MONEY_NSOL))) {
            response.setEstado(Constants.ERROR_MONEY_NOT_FOUND);
            return false;
        }

        //4. Validamos Cuotas
        if (!validParametro(String.valueOf(request.getCuota()), Constants.FAMILY_FEE)) {
            response.setEstado(Constants.ERROR_FEE_NOT_FOUND);
            return false;
        }

        //5. Validamos TEA
        if (!validParametro(String.valueOf(request.getTea()), Constants.FAMILY_RATE)) {
            response.setEstado(Constants.ERROR_RATE_NOT_FOUND);
            return false;
        }

        //6. Validamos Dia de Pago
        if (!validParametro(String.valueOf(request.getDiaPago()), Constants.FAMILY_PAYDAY)) {
            response.setEstado(Constants.ERROR_PAYDAY_NOT_FOUND);
            return false;
        }

        return true;
    }

    private ResultPersonType validPersona(String dni, String tarjeta) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Constants.URI_PERSON_BY_DNI + dni;
        ResponseEntity<Persona[]> personas = restTemplate.getForEntity(url, Persona[].class);
        if (personas.getBody().length == 0) {
            return ResultPersonType.ERROR_DNI;
        }
        else {
            Persona persona = personas.getBody()[0];
            if (!persona.getTarjeta().toLowerCase().equals(tarjeta.toLowerCase())) {
                return ResultPersonType.ERROR_CARD;
            }
        }
        return ResultPersonType.OK;
    }

    private Boolean validParametro(String param, String family) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Constants.URI_PARAMETER_BY_PRODUCT + param;
        ResponseEntity<Parametro[]> parametro = restTemplate.getForEntity(url, Parametro[].class);
        if (parametro.getBody().length == 0) {
            return false;
        } else {
            for (Parametro p: parametro.getBody()) {
                param = param.toLowerCase();
                family = family.toLowerCase();
                if (param.equals(p.getProducto().toLowerCase()) && family.equals(p.getFamilia().toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    public SimulationService() {
    }
}
