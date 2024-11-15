package jinbok.culture.board.service;

import jinbok.culture.board.domain.Board;
import jinbok.culture.board.dto.BoardRequest;
import jinbok.culture.board.dto.BoardResponse;
import jinbok.culture.board.repository.BoardRepository;
import jinbok.culture.user.domain.User;
import jinbok.culture.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    public final BoardRepository boardRepository;
    public final AuthService authService;

    public BoardResponse createBoard(BoardRequest boardRequest, Object object) {

        Board board = Board.builder()
                .user((User) object)
                .title(boardRequest.title())
                .content(boardRequest.content())
                .build();

        boardRepository.save(board);

        return BoardResponse.toBoardResponse(board);
    }

    public Page<BoardResponse> findAllBoard(Pageable pageable) {
        Page<Board> boards = boardRepository.findAllByOrderByIdDesc(pageable);

        return boards.map(BoardResponse::toBoardResponse);
    }

    public BoardResponse findBoardById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();

        return BoardResponse.toBoardResponse(board);
    }

    public Page<BoardResponse> findBoardByTitle(String title, Pageable pageable) {
        Page<Board> boards = boardRepository.findBoardsByTitleContaining(title, pageable);

        return boards.map(BoardResponse::toBoardResponse);
    }

    public Page<BoardResponse> findBoardByUsername(String username, Pageable pageable) {
        Page<Board> boards = boardRepository.findBoardsByUsername(username, pageable);

        return boards.map(BoardResponse::toBoardResponse);
    }

    public BoardResponse updateBoard(Long id, BoardRequest boardRequest) {

        Board board = boardRepository.findById(id).orElseThrow();

        board.updateBoard(boardRequest);

        return BoardResponse.toBoardResponse(board);
    }

    public BoardResponse deleteBoard(Long id) {

        Board board = boardRepository.findById(id).orElseThrow();

        boardRepository.delete(board);

        return BoardResponse.toBoardResponse(board);
    }
}
