package jinbok.culture.user.service;

import jinbok.culture.user.domain.User;
import jinbok.culture.user.dto.UserRequest;
import jinbok.culture.user.dto.UserResponse;
import jinbok.culture.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

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

    public UserResponse login(UserRequest userRequest) {

        User receiveUserInfo = User.builder()
                .loginId(userRequest.loginId())
                .username(userRequest.username())
                .password(userRequest.password())
                .build();

        User userLoginId = userRepository.findByLoginId(userRequest.loginId());
        if (receiveUserInfo.getLoginId().equals(userLoginId.getLoginId())
                && receiveUserInfo.getPassword().equals(userLoginId.getPassword())) {
            return UserResponse.toUserResponse(receiveUserInfo);
        }


        return null;
    }

    public UserResponse getUserInfo(Long userId) {

        User user = userRepository.findById(userId).orElse(null);
        assert user != null : "해당 userId를 가진 사람이 없습니다.";
        return UserResponse.toUserResponse(user);
    }
}
