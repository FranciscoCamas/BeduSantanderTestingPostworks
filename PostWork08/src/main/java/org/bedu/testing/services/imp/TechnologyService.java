package org.bedu.testing.services.imp;

import org.bedu.testing.controllers.mappers.TechnologyMapper;
import org.bedu.testing.models.TechnologyDTO;
import org.bedu.testing.persistence.ITechnologyRepository;
import org.bedu.testing.persistence.entities.TechnologyEntity;
import org.bedu.testing.services.ITechnologyService;
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
public class TechnologyService implements ITechnologyService {

    private final ITechnologyRepository repository;
    private final TechnologyMapper mapper;

    @Autowired
    public TechnologyService(ITechnologyRepository repository, TechnologyMapper mapper) {
        this.repository = repository;
        this.mapper     = mapper;
    }

    @Override
    public List<TechnologyDTO> findAllTechnologies() {
        return repository.findAll().stream().map(technology -> mapper.technologyEntityToTechnologyDTO(technology)).toList();// .collect(Collectors.toList());
    }

    public List<TechnologyDTO> findByNamerDescriptionSlug(String keyword) {
       return repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrSlugContainingIgnoreCase(keyword,keyword,keyword).stream().map(interviewer -> mapper.technologyEntityToTechnologyDTO(interviewer)).toList();// .collect(Collectors.toList());

    }
    @Override
    public Optional<TechnologyDTO> findTechnologyById(long idTechnology) {
        Optional<TechnologyEntity> technologyDB = repository.findById(idTechnology);
        return technologyDB.isPresent() ? Optional.of(mapper.technologyEntityToTechnologyDTO(technologyDB.get())) :Optional.empty();
    }
    @Override
    public TechnologyDTO saveTechnology(  TechnologyDTO interviewer) {
        return mapper.technologyEntityToTechnologyDTO (
                repository.save (mapper.technologyDTOToTechnologyEntity  (interviewer))
        );
    }

    @Override
    public TechnologyDTO updateTechnology(long id,  TechnologyDTO interviewerDTO) {
        Optional<TechnologyEntity> technologyDB = repository.findById(id);

        if (!technologyDB.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The technology that do you want to update does not exist.");

        interviewerDTO.setId(id);
        return mapper.technologyEntityToTechnologyDTO(
                repository.save(mapper.technologyDTOToTechnologyEntity (interviewerDTO))
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