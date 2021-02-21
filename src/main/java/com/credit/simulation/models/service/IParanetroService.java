package com.credit.simulation.models.service;

import com.credit.simulation.models.entity.Parametro;
import com.credit.simulation.models.util.SearchType;

import java.util.List;

public interface IParanetroService {
    public List<Parametro> search(SearchType searchType, String searchCriteria);

}
