package jinbok.culture.board.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jinbok.culture.board.dto.BoardRequest;
import jinbok.culture.board.dto.BoardResponse;
import jinbok.culture.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/movie")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class BoardController {

    public final BoardService boardService;

    @PostMapping()
    public BoardResponse createBoard(@Valid @RequestBody BoardRequest boardRequest, HttpSession session){

        Object object = session.getAttribute("user");

        return boardService.createBoard(boardRequest, object);
    }

    @GetMapping("/all")
    public Page<BoardResponse> findAllBoard(Pageable pageable) {
        return boardService.findAllBoard(pageable);
    }

    @GetMapping("/{id}")
    public BoardResponse findBoardById(@PathVariable Long id){
        return boardService.findBoardById(id);
    }

    @GetMapping()
    public Page<BoardResponse> findBoardByTitle(@RequestParam(required = false) String title,
                                                @RequestParam(required = false) String username,
                                                Pageable pageable){
        if (title != null) {
            return boardService.findBoardByTitle(title, pageable);
        } else if (username != null) {
            return boardService.findBoardByUsername(username, pageable);
        } else {
            return boardService.findAllBoard(pageable);
        }
    }

    @PutMapping("/{id}")
    public BoardResponse updateBoard(@PathVariable Long id, @Valid @RequestBody BoardRequest boardRequest){

        return boardService.updateBoard(id, boardRequest);
    }

    @DeleteMapping("/{id}")
    public BoardResponse deleteBoardById(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }


}
