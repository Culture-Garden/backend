package jinbok.culture.comment.controller;

import jakarta.servlet.http.HttpSession;
import jinbok.culture.comment.dto.CommentRequest;
import jinbok.culture.comment.dto.CommentResponse;
import jinbok.culture.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment/{boardId}")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentResponse createComment(@PathVariable Long boardId, @RequestBody CommentRequest commentRequest, HttpSession session) {

        Object object = session.getAttribute("user");

        return commentService.createComment(boardId, commentRequest, object);
    }

    @GetMapping
    public List<CommentResponse> findAllCommentsByBoardId(@PathVariable Long boardId) {
        return commentService.findAllCommentsByBoardId(boardId);
    }

    @PutMapping("/{commentId}")
    public CommentResponse updateComment(@PathVariable Long boardId, @PathVariable Long commentId,
                                         @RequestBody CommentRequest commentRequest) {

        return commentService.updateComment(boardId, commentId, commentRequest);
    }

    @DeleteMapping("/{commentId}")
    public CommentResponse deleteComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        return commentService.deleteComment(boardId, commentId);
    }

}
