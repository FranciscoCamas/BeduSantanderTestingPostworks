package org.bedu.testing.services.imp;

import org.bedu.testing.controllers.mappers.InterviewTypeMapper;
import org.bedu.testing.models.InterviewTypeDTO;
import org.bedu.testing.persistence.IInterviewTypeRepository;
import org.bedu.testing.persistence.entities.InterviewTypeEntity;
import org.bedu.testing.services.IInterviewTypeService;
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
public class InterviewTypeService implements IInterviewTypeService {

    private final IInterviewTypeRepository repository;
    private final InterviewTypeMapper mapper;

    @Autowired
    public InterviewTypeService(IInterviewTypeRepository repository, InterviewTypeMapper mapper) {
        this.repository = repository;
        this.mapper     = mapper;
    }

    @Override
    public List<InterviewTypeDTO> findAllInterviewTypes() {
        return repository.findAll().stream().map(interviewerType -> mapper.interviewTypeEntityToInterviewTypeDTO(interviewerType)).toList();// .collect(Collectors.toList());
    }

    public List<InterviewTypeDTO> findByNameOrDescriptionOrSlug(String keyword) {
      return repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrSlugContainingIgnoreCase(keyword,keyword,keyword).stream().map(interviewerType -> mapper.interviewTypeEntityToInterviewTypeDTO(interviewerType)).toList();// .collect(Collectors.toList());
    }
    @Override
    public Optional<InterviewTypeDTO> findInterviewTypeById(long idinterviewerType) {
        Optional<InterviewTypeEntity> interviewerDB = repository.findById(idinterviewerType);
        return interviewerDB.isPresent() ? Optional.of(mapper.interviewTypeEntityToInterviewTypeDTO(interviewerDB.get())) :Optional.empty();
    }
    @Override
    public InterviewTypeDTO saveInterviewType(  InterviewTypeDTO interviewerTypeDTO) {
        return mapper.interviewTypeEntityToInterviewTypeDTO (
                repository.save (mapper.interviewTypeDTOToInterviewTypeEntity  (interviewerTypeDTO))
        );
    }

    @Override
    public InterviewTypeDTO updateInterviewType(long id,  InterviewTypeDTO interviewerTypeDTO) {
        Optional<InterviewTypeEntity> interviewerTypeDB = repository.findById(id);

        if (!interviewerTypeDB.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Interviewer Type that do you want to update does not exist.");

        interviewerTypeDTO.setId(id);
        return mapper.interviewTypeEntityToInterviewTypeDTO(
                repository.save(mapper.interviewTypeDTOToInterviewTypeEntity (interviewerTypeDTO))
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