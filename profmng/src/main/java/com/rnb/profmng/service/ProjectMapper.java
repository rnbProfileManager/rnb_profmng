package com.rnb.profmng.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.rnb.profmng.dto.ProjectDTO;
import com.rnb.profmng.entity.project.Project;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectMapper {
    @Mapping(source = "projectCd", target = "projectPk.projectCd")
    @Mapping(source = "projectNm", target = "projectPk.projectNm")
    @Mapping(source = "startDate", target = "projectPk.startDate")
//	@Mapping(target = "projectPk", ignore = true)
    void updateProjectFromDto(ProjectDTO dto, @MappingTarget Project entity);
}