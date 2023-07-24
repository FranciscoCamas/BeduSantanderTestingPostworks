package org.bedu.testing.services;


import org.bedu.testing.models.InterviewerDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */

public interface IInterviewerService {

    List<@Valid InterviewerDTO> findAllInterviewers();

    Optional<@Valid InterviewerDTO> findInterviewerById(long id);

    InterviewerDTO saveInterviewer(@Valid InterviewerDTO data);


    InterviewerDTO updateInterviewer(long id, @Valid InterviewerDTO data);

    public void delete(long id);

    public long count();

}
