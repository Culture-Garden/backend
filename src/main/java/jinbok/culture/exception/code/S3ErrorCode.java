package jinbok.culture.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum S3ErrorCode implements ErrorCode {

    INVALID_IMAGE(HttpStatus.BAD_REQUEST,"이미지가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
