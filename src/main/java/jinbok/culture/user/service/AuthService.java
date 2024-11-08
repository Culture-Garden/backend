package jinbok.culture.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jinbok.culture.user.domain.User;
import jinbok.culture.user.dto.UserRequest;
import jinbok.culture.user.dto.UserResponse;
import jinbok.culture.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    public final UserRepository userRepository;

    public UserResponse signUp(UserRequest userRequest) {

        User user = User.builder()
                .loginId(userRequest.loginId())
                .username(userRequest.username())
                .password(userRequest.password())
                .build();

        userRepository.save(user);

        return UserResponse.toUserResponse(user);
    }

    public User login(UserRequest userRequest) {

        return userRepository.findByLoginId(userRequest.loginId())
                .filter(m -> m.getPassword().equals(userRequest.password()))
                .orElseThrow();
    }

}
