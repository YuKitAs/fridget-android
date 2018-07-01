package edu.kit.pse.fridget.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import edu.kit.pse.fridget.server.models.Membership;
import edu.kit.pse.fridget.server.models.representations.UserMembershipRepresentation;
import edu.kit.pse.fridget.server.repositories.AccessCodeRepository;
import edu.kit.pse.fridget.server.repositories.MembershipRepository;
import edu.kit.pse.fridget.server.repositories.UserRepository;

@Service
public class MembershipServiceImpl implements MembershipService {
    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;
    private final AccessCodeRepository accessCodeRepository;
    private final MagnetColorService magnetColorService;

    @Autowired
    public MembershipServiceImpl(MembershipRepository membershipRepository, UserRepository userRepository,
            AccessCodeRepository accessCodeRepository, MagnetColorService magnetColorService) {
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
        this.accessCodeRepository = accessCodeRepository;
        this.magnetColorService = magnetColorService;
    }

    @Override
    public List<UserMembershipRepresentation> getAllMembers(String flatshareId) {
        return membershipRepository.findByFlatshareId(flatshareId)
                .stream()
                .map(membership -> new UserMembershipRepresentation(userRepository.getOne(membership.getUserId()), membership))
                .collect(Collectors.toList());
    }

    @Override
    public UserMembershipRepresentation getMember(String flatshareId, String userId) {
        return new UserMembershipRepresentation(userRepository.getOne(userId),
                membershipRepository.findByFlatshareIdAndUserId(flatshareId, userId));
    }

    @Override
    public Membership saveMembership(String accessCode, String userId, Membership.Builder membershipBuilder) {
        String flatshareId = accessCodeRepository.findByContent(accessCode).getFlatshareId();

        return saveMembership(membershipBuilder.setRandomId()
                .setUserId(userId)
                .setFlatshareId(flatshareId)
                .setMagnetColor(magnetColorService.getRandomColor(flatshareId))
                .build());
    }

    @Override
    public Membership saveMembership(Membership membership) {
        return membershipRepository.save(membership);
    }

    @Override
    public void deleteMembership(String flatshareId, String userId) {
        membershipRepository.deleteByFlatshareIdAndUserId(flatshareId, userId);
    }
}
