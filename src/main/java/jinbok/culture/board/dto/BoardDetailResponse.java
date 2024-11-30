package jinbok.culture.board.dto;

import jinbok.culture.board.domain.Board;
import jinbok.culture.comment.dto.CommentResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BoardDetailResponse (
    Long id,
    String username,

    String title,
    String content,
    String imageUrl,

    LocalDateTime createdAt,
    List<CommentResponse> comments
    )
{
    public static BoardDetailResponse toBoardDetailResponse(Board board, List<CommentResponse> comments) {
        return BoardDetailResponse.builder()
                .id(board.getId())
                .username(board.getUser().getUsername())
                .title(board.getTitle())
                .content(board.getContent())
                .imageUrl(board.getImageUrl())
                .createdAt(board.getCreatedAt())
                .comments(comments)
                .build();
    }
}
