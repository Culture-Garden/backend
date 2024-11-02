package jinbok.culture.user.controller;

import jinbok.culture.user.dto.UserRequest;
import jinbok.culture.user.dto.UserResponse;
import jinbok.culture.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080") // 특정 출처 허용
public class UserController {

    public final UserService userService;

    @PostMapping("/signUp")
    public UserResponse singUp(@RequestBody UserRequest userRequest) {
        return userService.signUp(userRequest);

    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest userRequest) {
        return userService.login(userRequest);

    }

    @GetMapping("/{id}")
    public UserResponse getUserInfo(@PathVariable("id") Long userId) {
        return  userService.getUserInfo(userId);
    }
}
