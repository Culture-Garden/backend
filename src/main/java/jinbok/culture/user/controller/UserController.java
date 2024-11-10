package jinbok.culture.user.controller;

import jakarta.servlet.http.HttpSession;
import jinbok.culture.user.domain.User;
import jinbok.culture.user.dto.UserRequest;
import jinbok.culture.user.dto.UserResponse;
import jinbok.culture.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myProfile")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true") // 특정 출처 허용 및 자격 증명 허용
public class UserController {

    public final UserService userService;

    @GetMapping
    public UserResponse getUser(HttpSession session) {

        User userInfo = (User) session.getAttribute("user");

        return userService.getUser(userInfo);
    }

    @PutMapping
    public UserResponse updateUser(@RequestBody UserRequest userRequest, HttpSession session) {

        User userInfo = (User) session.getAttribute("user");

        User updatedUser = userService.updateUser(userRequest, userInfo);

        session.setAttribute("user", updatedUser);

        return UserResponse.toUserResponse(updatedUser);
    }

    @DeleteMapping
    public void deleteUser(HttpSession session) {
        User userInfo = (User) session.getAttribute("user");

        userService.deleteUser(userInfo);

        session.removeAttribute("user");
    }
}
