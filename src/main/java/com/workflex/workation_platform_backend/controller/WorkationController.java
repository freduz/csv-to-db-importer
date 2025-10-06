package com.workflex.workation_platform_backend.controller;

import com.workflex.workation_platform_backend.modal.WorkationDto;
import com.workflex.workation_platform_backend.service.WorkationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("/api/v1/workflex/workation/")
@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class WorkationController {

    private final WorkationService service;
    @GetMapping
    public ResponseEntity<Collection<WorkationDto>> getWorkation() {
        Collection<WorkationDto> workation = service.getWorkation();
        return new ResponseEntity<>(workation, HttpStatus.OK);
    }
}
