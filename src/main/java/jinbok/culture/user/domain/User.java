package jinbok.culture.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jinbok.culture.board.domain.Board;
import jinbok.culture.comment.domain.Comment;
import jinbok.culture.user.dto.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank(message = "ID는 반드시 작성하여야 합니다.")
    private String loginId;

    @Column
    @NotBlank(message = "이름은 반드시 작성하여야 합니다.")
    private String username;

    @Column
    @NotBlank(message = "비밀번호는 반드시 작성하여야 합니다.")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> board = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public void updateUser(UserRequest userRequest) {
        this.loginId = userRequest.loginId();
        this.username = userRequest.username();
        this.password = userRequest.password();
    }
}
