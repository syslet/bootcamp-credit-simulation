package com.credit.simulation.models.service;

import com.credit.simulation.models.dao.ParametroRepository;
import com.credit.simulation.models.entity.Parametro;
import com.credit.simulation.models.util.SearchType;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ParametroServiceImpl implements IParanetroService {

    @Resource
    private ParametroRepository parametroRepository;

    @Override
    public List<Parametro> search(SearchType searchType, String searchCriteria) {
        List<Parametro> parametros;
        if (searchType == searchType.BY_ID) {
            parametros = parametroRepository.findById(searchCriteria);
        } else if (searchType == searchType.BY_FAMILY_NAME) {
            parametros = parametroRepository.findByFamilyName(searchCriteria);
        } else if (searchType == searchType.BY_PRODUCT_NAME) {
            parametros = parametroRepository.findByProductName(searchCriteria);
        } else {
            parametros = parametroRepository.findByAll();
        }
        return parametros;
    }


}
