package org.bedu.testing.controllers.mappers;

import org.bedu.testing.models.InterviewTypeDTO;
import org.bedu.testing.persistence.entities.InterviewTypeEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InterviewTypeMapper {
    InterviewTypeEntity interviewTypeDTOToInterviewTypeEntity(InterviewTypeDTO interviewerTypeDTO);
    InterviewTypeDTO interviewTypeEntityToInterviewTypeDTO(InterviewTypeEntity interviewerTypeEntity);
}