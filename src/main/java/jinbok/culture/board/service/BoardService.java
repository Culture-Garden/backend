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
import jinbok.culture.exception.code.S3ErrorCode;
import jinbok.culture.exception.code.UserErrorCode;
import jinbok.culture.service.S3Service;
import jinbok.culture.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    public final CommentService commentService;
    public final BoardRepository boardRepository;
    public final UserService userService;
    public final S3Service s3Service;

    public BoardDetailResponse createBoard(BoardRequest boardRequest, MultipartFile image, Long userId) throws IOException {

        Board board = Board.builder()
                .user(userService.getUser(userId))
                .title(boardRequest.title())
                .content(boardRequest.content())
                .imageUrl(s3Service.upload(image,"BoardImage"))
                .build();

        boardRepository.save(board);

        List<CommentResponse> comment = commentService.findAllCommentsByBoardId(board.getId());

        return BoardDetailResponse.toBoardDetailResponse(board,comment);
    }

    public Page<BoardResponse> findAllBoard(Pageable pageable) {
        Page<Board> boards = boardRepository.findAllByOrderByIdDesc(pageable);

        return boards.map(BoardResponse::toBoardResponse);
    }

    public BoardDetailResponse findBoardByIdToDto(Long id) {
        Board board = findBoardById(id);

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

    public BoardResponse updateBoard(Long id, BoardRequest boardRequest, MultipartFile image, Long userId) throws IOException {

        Board board = boardRepository.findById(id).orElseThrow(() -> new RestApiException(BoardErrorCode.INVALID_BOARD));

        if (!board.getUser().getId().equals(userId)) {
            throw new RestApiException(BoardErrorCode.INVALID_USER);
        }

        board.updateBoard(boardRequest, s3Service.upload(image, "BoardImage"));

        return BoardResponse.toBoardResponse(board);
    }

    public BoardResponse deleteBoard(Long id, Long userId) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new RestApiException(BoardErrorCode.INVALID_BOARD));

        if (!board.getUser().getId().equals(userId)) {
            throw new RestApiException(BoardErrorCode.INVALID_USER);
        }

        String ImageUrl = board.getImageUrl();

        s3Service.deleteS3(Optional.ofNullable(ImageUrl)
                .orElseThrow(() -> new RestApiException(S3ErrorCode.INVALID_IMAGE)));

        boardRepository.delete(board);

        return BoardResponse.toBoardResponse(board);
    }

    public Board findBoardById(Long boardId){
        return boardRepository.findById(boardId).orElseThrow(() -> new RestApiException(BoardErrorCode.INVALID_BOARD));
    }
}
