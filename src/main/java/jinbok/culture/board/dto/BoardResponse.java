package jinbok.culture.board.dto;

import jinbok.culture.board.domain.Board;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record BoardResponse(
    Long id,
    String username,

    String title,
    String content,

    LocalDateTime createdAt
) {
    public static BoardResponse toBoardResponse(Board board) {

        return BoardResponse.builder()
                .id(board.getId())
                .username(board.getUser().getUsername())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .build();
    }
}
