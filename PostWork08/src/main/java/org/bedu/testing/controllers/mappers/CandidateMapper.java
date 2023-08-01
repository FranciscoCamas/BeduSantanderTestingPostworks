package org.bedu.testing.controllers.mappers;

import org.bedu.testing.models.CandidateDTO;
import org.bedu.testing.persistence.entities.CandidateEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CandidateMapper {

    @Mapping(source = "last_Name",    target = "lastName")
    CandidateEntity candidateDTOToCandidateEntity(CandidateDTO candidateDTO);

    @Mapping(source = "lastName",    target = "last_Name")
    CandidateDTO candidateEntityToCandidateDTO(CandidateEntity interviewerEntity);
}
