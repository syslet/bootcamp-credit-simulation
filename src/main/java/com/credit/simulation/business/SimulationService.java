package com.credit.simulation.business;

import com.credit.simulation.models.entity.Parametro;
import com.credit.simulation.models.entity.Persona;
import com.credit.simulation.models.entity.SimuladorRq;
import com.credit.simulation.models.entity.SimuladorRs;
import com.credit.simulation.models.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.*;

public class SimulationService {
    public SimuladorRs creditSimulation (SimuladorRq request) {
        SimuladorRs response = new SimuladorRs();
        String dni = request.getDni();
        String tarjeta = request.getTarjeta();
        String moneda = request.getMoneda();

        if (validRequest(request, response)){
            double pagoMensual = getPagoMensual(request.getMonto(), request.getCuota(), request.getTea());

            Calendar fechaPrimeraCuota = Calendar.getInstance();
            fechaPrimeraCuota.add(Calendar.MONTH, 1);

            pagoMensual = Math.round(pagoMensual*100)/100;

            response.setEstado(Constants.RESULT_OK);
            response.setMoneda(moneda);
            response.setCuota( String.valueOf(pagoMensual) );
            response.setPrimeraCuota(formatearCalendar(fechaPrimeraCuota));
        }
        return response;
    }

    private String formatearCalendar(Calendar c) {
        Date fecha = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String fechaTexto = formatter.format(fecha);
        return fechaTexto;
    }

    private double getPagoMensual(double montoInicial, int plazoMeses, String tasaAnualS) {
        double tasaAnual = Double.parseDouble(tasaAnualS.replace("%", ""));
        double tasaMensual = tasaAnual / (12*100);
        double pagoMensual = montoInicial * (tasaMensual / (1 - Math.pow((1 + tasaMensual), (plazoMeses * -1))));
        return pagoMensual;
    }

    private boolean validRequest(SimuladorRq request, SimuladorRs response) {
        //1. Validar que el Cliente Exista en la BD
        if (!validPersona(request.getDni())) {
            response.setEstado(Constants.ERROR_DNI_NOT_FOUND);
            return false;
        }

        //2. Validamos el Tipo de Tarjeta
        if (!validParametro(request.getTarjeta(), Constants.FAMILY_CARDS)) {
            response.setEstado(Constants.ERROR_CARD_NOT_FOUND);
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

    private Boolean validPersona(String dni) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Constants.URI_PERSON_BY_DNI + dni;
        ResponseEntity<Persona[]> personas = restTemplate.getForEntity(url, Persona[].class);
        if (personas.getBody().length == 0) {
            return false;
        }
        return true;
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
