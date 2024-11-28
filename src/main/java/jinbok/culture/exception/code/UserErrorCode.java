package jinbok.culture.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    INVALID_CREDENTIALS(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 잘못되었습니다."),
    DUPLICATE_PARAMETER(HttpStatus.BAD_REQUEST, "중복된 아이디가 존재합니다."),
    INVALID_USER(HttpStatus.BAD_REQUEST, "요청된 유저가 존재하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
