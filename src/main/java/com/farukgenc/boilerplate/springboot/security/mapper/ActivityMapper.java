package com.farukgenc.boilerplate.springboot.security.mapper;

import com.farukgenc.boilerplate.springboot.model.Activity;
import com.farukgenc.boilerplate.springboot.security.dto.request.CreateActivityRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActivityMapper {
    ActivityMapper MAPPER = Mappers.getMapper(ActivityMapper.class);

    @Mapping(target = "createdBy", constant = "Sample")
    Activity toActivity(CreateActivityRequest request);
}
