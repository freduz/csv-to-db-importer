package com.workflex.workation_platform_backend.repository;

import com.workflex.workation_platform_backend.entity.Workation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkationRepository extends JpaRepository<Workation, String> {
}
