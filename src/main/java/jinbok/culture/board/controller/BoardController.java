package jinbok.culture.board.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jinbok.culture.board.dto.BoardRequest;
import jinbok.culture.board.dto.BoardResponse;
import jinbok.culture.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class BoardController {

    public final BoardService boardService;

    @PostMapping("/movie")
    public BoardResponse createBoard(@Valid @RequestBody BoardRequest boardRequest, HttpSession session){

        Object object = session.getAttribute("user");
        return boardService.createBoard(boardRequest, object);
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
