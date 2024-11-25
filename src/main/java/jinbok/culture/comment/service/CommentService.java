package jinbok.culture.comment.service;

import jinbok.culture.comment.domain.Comment;
import jinbok.culture.comment.dto.CommentRequest;
import jinbok.culture.comment.dto.CommentResponse;
import jinbok.culture.comment.repository.CommentRepository;
import jinbok.culture.exception.RestApiException;
import jinbok.culture.exception.code.CommentErrorCode;
import jinbok.culture.user.domain.User;
import jinbok.culture.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;

    public CommentResponse createComment(Long boardId, CommentRequest commentRequest, Long userId) {

        Comment comment = Comment.builder()
                .content(commentRequest.content())
                .user(userService.getUser(userId))
                .boardId(boardId)
                .build();

        commentRepository.save(comment);

        return CommentResponse.toCommentResponse(comment);
    }

    public List<CommentResponse> findAllCommentsByBoardId(Long boardId) {
        List<Comment> comment = commentRepository.findAllByBoardId(boardId);
        return comment.stream().
                map(CommentResponse::toCommentResponse).
                collect(Collectors.toList());
    }

    public CommentResponse updateComment(Long boardId, Long commentId, CommentRequest commentRequest, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();

        if (!comment.getBoardId().equals(boardId)) {
            throw new RestApiException(CommentErrorCode.INVALID_BOARD);
        }

        if (!comment.getUser().getId().equals(userId)) {
            throw new RestApiException(CommentErrorCode.INVALID_USER);
        }

        comment.updateComment(commentRequest);

        return CommentResponse.toCommentResponse(comment);
    }

    public CommentResponse deleteComment(Long boardId, Long commentId, Long userId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RestApiException(CommentErrorCode.INVALID_COMMENT));

        if (!comment.getBoardId().equals(boardId)) {
            throw new RestApiException(CommentErrorCode.INVALID_BOARD);
        }

        if (!comment.getUser().getId().equals(userId)) {
            throw new RestApiException(CommentErrorCode.INVALID_USER);
        }

        commentRepository.delete(comment);

        return CommentResponse.toCommentResponse(comment);
    }

}
