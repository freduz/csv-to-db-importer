package com.workflex.workation_platform_backend.service.impl;

import com.workflex.workation_platform_backend.entity.Workation;
import com.workflex.workation_platform_backend.mapper.Mapper;
import com.workflex.workation_platform_backend.modal.WorkationDto;
import com.workflex.workation_platform_backend.repository.WorkationRepository;
import com.workflex.workation_platform_backend.service.WorkationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class WorkationServiceImpl implements WorkationService {
    private final WorkationRepository repository;
    private final Mapper<WorkationDto, Workation> mapper;

    @Override
    public Collection<WorkationDto> getWorkation() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
