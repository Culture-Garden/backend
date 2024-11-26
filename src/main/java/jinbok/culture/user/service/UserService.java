package jinbok.culture.user.service;

import jinbok.culture.exception.RestApiException;
import jinbok.culture.exception.code.UserErrorCode;
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

    public UserResponse getUserToDto(Long userId) {

        return UserResponse.toUserResponse(getUser(userId));
    }

    public User getUser(Long userId){

        return userRepository.findById(userId).orElseThrow(() -> new RestApiException(UserErrorCode.INVALID_USER));
    }

    public void updateUser(UserRequest userRequest, Long userId) {

        User user = getUser(userId);

        user.updateUser(userRequest);

        userRepository.save(user);
    }

    public void deleteUser(Long userId) {

        userRepository.delete(userRepository.findById(userId)
                .orElseThrow(() -> new RestApiException(UserErrorCode.INVALID_USER)));

    }


}
