package jinbok.culture.user.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jinbok.culture.user.dto.UserRequest;
import jinbok.culture.user.dto.UserResponse;
import jinbok.culture.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:80", "http://localhost:8080"}, allowCredentials = "true")
public class AuthController {

    public final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserResponse> singUp(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(authService.signUp(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody UserRequest userRequest, HttpSession session) {

        UserResponse userResponse = authService.login(userRequest);

        session.setAttribute("userId", userResponse.id());

        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

}
