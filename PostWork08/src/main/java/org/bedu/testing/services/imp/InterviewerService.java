package org.bedu.testing.services.imp;

import org.bedu.testing.controllers.mappers.InterviewerMapper;
import org.bedu.testing.models.InterviewerDTO;
import org.bedu.testing.persistence.IInterviewerRepository;
import org.bedu.testing.persistence.entities.InterviewerEntity;
import org.bedu.testing.services.IInterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */

@Service
public class InterviewerService implements IInterviewerService {

    private final IInterviewerRepository repository;
    private final InterviewerMapper       mapper;

    @Autowired
    public InterviewerService(IInterviewerRepository repository, InterviewerMapper mapper) {
        this.repository = repository;
        this.mapper     = mapper;
    }


    @Override
    public List<InterviewerDTO> findAllInterviewers() {
        return repository.findAll().stream().map(interviewer -> mapper.interviewerEntityToInterviewerDTO(interviewer)).toList();// .collect(Collectors.toList());
    }

    public List<InterviewerDTO> findByNameOrEmail(String keyword) {
        return repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrLastNameContainingIgnoreCase(keyword,keyword,keyword).stream().map(interviewer -> mapper.interviewerEntityToInterviewerDTO(interviewer)).toList();// .collect(Collectors.toList());
    }
    @Override
    public Optional<InterviewerDTO> findInterviewerById(long idInterviewer) {
        Optional<InterviewerEntity> interviewerDB = repository.findById(idInterviewer);
        return interviewerDB.isPresent() ? Optional.of(mapper.interviewerEntityToInterviewerDTO(interviewerDB.get())) :Optional.empty();
    }
    @Override
    public InterviewerDTO saveInterviewer(  InterviewerDTO interviewer) {
        return mapper.interviewerEntityToInterviewerDTO (
                repository.save (mapper.InterviewerDTOToInterviewerEntity  (interviewer))
        );
    }

    @Override
    public InterviewerDTO updateInterviewer(long id,  InterviewerDTO interviewerDTO) {
        Optional<InterviewerEntity> interviewerDB = repository.findById(id);

        if (!interviewerDB.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The client that do you want to update does not exist.");

        interviewerDTO.setId(id);
        return mapper.interviewerEntityToInterviewerDTO(
                repository.save(mapper.InterviewerDTOToInterviewerEntity (interviewerDTO))
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