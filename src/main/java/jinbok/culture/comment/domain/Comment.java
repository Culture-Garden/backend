package jinbok.culture.comment.domain;

import jakarta.persistence.*;
import jinbok.culture.board.domain.Board;
import jinbok.culture.board.domain.TimeStamp;
import jinbok.culture.comment.dto.CommentRequest;
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
public class Comment extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String content;

    @Column
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void updateComment(CommentRequest commentRequest) {
        this.content = commentRequest.content();
    }
}
