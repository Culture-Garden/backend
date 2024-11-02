package jinbok.culture.board.controller;

import jinbok.culture.board.dto.BoardRequest;
import jinbok.culture.board.dto.BoardResponse;
import jinbok.culture.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080") // 특정 출처 허용
public class BoardController {

    public final BoardService boardService;

    @PostMapping("/movie")
    public BoardResponse createBoard(@RequestBody BoardRequest boardRequest) {
        return boardService.createBoard(boardRequest);
    }
    @GetMapping("/movie")
    public List<BoardResponse> findAllBoard() {
        return boardService.findAllBoard();
    }

    @GetMapping("/movie/{id}")
    public BoardResponse findBoardById(@PathVariable Long id) {
        return boardService.findBoardById(id);
    }


}
