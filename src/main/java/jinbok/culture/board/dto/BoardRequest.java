package jinbok.culture.board.dto;

import jakarta.validation.constraints.NotBlank;

public record BoardRequest(

        @NotBlank(message = "제목은 반드시 작성해야합니다.")
        String title,
        String content
) {}
