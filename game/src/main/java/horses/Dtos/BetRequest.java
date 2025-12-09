package horses.Dtos;

import java.util.UUID;

public record BetRequest(
    UUID raceId,
    UUID horseId,
    Integer amount
) {}
