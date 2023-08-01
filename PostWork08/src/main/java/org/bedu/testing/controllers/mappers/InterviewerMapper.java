package org.bedu.testing.controllers.mappers;

import org.bedu.testing.models.InterviewerDTO;
import org.bedu.testing.persistence.entities.InterviewerEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InterviewerMapper {

  //  @Mapping(source = "is_admin",     target = "admin")
   @Mapping(source = "last_Name",    target = "lastName")
    InterviewerEntity InterviewerDTOToInterviewerEntity(InterviewerDTO interviewerDTO);

    //@Mapping(source = "admin",        target = "is_admin")
    @Mapping(source = "lastName",    target = "last_Name")
    InterviewerDTO interviewerEntityToInterviewerDTO(InterviewerEntity interviewerEntity);
}
