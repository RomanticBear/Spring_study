package me.gom.springbootdeveloper.repository;

import me.gom.springbootdeveloper.domain.ScheduleReference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleReferenceRepository extends JpaRepository<ScheduleReference, Long> {
}
