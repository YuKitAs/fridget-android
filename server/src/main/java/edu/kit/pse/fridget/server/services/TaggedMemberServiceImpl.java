package edu.kit.pse.fridget.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import edu.kit.pse.fridget.server.models.Membership;
import edu.kit.pse.fridget.server.models.TaggedMember;
import edu.kit.pse.fridget.server.repositories.MembershipRepository;
import edu.kit.pse.fridget.server.repositories.TaggedMemberRepository;

@Service
public class TaggedMemberServiceImpl implements TaggedMemberService {
    private final TaggedMemberRepository taggedMemberRepository;
    private final MembershipRepository membershipRepository;

    @Autowired
    public TaggedMemberServiceImpl(TaggedMemberRepository taggedMemberRepository, MembershipRepository membershipRepository) {
        this.taggedMemberRepository = taggedMemberRepository;
        this.membershipRepository = membershipRepository;
    }

    @Override
    public List<Membership> getAllTaggedMembers(String coolNoteId) {
        return taggedMemberRepository.findByCoolNoteId(coolNoteId)
                .stream()
                .map(TaggedMember::getMembershipId)
                .map(membershipRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    public TaggedMember saveTaggedMember(String membershipId, String coolNoteId) {
        return taggedMemberRepository.save(TaggedMember.buildNew(membershipId, coolNoteId));
    }
}
