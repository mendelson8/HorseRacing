package horses.Dtos;


import java.util.List;
import java.util.UUID;

public record CreateRaceRequest(
    String name,
    List<UUID> horseIds
) {}
