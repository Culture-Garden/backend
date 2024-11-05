package jinbok.culture.board.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jinbok.culture.board.domain.Board;
import jinbok.culture.board.dto.BoardRequest;
import jinbok.culture.board.dto.BoardResponse;
import jinbok.culture.board.repository.BoardRepository;
import jinbok.culture.user.domain.User;
import jinbok.culture.user.repository.UserRepository;
import jinbok.culture.user.service.AuthService;
import jinbok.culture.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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

    public List<BoardResponse> findAllBoard() {
        List<Board> boards = boardRepository.findAll();

        return boards.stream()
                .map(BoardResponse::toBoardResponse)
                .collect(Collectors.toList());
    }

    public BoardResponse findBoardById(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        assert board != null : "해당 BoardId를 가진 보드는 없습니다.";
        return BoardResponse.toBoardResponse(board);
    }
}
