package dev.dbelahcen.playerservice;

import dev.dbelahcen.playerservice.api.PlayerServiceController;
import dev.dbelahcen.playerservice.exceptions.PlayerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "players.csv-file.path=src/test/resources/player_test.csv"
})
class PlayerServiceApplicationTests {

    @Autowired
    private PlayerServiceController playerServiceController;

    @Test
    void testGetAllPlayers() {
        StepVerifier.create(playerServiceController.getPlayers(0, 2))
                .assertNext(entity -> {
                    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
                    assertThat(entity.getBody().getPlayers().size()).isEqualTo(2);
                })
                .verifyComplete();
    }

    @Test
    void testGetById() {
        StepVerifier.create(playerServiceController.getPlayerById("aardsda01"))
                // assertions should be expanded to more fields.
                .assertNext(entity -> assertThat(entity.getBody().getPlayers().get(0).getFirstName()).isEqualTo("David"))
                .verifyComplete();
    }

    @Test
    void testGetByIdNotFound() {
        StepVerifier.create(playerServiceController.getPlayerById("not_found_id"))
                .expectError(PlayerNotFoundException.class)
                .verify();
    }
}
