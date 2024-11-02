package jinbok.culture.user.domain;

import jakarta.persistence.*;
import jinbok.culture.board.domain.Board;
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
    private String loginId;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> board = new ArrayList<>();

}
