package jinbok.culture.board.service;

import jinbok.culture.board.domain.Board;
import jinbok.culture.board.dto.BoardDetailResponse;
import jinbok.culture.board.dto.BoardRequest;
import jinbok.culture.board.dto.BoardResponse;
import jinbok.culture.board.repository.BoardRepository;
import jinbok.culture.comment.dto.CommentResponse;
import jinbok.culture.comment.service.CommentService;
import jinbok.culture.exception.RestApiException;
import jinbok.culture.exception.code.BoardErrorCode;
import jinbok.culture.exception.code.UserErrorCode;
import jinbok.culture.user.domain.User;
import jinbok.culture.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    public final CommentService commentService;
    public final BoardRepository boardRepository;
    private final UserService userService;

    public BoardDetailResponse createBoard(BoardRequest boardRequest, Long userId) {

        Board board = Board.builder()
                .user(userService.getUser(userId))
                .title(boardRequest.title())
                .content(boardRequest.content())
                .build();

        boardRepository.save(board);

        List<CommentResponse> comment = commentService.findAllCommentsByBoardId(board.getId());

        return BoardDetailResponse.toBoardDetailResponse(board,comment);
    }

    public Page<BoardResponse> findAllBoard(Pageable pageable) {
        Page<Board> boards = boardRepository.findAllByOrderByIdDesc(pageable);

        return boards.map(BoardResponse::toBoardResponse);
    }

    public BoardDetailResponse findBoardById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RestApiException(BoardErrorCode.INVALID_BOARD));

        List<CommentResponse> comment = commentService.findAllCommentsByBoardId(board.getId());

        return BoardDetailResponse.toBoardDetailResponse(board,comment);
    }

    public Page<BoardResponse> findBoardByTitle(String title, Pageable pageable) {
        Page<Board> boards = boardRepository.findBoardsByTitleContaining(title, pageable);

        return boards.map(BoardResponse::toBoardResponse);
    }

    public Page<BoardResponse> findBoardByUsername(String username, Pageable pageable) {
        Page<Board> boards = boardRepository.findBoardsByUsername(username, pageable);

        return boards.map(BoardResponse::toBoardResponse);
    }

    public BoardResponse updateBoard(Long id, BoardRequest boardRequest, Long userId) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new RestApiException(BoardErrorCode.INVALID_BOARD));

        if (!board.getUser().getId().equals(userId)) {
            throw new RestApiException(UserErrorCode.INVALID_CREDENTIALS);
        }

        board.updateBoard(boardRequest);

        return BoardResponse.toBoardResponse(board);
    }

    public BoardResponse deleteBoard(Long id, Long userId) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new RestApiException(BoardErrorCode.INVALID_BOARD));

        if (!board.getUser().getId().equals(userId)) {
            throw new RestApiException(UserErrorCode.INVALID_CREDENTIALS);
        }

        boardRepository.delete(board);

        return BoardResponse.toBoardResponse(board);
    }
}
