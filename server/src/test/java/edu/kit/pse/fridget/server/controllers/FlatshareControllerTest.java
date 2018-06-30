package edu.kit.pse.fridget.server.controllers;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import edu.kit.pse.fridget.server.models.Flatshare;

import static org.assertj.core.api.Assertions.assertThat;

public class FlatshareControllerTest extends AbstractControllerTest {
    private static final String FLATSHARE_ID = "00000000-0000-0000-0000-000000000000";

    @Test
    public void getFlatshare_ReturnsCorrectValue() {
        ResponseEntity<Flatshare> flatshareResponse = getTestRestTemplate().getForEntity(String.format("/flatshares/%s", FLATSHARE_ID),
                Flatshare.class);

        assertThat(flatshareResponse.getBody().getId()).isEqualTo(FLATSHARE_ID);
        assertThat(flatshareResponse.getBody().getName()).isEqualTo("An Awesome Flatshare");
    }
}