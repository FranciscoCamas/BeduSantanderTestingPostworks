package org.bedu.testing.services;

import org.bedu.testing.models.InterviewTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */

public interface IInterviewTypeService {
    List<InterviewTypeDTO> findAllInterviewTypes();

    Optional< InterviewTypeDTO> findInterviewTypeById(long id);

    InterviewTypeDTO saveInterviewType( InterviewTypeDTO data);

    InterviewTypeDTO updateInterviewType(long id,  InterviewTypeDTO data);

    public void delete(long id);

    public long count();

}