package jinbok.culture.user.dto;


public record UserRequest(
        String loginId,
        String username,
        String password
) {}
