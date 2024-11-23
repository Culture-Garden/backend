package jinbok.culture.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {


    INVALID_CREDENTIALS(HttpStatus.FORBIDDEN, "아이디 또는 비밀번호가 잘못되었습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
