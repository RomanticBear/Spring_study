package me.gom.springbootdeveloper.repository;

import me.gom.springbootdeveloper.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}