package com.credit.simulation.controllers;

import com.credit.simulation.business.SimulationService;
import com.credit.simulation.models.entity.Parametro;
import com.credit.simulation.models.entity.Persona;
import com.credit.simulation.models.entity.SimuladorRq;
import com.credit.simulation.models.entity.SimuladorRs;
import com.credit.simulation.models.service.IParanetroService;
import com.credit.simulation.models.service.IPersonaService;
import com.credit.simulation.models.util.Constants;
import com.credit.simulation.models.util.SearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SimulationController {
    @Autowired
    private IParanetroService paranetroService;

    @Autowired
    private IPersonaService personaService;


    @GetMapping(Constants.URI_RESOURCE_PARAMETER_LIST)
    public List<Parametro> parametroListar() {
        return  paranetroService.search(SearchType.BY_ALL, "");
    }

    @GetMapping(Constants.URI_RESOURCE_PARAMETER_CARDS)
    public List<Parametro> parametroTarjetas() {
        return  paranetroService.search(SearchType.BY_FAMILY_NAME, "Tarjetas");
    }

    @GetMapping(Constants.URI_RESOURCE_PARAMETER_FEE)
    public List<Parametro> parametroCuotas() {
        return  paranetroService.search(SearchType.BY_FAMILY_NAME, "Cuotas");
    }

    @GetMapping(Constants.URI_RESOURCE_PARAMETER_PAYDAY)
    public List<Parametro> parametroDiasPago() {
        return  paranetroService.search(SearchType.BY_FAMILY_NAME, "DiasPago");
    }

    @GetMapping(Constants.URI_RESOURCE_PARAMETER_RATE)
    public List<Parametro> parametroTEA() {
        return  paranetroService.search(SearchType.BY_FAMILY_NAME, "TEA");
    }


    @GetMapping(Constants.URI_RESOURCE_PARAMETER_ID)
    public List<Parametro> parametroDetalle(@PathVariable Long id) {
        return paranetroService.search(SearchType.BY_ID, id.toString());
    }

    @GetMapping(Constants.URI_RESOURCE_PARAMETER_PRODUCT)
    public List<Parametro> parametroProductoDetalle(@PathVariable String prod) {
        return paranetroService.search(SearchType.BY_PRODUCT_NAME, prod);
    }

    @GetMapping(Constants.URI_RESOURCE_PERSON_LIST)
    public List<Persona> personaListar() {
        return personaService.search(SearchType.BY_ALL, "");
    }

    @GetMapping(Constants.URI_RESOURCE_PERSON_DNI)
    public List<Persona> personaDetalle(@PathVariable String dni) {
        return personaService.search(SearchType.BY_DNI, dni);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @PostMapping(value = Constants.URI_RESOURCE_CREDIT_SIMULATION,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SimuladorRs> simulaCredito(@RequestBody SimuladorRq simuladorRq) {
        SimuladorRs simuladorRs = new SimulationService().creditSimulation(simuladorRq);
        if (simuladorRs.getEstado().equals(Constants.RESULT_OK) ) {
            return ResponseEntity.ok(simuladorRs);
        }  else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(simuladorRs);
        }
    }



}
