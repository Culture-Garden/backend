package jinbok.culture.user.controller;

import jinbok.culture.user.dto.UserRequest;
import jinbok.culture.user.dto.UserResponse;
import jinbok.culture.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true") // 특정 출처 허용 및 자격 증명 허용
public class UserController {

    public final UserService userService;

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findByLoginId(id);
    }
}
