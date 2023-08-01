package org.bedu.testing.services;

import org.bedu.testing.models.InterviewerDTO;

import java.util.List;
import java.util.Optional;
/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */

public interface IInterviewerService {

    List< InterviewerDTO> findAllInterviewers();


    Optional< InterviewerDTO> findInterviewerById(long id);

    InterviewerDTO saveInterviewer( InterviewerDTO data);

    InterviewerDTO updateInterviewer(long id,  InterviewerDTO data);

    public void delete(long id);

    public long count();

}