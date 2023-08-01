package org.bedu.testing.controllers.mappers;

import org.bedu.testing.models.DisciplineDTO;
import org.bedu.testing.persistence.entities.DisciplineEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Francisco Javier Camas Tec francisco_camas@chotmail.com
 */

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DisciplineMapper {
    DisciplineEntity disciplineDTOToDisciplineEntity(DisciplineDTO disciplineDTO);

    DisciplineDTO disciplineEntityToDisciplineDTO(DisciplineEntity disciplineEntity);

}