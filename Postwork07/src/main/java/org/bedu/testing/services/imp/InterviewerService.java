package org.bedu.testing.services.imp;

import org.bedu.testing.controllers.mappers.InterviewerMapper;
import org.bedu.testing.models.InterviewerDTO;
import org.bedu.testing.persistence.IInterviewerRepository;
import org.bedu.testing.persistence.entities.InterviewerEntity;
import org.bedu.testing.services.IInterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;



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
        return repository.findAll().stream().map(client -> mapper.interviewerEntityToInterviewerDTO(client)).toList();// .collect(Collectors.toList());
    }
    @Override
    public Optional<InterviewerDTO> findInterviewerById(long idClient) {
        Optional<InterviewerEntity> guest = repository.findById(idClient);
        return guest.isPresent() ? Optional.of(mapper.interviewerEntityToInterviewerDTO(guest.get())) :Optional.empty();
    }
    @Override
    public InterviewerDTO saveInterviewer(InterviewerDTO interviewer) {
        return mapper.interviewerEntityToInterviewerDTO (
                repository.save (mapper.InterviewerDTOToInterviewerEntity  (interviewer))
        );
    }

    @Override
    public InterviewerDTO updateInterviewer(long id, InterviewerDTO clientDTO) {
        Optional<InterviewerEntity> actual = repository.findById(id);

        if (!actual.isPresent()) {
            //     throw new ClientNotFoundException("Client not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The client that do you want to update does not exist.");
        }
        clientDTO.setId(id);
        return mapper.interviewerEntityToInterviewerDTO(
                repository.save(mapper.InterviewerDTOToInterviewerEntity (clientDTO))
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
