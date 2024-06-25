package site.toeicdoit.user.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = {"id"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //댓글 아이디

    @Column(name = "content")
    private String content; //댓글 내용

    @Column(name = "media_name")
    private String mediaName; //미디어 파일 이름

    @Column(name = "media_url")
    private String mediaUrl; //미디어 URL

    @Column(name = "likes")
    private int likes = 0; //댓글 좋아요 수

    @Column(name = "time")
    private String time; //댓글 작성 시간

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parentComment; //부모 댓글

    @OneToMany(mappedBy = "parentComment", orphanRemoval = true)
    private List<Comment> childrenComment = new ArrayList<>(); //자식 댓글들(대댓글)

    @PrePersist
    protected void createTime() {
        time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }
}
