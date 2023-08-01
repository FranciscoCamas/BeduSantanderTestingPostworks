package org.bedu.testing.services;

import org.bedu.testing.models.TechnologyDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */

public interface ITechnologyService {

    List<TechnologyDTO> findAllTechnologies();

    Optional< TechnologyDTO> findTechnologyById(long id);

    TechnologyDTO saveTechnology( TechnologyDTO data);

    TechnologyDTO updateTechnology(long id,  TechnologyDTO data);

    public void delete(long id);

    public long count();
}