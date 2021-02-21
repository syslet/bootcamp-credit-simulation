package com.credit.simulation.models.service;

import com.credit.simulation.models.entity.Persona;
import com.credit.simulation.models.util.SearchType;

import java.util.List;

public interface IPersonaService {
    public List<Persona> search(SearchType searchType, String searchCriteria);
}
