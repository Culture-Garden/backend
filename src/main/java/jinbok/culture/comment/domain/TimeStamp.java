package jinbok.culture.comment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeStamp {

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        String customCreatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.createdAt = LocalDateTime.parse(customCreatedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
