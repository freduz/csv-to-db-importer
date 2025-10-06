package com.workflex.workation_platform_backend.mapper;

import com.workflex.workation_platform_backend.entity.Workation;
import com.workflex.workation_platform_backend.modal.WorkationDto;
import org.springframework.stereotype.Component;

@Component
public class WorkationMapper implements Mapper<WorkationDto, Workation> {
    @Override
    public WorkationDto toDomain(Workation workationEntity) {
        return WorkationDto.builder()
                .employee(workationEntity.getEmployee())
                .origin(workationEntity.getOrigin())
                .destination(workationEntity.getDestination())
                .start(workationEntity.getStart())
                .end(workationEntity.getEnd())
                .workingDays(workationEntity.getWorkingDays())
                .risk(workationEntity.getRisk())
                .build();
    }
}
