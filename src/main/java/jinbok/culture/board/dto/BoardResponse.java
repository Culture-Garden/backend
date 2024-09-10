package jinbok.culture.board.dto;

import jinbok.culture.board.domain.Board;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record BoardResponse(
    Long id,

    String title,
    String content,

    String imgSrc,

    LocalDateTime createdAt
) {
    public static BoardResponse toBoardResponse(Board board) {

        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .imgSrc(board.getImgSrc())
                .createdAt(board.getCreatedAt())
                .build();
    }
}
