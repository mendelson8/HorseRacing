package horses.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@Deprecated
public class CreateGame {
    private static final Logger logger = LoggerFactory.getLogger(CreateGame.class);

    @GetMapping("/create")
    public ResponseEntity<String> createGame() {
        logger.info("Legacy game creation endpoint called - redirecting to new API");
        return ResponseEntity.ok("Please use the new /race/create endpoint");
    }

    @GetMapping("/list")
    public ResponseEntity<String> getList() {
        logger.info("Legacy game list endpoint called - redirecting to new API");
        return ResponseEntity.ok("Please use the new /race/list endpoint");
    }

    @GetMapping("/list/{game_id}")
    public ResponseEntity<String> getGameById(@PathVariable int game_id) {
        logger.info("Legacy game detail endpoint called - redirecting to new API");
        return ResponseEntity.ok("Please use the new /race/{id} endpoint");
    }

    @GetMapping("/join/{game_id}")
    public ResponseEntity<String> joinGame(@PathVariable int game_id) {
        logger.info("Legacy game join endpoint called - redirecting to new API");
        return ResponseEntity.ok("Please use the new /bet/place endpoint");
    }

    @PostMapping("/bet/{game_id}")
    public ResponseEntity<String> bet(@PathVariable int game_id) {
        logger.info("Legacy bet endpoint called - redirecting to new API");
        return ResponseEntity.ok("Please use the new /bet/place endpoint");
    }
}
