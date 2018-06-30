package edu.kit.pse.fridget.server.controllers;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import edu.kit.pse.fridget.server.models.Flatshare;
import edu.kit.pse.fridget.server.models.commands.SaveFlatshareCommand;

import static org.assertj.core.api.Assertions.assertThat;

public class FlatshareControllerIntegrationTest extends AbstractControllerIntegrationTest {
    private static final String FLATSHARE_ID = "00000000-0000-0000-0000-000000000000";

    @Test
    public void getFlatshare_ReturnsCorrectResponse() {
        ResponseEntity<Flatshare> flatshareResponse = getTestRestTemplate().getForEntity(String.format("/flatshares/%s", FLATSHARE_ID),
                Flatshare.class);

        assertThat(flatshareResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(flatshareResponse.getBody()).satisfies(body -> {
            assertThat(body.getId()).isEqualTo(FLATSHARE_ID);
            assertThat(body.getName()).isEqualTo("An Awesome Flatshare");
        });
    }

    @Test
    public void saveFlatshare_ReturnsCorrectResponse() throws Exception {
        SaveFlatshareCommand saveFlatshareCommand = getFixture("saveFlatshareCommand.json", SaveFlatshareCommand.class);
        ResponseEntity<Flatshare> flatshareResponse = getTestRestTemplate().postForEntity("/flatshares", saveFlatshareCommand,
                Flatshare.class);

        assertThat(flatshareResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(flatshareResponse.getBody()).satisfies(body -> {
            assertThat(body.getId()).matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}");
            assertThat(body.getName()).isEqualTo("Another Awesome Flatshare");
        });
    }
}