package site.toeicdoit.user.domain.dto;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;
import site.toeicdoit.user.domain.model.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class CommentDto {
    private Long id; //댓글 아이디
    private String content; //댓글 내용
    private String mediaName; //미디어 파일 이름
    private String mediaUrl; //미디어 URL
    private int likes = 0; //댓글 좋아요 수
    private String time; //댓글 작성 시간
    private Comment parentComment; //부모 댓글
    private List<Comment> childrenComment = new ArrayList<>(); //자식 댓글들(대댓글)
    protected String createTime;
}
