package jinbok.culture.board.dto;

import jakarta.validation.Valid;

public record BoardRequest(
        String title,
        String content
) {}
