package jinbok.culture.user.service;

import jinbok.culture.user.domain.User;
import jinbok.culture.user.dto.UserRequest;
import jinbok.culture.user.dto.UserResponse;
import jinbok.culture.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;

    public UserResponse findByLoginId(Long id) {
        return UserResponse.toUserResponse(userRepository.findById(id).orElseThrow());
    }

    public UserResponse updateUserInfo(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow();


    }
}
