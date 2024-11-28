package jinbok.culture.board.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jinbok.culture.board.dto.BoardRequest;
import jinbok.culture.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board extends TimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void updateBoard(BoardRequest boardRequest){
        this.title = boardRequest.title();
        this.content = boardRequest.content();
    }

}
