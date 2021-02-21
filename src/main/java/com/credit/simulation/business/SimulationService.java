package com.credit.simulation.business;

import com.credit.simulation.models.entity.SimuladorRq;
import com.credit.simulation.models.entity.SimuladorRs;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SimulationService {

    public SimuladorRs creditSimulation (SimuladorRq request) {
        SimuladorRs response = new SimuladorRs();
        String dni = request.getDni();
        String tarjeta = request.getTarjeta();
        String moneda = request.getMoneda();
        Double monto = request.getMonto();
        Double cuota = request.getCuota();
        Double tea =  getTea(request.getTea());
        String diaPago = request.getDiaPago();

        // Cuota = (Monto * (TEM x (1 + TEM) ^ n)) / ((1 + TEM) ^ n) - 1)
        Double montoCuota = monto * (Math.pow((1 + tea), cuota) / (Math.pow((1 + tea), cuota) - 1)) ;
        montoCuota = Math.round(montoCuota*100.0)/100.0;

        Calendar fechaPrimeraCuota = Calendar.getInstance();
        fechaPrimeraCuota.add(Calendar.MONTH, 1);

        response.setEstado("Exitoso");
        response.setMoneda(moneda);
        response.setCuota(montoCuota.toString());
        response.setPrimeraCuota(formatearCalendar(fechaPrimeraCuota));

        return response;
    }

    private Double getTea(String tea) {
        tea = tea.replace("%", "");
        Double teaD = Double.parseDouble(tea);
        teaD = teaD / 100;
        return teaD;
    }

    private String formatearCalendar(Calendar c) {
        Date fecha = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String fechaTexto = formatter.format(fecha);
        return fechaTexto;
    }

    public SimulationService() {
    }
}
