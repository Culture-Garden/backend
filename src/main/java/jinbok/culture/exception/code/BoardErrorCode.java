package jinbok.culture.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoardErrorCode implements ErrorCode {

    INVALID_BOARD(HttpStatus.BAD_REQUEST,"요청된 글이 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
