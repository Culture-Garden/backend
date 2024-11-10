package jinbok.culture.user.service;

import jinbok.culture.user.domain.User;
import jinbok.culture.user.dto.UserRequest;
import jinbok.culture.user.dto.UserResponse;
import jinbok.culture.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;

    public UserResponse getUser(User user) {

        return UserResponse.toUserResponse(userRepository.findById(user.getId()).orElseThrow());
    }

    public User updateUser(UserRequest userRequest, User user) {

        user.updateUserInfo(userRequest);

        userRepository.save(user);

        return user;
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }


}
