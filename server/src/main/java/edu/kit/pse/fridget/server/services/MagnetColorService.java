package edu.kit.pse.fridget.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import edu.kit.pse.fridget.server.models.Membership;
import edu.kit.pse.fridget.server.repositories.MembershipRepository;

@Service
public class MagnetColorService {
    private final MembershipRepository membershipRepository;

    private final String[] colors = {"0099cc", "0000ff", "666699", "cc00ff", "cc0099", "990033", "ff0000", "ff9900", "cccc00", "009900",
            "006600", "009999", "ffcccc", "ffffff", "996633"};

    @Autowired
    public MagnetColorService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    String getRandomColor() {
        return colors[new Random().nextInt(15)];
    }

    String getRandomColor(String flatshareId) {
        List<String> magnetColors = membershipRepository.findByFlatshareId(flatshareId)
                .stream()
                .map(Membership::getMagnetColor)
                .collect(Collectors.toList());

        List<String> availableColors = Arrays.stream(colors)
                .collect(Collectors.toList())
                .stream()
                .filter(color -> !magnetColors.contains(color))
                .collect(Collectors.toList());

        return availableColors.get(new Random().nextInt(availableColors.size()));
    }
}
