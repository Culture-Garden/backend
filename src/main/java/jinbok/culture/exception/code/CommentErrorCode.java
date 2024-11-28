package jinbok.culture.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {

    INVALID_COMMENT(HttpStatus.BAD_REQUEST, "요청하는 댓글이 존재하지 않습니다."),
    INVALID_BOARD(HttpStatus.BAD_REQUEST, "댓글이 옳지 않은 게시글에 존재합니다."),
    INVALID_USER(HttpStatus.BAD_REQUEST, "댓글에 옳지 않은 유저가 접근하였습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
