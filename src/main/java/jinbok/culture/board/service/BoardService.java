package jinbok.culture.board.service;

import jinbok.culture.board.domain.Board;
import jinbok.culture.board.dto.BoardRequest;
import jinbok.culture.board.dto.BoardResponse;
import jinbok.culture.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BoardService {

    public final BoardRepository boardRepository;

    public BoardResponse createBoard(BoardRequest boardRequest) {
        Board board = Board.builder()
                .title(boardRequest.title())
                .content(boardRequest.content())
                .imgSrc(boardRequest.imgSrc())
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
        assert board != null;
        return BoardResponse.toBoardResponse(board);
    }
}
