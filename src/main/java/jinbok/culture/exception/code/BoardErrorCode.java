package jinbok.culture.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@RequiredArgsConstructor
public enum BoardErrorCode implements ErrorCode {

    INVALID_BOARD(HttpStatus.BAD_REQUEST,"요청된 글이 존재하지 않음");

    private final HttpStatus httpStatus;
    private final String message;
}
