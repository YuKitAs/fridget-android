package edu.kit.pse.fridget.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.kit.pse.fridget.server.models.AccessCode;

public interface AccessCodeRepository extends JpaRepository<AccessCode, String> {
    AccessCode findByContent(String content);
}
