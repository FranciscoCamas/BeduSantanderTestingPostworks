package org.bedu.testing.controllers.mappers;

import org.bedu.testing.models.TechnologyDTO;
import org.bedu.testing.persistence.entities.TechnologyEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TechnologyMapper {
    TechnologyEntity technologyDTOToTechnologyEntity(TechnologyDTO technologyDTO);
    TechnologyDTO technologyEntityToTechnologyDTO(TechnologyEntity technologyEntity);
}