package jinbok.culture.user.controller;

import jakarta.servlet.http.HttpSession;
import jinbok.culture.user.domain.User;
import jinbok.culture.user.dto.UserRequest;
import jinbok.culture.user.dto.UserResponse;
import jinbok.culture.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myProfile")
@CrossOrigin(origins = {"http://localhost:80", "http://localhost:8080"}, allowCredentials = "true")
public class UserController {

    public final UserService userService;

    @GetMapping
    public UserResponse getUser(HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        return userService.getUserToDto(userId);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserRequest userRequest, HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        userService.updateUser(userRequest, userId);

        return ResponseEntity.ok("사용자 정보 수정 완료");
    }

    @DeleteMapping
    public void deleteUser(HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        userService.deleteUser(userId);

        session.removeAttribute("userId");
    }
}
