package jinbok.culture.comment.dto;

import jinbok.culture.comment.domain.Comment;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentResponse (
    Long id,
    String content,

    Long boardId,
    LocalDateTime createdAt
){
    public static CommentResponse toCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .boardId(comment.getBoardId())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
