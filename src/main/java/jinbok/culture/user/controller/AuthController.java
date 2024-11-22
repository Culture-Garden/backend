package jinbok.culture.user.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jinbok.culture.user.domain.User;
import jinbok.culture.user.dto.UserRequest;
import jinbok.culture.user.dto.UserResponse;
import jinbok.culture.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true") // 특정 출처 허용 및 자격 증명 허용
public class AuthController {

    public final AuthService authService;

    @PostMapping("/signUp")
    public UserResponse singUp(@Valid @RequestBody UserRequest userRequest) {
        return authService.signUp(userRequest);
    }

    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody UserRequest userRequest, HttpSession session) {

        User user = authService.login(userRequest);

        session.setAttribute("user", user);

        return UserResponse.toUserResponse(user);
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
