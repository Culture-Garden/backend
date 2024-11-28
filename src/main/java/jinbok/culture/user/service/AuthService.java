package jinbok.culture.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jinbok.culture.board.dto.BoardRequest;
import jinbok.culture.exception.RestApiException;
import jinbok.culture.exception.code.CommonErrorCode;
import jinbok.culture.exception.code.UserErrorCode;
import jinbok.culture.user.domain.User;
import jinbok.culture.user.dto.UserRequest;
import jinbok.culture.user.dto.UserResponse;
import jinbok.culture.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpRequest;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    public final UserRepository userRepository;

    public UserResponse signUp(UserRequest userRequest) {

        Optional<User> existingUser = userRepository.findByLoginId(userRequest.loginId());

        if (existingUser.isPresent()) {
            throw new RestApiException(UserErrorCode.DUPLICATE_PARAMETER);
        }

        User user = User.builder()
                .loginId(userRequest.loginId())
                .username(userRequest.username())
                .password(userRequest.password())
                .build();

        userRepository.save(user);

        return UserResponse.toUserResponse(user);
    }

    public UserResponse login(UserRequest userRequest) {

        User user = userRepository.findByLoginId(userRequest.loginId())
                .filter(m -> m.getPassword().equals(userRequest.password()))
                .orElseThrow(() -> new RestApiException(UserErrorCode.INVALID_CREDENTIALS));

        return UserResponse.toUserResponse(user);
    }
}
