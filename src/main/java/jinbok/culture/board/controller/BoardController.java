package jinbok.culture.board.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jinbok.culture.board.dto.BoardDetailResponse;
import jinbok.culture.board.dto.BoardRequest;
import jinbok.culture.board.dto.BoardResponse;
import jinbok.culture.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/board/movie")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:80", "http://localhost:8080"}, allowCredentials = "true")
public class BoardController {

    public final BoardService boardService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BoardDetailResponse createBoard(@Valid @RequestPart BoardRequest boardRequest,
                                           @RequestPart MultipartFile image, HttpSession session) throws IOException {

        Long userId = (Long) session.getAttribute("userId");

        return boardService.createBoard(boardRequest, image, userId);
    }

    @GetMapping("/all")
    public Page<BoardResponse> findAllBoard(Pageable pageable) {
        return boardService.findAllBoard(pageable);
    }

    @GetMapping("/{id}")
    public BoardDetailResponse findBoardById(@PathVariable Long id){
        return boardService.findBoardByIdToDto(id);
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
    public BoardResponse updateBoard(@PathVariable Long id, @Valid @RequestPart BoardRequest boardRequest,
                                     @RequestPart MultipartFile image, HttpSession session) throws IOException {
        Long userId = (Long) session.getAttribute("userId");

        return boardService.updateBoard(id, boardRequest, image, userId);
    }

    @DeleteMapping("/{id}")
    public BoardResponse deleteBoardById(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        return boardService.deleteBoard(id, userId);
    }

}
