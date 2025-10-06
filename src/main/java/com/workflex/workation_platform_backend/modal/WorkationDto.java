package com.workflex.workation_platform_backend.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkationDto {
    private String employee;
    private String origin;
    private String destination;
    private LocalDate start;
    private LocalDate end;
    private Integer workingDays;
    private String risk;
}
