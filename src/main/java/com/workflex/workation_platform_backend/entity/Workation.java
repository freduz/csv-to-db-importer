package com.workflex.workation_platform_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
public class Workation {

    @Id
    private String workationId;
    private String employee;
    private String origin;
    private String destination;
    @Column(name = "start_date")
    private LocalDate start;
    @Column(name = "end_date")
    private LocalDate end;
    private Integer workingDays;
    private String risk;

    public Workation() {
    }
}