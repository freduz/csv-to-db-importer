package com.workflex.workation_platform_backend.service;

import com.workflex.workation_platform_backend.modal.WorkationDto;

import java.util.Collection;

public interface WorkationService {
    Collection<WorkationDto> getWorkation();
}
