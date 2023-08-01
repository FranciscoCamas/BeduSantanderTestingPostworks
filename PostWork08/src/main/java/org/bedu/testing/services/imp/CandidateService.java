package org.bedu.testing.services.imp;

import org.bedu.testing.controllers.mappers.CandidateMapper;
import org.bedu.testing.models.CandidateDTO;
import org.bedu.testing.persistence.ICandidateRepository;
import org.bedu.testing.persistence.IInterviewerRepository;
import org.bedu.testing.persistence.entities.CandidateEntity;
import org.bedu.testing.persistence.entities.InterviewerEntity;
import org.bedu.testing.services.ICandidateService;
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
public class CandidateService implements ICandidateService {

    private final ICandidateRepository repository;
    private final CandidateMapper     mapper;

    @Autowired
    public CandidateService(ICandidateRepository repository, CandidateMapper mapper) {
        this.repository = repository;
        this.mapper     = mapper;
    }

    @Override
    public List<CandidateDTO> findAllCandidates() {
        return repository.findAll().stream().map(candidate -> mapper.candidateEntityToCandidateDTO(candidate)).toList();// .collect(Collectors.toList());
    }

    public List<CandidateDTO> findByNameOrEmail(String keyword) {
        return repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrLastNameContainingIgnoreCase(keyword,keyword,keyword).stream().map(candidate -> mapper.candidateEntityToCandidateDTO(candidate)).toList();// .collect(Collectors.toList());
    // return null;
    }
    @Override
    public Optional<CandidateDTO> findCandidateById(long idCandidate) {
        Optional<CandidateEntity> candidateDB = repository.findById(idCandidate);
        return candidateDB.isPresent() ? Optional.of(mapper.candidateEntityToCandidateDTO(candidateDB.get())) :Optional.empty();
    }
    @Override
    public CandidateDTO saveCandidate(  CandidateDTO candidate) {
        return mapper.candidateEntityToCandidateDTO (
                repository.save (mapper.candidateDTOToCandidateEntity  (candidate))
        );
    }

    @Override
    public CandidateDTO updateCandidate(long id,  CandidateDTO candidateDTO) {
        Optional<CandidateEntity> candidateDB = repository.findById(id);

        if (!candidateDB.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The candidate that do you want to update does not exist.");

        candidateDTO.setId(id);
        return mapper.candidateEntityToCandidateDTO(
                repository.save(mapper.candidateDTOToCandidateEntity (candidateDTO))
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