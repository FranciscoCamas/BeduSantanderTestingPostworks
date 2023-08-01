package org.bedu.testing.services.imp;

import org.bedu.testing.controllers.mappers.DisciplineMapper;
import org.bedu.testing.models.DisciplineDTO;
import org.bedu.testing.persistence.IDisciplineRepository;
import org.bedu.testing.persistence.entities.DisciplineEntity;
import org.bedu.testing.persistence.entities.InterviewerEntity;
import org.bedu.testing.services.IDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */

@Service
public class DisciplineService implements IDisciplineService {

    private final IDisciplineRepository repository;
    private final DisciplineMapper      mapper;

    @Autowired
    public DisciplineService(IDisciplineRepository repository, DisciplineMapper mapper) {
        this.repository = repository;
        this.mapper     = mapper;
    }

    @Override
    public List<DisciplineDTO> findAllDisciplines() {
        return repository.findAll().stream().map(discipline -> mapper.disciplineEntityToDisciplineDTO(discipline)).toList();// .collect(Collectors.toList());
    }

    public List<DisciplineDTO> findByNameOrDescriptionOrSlug(String keyword) {
        return repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrSlugContainingIgnoreCase(keyword,keyword,keyword).stream().map(discipline -> mapper.disciplineEntityToDisciplineDTO(discipline)).toList();// .collect(Collectors.toList());
    //  return null;
    }
    @Override
    public Optional<DisciplineDTO> findDisciplineById(long idDiscipline) {

        Optional<DisciplineEntity> disciplineDB = repository.findById(idDiscipline);
        return disciplineDB.isPresent() ? Optional.of(mapper.disciplineEntityToDisciplineDTO(disciplineDB.get())) :Optional.empty();
    }
    @Override
    public DisciplineDTO saveDiscipline(  DisciplineDTO Discipline) {
        return mapper.disciplineEntityToDisciplineDTO (
                repository.save (mapper.disciplineDTOToDisciplineEntity  (Discipline))
        );
    }

    @Override
    public DisciplineDTO updateDiscipline(long id,  DisciplineDTO disciplineDTO ) {
        Optional<DisciplineEntity> disciplineDB = repository.findById(id);

        if (!disciplineDB.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The discipline that do you want to update does not exist.");

        disciplineDTO.setId(id);

        return mapper.disciplineEntityToDisciplineDTO(
                repository.save(mapper.disciplineDTOToDisciplineEntity (disciplineDTO))
        );

    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }
}