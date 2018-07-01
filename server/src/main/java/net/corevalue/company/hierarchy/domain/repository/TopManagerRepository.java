package net.corevalue.company.hierarchy.domain.repository;

import net.corevalue.company.hierarchy.domain.model.TopManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopManagerRepository extends JpaRepository<TopManager, Long> {

    Optional<TopManager> findByRole(String role);
}
