package com.credit.simulation.models.service;

import com.credit.simulation.models.dao.PersonaRepository;
import com.credit.simulation.models.entity.Persona;
import com.credit.simulation.models.util.SearchType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Resource
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> search(SearchType searchType, String searchCriteria) {
        List<Persona> personas;

        if (searchType == SearchType.BY_DNI) {
            personas = personaRepository.findByDNI(searchCriteria);
        } else {
            personas = personaRepository.findByAll();
        }

        return personas;
    }
}
