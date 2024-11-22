package jinbok.culture.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    INCORRECT_USER(HttpStatus.FORBIDDEN, "권한이 있는 유저만 접근할 수 있습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
