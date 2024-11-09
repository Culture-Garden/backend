package jinbok.culture.user.dto;

import jinbok.culture.user.domain.User;
import lombok.Builder;

@Builder
public record UserResponse(
        Long id,

        String loginId,
        String username,
        String password
)
{
    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
