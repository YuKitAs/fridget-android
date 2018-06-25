package edu.kit.pse.fridget.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import edu.kit.pse.fridget.server.models.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByGoogleUserId(String googleUserId);
}
