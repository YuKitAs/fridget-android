package edu.kit.pse.fridget.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import edu.kit.pse.fridget.server.models.Membership;

public interface MembershipRepository extends JpaRepository<Membership, String> {
    List<Membership> findByFlatshareId(String flatshareId);

    Membership findByFlatshareIdAndUserId(String flatshareId, String userId);

    void deleteByFlatshareIdAndUserId(String flatshareId, String userId);
}
