package me.gom.springbootdeveloper.repository;

import me.gom.springbootdeveloper.domain.Manual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualRepository extends JpaRepository<Manual, Long> {
}
