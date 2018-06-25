package edu.kit.pse.fridget.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.kit.pse.fridget.server.models.Flatshare;

public interface FlatshareRepository extends JpaRepository<Flatshare, String> {
}
