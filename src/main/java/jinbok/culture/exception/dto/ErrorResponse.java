package jinbok.culture.exception.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
public record ErrorResponse(
    String code,
    String message
) {}
