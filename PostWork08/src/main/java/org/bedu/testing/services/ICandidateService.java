package org.bedu.testing.services;

import org.bedu.testing.models.CandidateDTO;

import java.util.List;
import java.util.Optional;
/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */
public interface ICandidateService {

    List<CandidateDTO> findAllCandidates();

    Optional< CandidateDTO> findCandidateById(long id);

    CandidateDTO saveCandidate( CandidateDTO data);

    CandidateDTO updateCandidate(long id,  CandidateDTO data);

    public void delete(long id);

    public long count();

}