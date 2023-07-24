package org.bedu.testing.controllers.mappers;

import org.bedu.testing.models.InterviewerDTO;
import org.bedu.testing.persistence.entities.InterviewerEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InterviewerMapper {

    InterviewerEntity InterviewerDTOToInterviewerEntity(InterviewerDTO interviewerModel);

    InterviewerDTO interviewerEntityToInterviewerDTO(InterviewerEntity interviewerEntity);
}
