package jinbok.culture.user.service;

import jakarta.servlet.http.HttpSession;
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

    public User updateUserInfo(Long Id, UserRequest userRequest) {
        User user = userRepository.findById(Id).orElseThrow();

        user.updateUserInfo(userRequest);

        userRepository.save(user);

        return user;
    }

    public void deleteUserInfo(Long id, HttpSession session) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        session.invalidate();
    }


}
