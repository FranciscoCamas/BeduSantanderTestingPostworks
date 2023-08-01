package org.bedu.testing.services;

import org.bedu.testing.models.DisciplineDTO;

import java.util.List;
import java.util.Optional;
/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */
public interface IDisciplineService {
    List<DisciplineDTO> findAllDisciplines();

    Optional< DisciplineDTO> findDisciplineById(long id);

    DisciplineDTO saveDiscipline( DisciplineDTO data);

    DisciplineDTO updateDiscipline(long id,  DisciplineDTO data);

    public void delete(long id);

    public long count();

}