package jinbok.culture.comment.service;

import jinbok.culture.board.dto.BoardResponse;
import jinbok.culture.board.service.BoardService;
import jinbok.culture.comment.domain.Comment;
import jinbok.culture.comment.dto.CommentRequest;
import jinbok.culture.comment.dto.CommentResponse;
import jinbok.culture.comment.repository.CommentRepository;
import jinbok.culture.user.domain.User;
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

    public CommentResponse createComment(Long boardId, CommentRequest commentRequest, Object object) {

        Comment comment = Comment.builder()
                .content(commentRequest.content())
                .user((User) object)
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

    public CommentResponse updateComment(Long boardId, Long commentId, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();

        if (!comment.getBoardId().equals(boardId)) {
            throw new IllegalArgumentException("댓글이 옳지 않은 게시글에 존재");
        }

        comment.updateComment(commentRequest);

        return CommentResponse.toCommentResponse(comment);
    }

    public CommentResponse deleteComment(Long boardId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();

        if (!comment.getBoardId().equals(boardId)) {
            throw new IllegalArgumentException("댓글이 옳지 않은 게시글에 존재");
        }

        commentRepository.delete(comment);

        return CommentResponse.toCommentResponse(comment);
    }

}
