package horses.Dtos;

public record LoginResponse(
    String token,
    String username,
    Integer points
) {}
