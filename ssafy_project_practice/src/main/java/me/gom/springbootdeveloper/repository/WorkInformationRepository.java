package me.gom.springbootdeveloper.repository;

import me.gom.springbootdeveloper.domain.WorkInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkInformationRepository extends JpaRepository<WorkInformation, Long> {
}
