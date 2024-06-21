package site.toeicdoit.article.domain;

import jakarta.persistence.*;
import lombok.*;
import site.toeicdoit.user.domain.UserModel;

@Entity(name = "articles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = "id")
public class ArticleModel {

    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String type;
    private String answer;
    private String createDate;
    private String updateDate;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel userId;
}
