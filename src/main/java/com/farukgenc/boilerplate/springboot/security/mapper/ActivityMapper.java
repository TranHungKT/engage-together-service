package com.farukgenc.boilerplate.springboot.security.mapper;

import com.farukgenc.boilerplate.springboot.model.Activity;
import com.farukgenc.boilerplate.springboot.security.dto.request.CreateActivityRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(imports = {UUID.class})
public interface ActivityMapper {
    ActivityMapper MAPPER = Mappers.getMapper(ActivityMapper.class);

    @Mapping(target = "createdBy", constant = "Sample")
    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "categories", ignore = true)
    Activity toActivity(CreateActivityRequest request);
}