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
@CrossOrigin(origins = {"http://localhost:80", "http://localhost:8080"}, allowCredentials = "true")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentResponse createComment(@PathVariable Long boardId, @RequestBody CommentRequest commentRequest, HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        return commentService.createComment(boardId, commentRequest, userId);
    }

    @GetMapping
    public List<CommentResponse> findAllCommentsByBoardId(@PathVariable Long boardId) {
        return commentService.findAllCommentsByBoardId(boardId);
    }

    @PutMapping("/{commentId}")
    public CommentResponse updateComment(@PathVariable Long boardId, @PathVariable Long commentId,
                                         @RequestBody CommentRequest commentRequest, HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        return commentService.updateComment(boardId, commentId, commentRequest, userId);
    }

    @DeleteMapping("/{commentId}")
    public CommentResponse deleteComment(@PathVariable Long boardId, @PathVariable Long commentId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        return commentService.deleteComment(boardId, commentId, userId);
    }

}
