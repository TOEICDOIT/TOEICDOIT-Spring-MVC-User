package site.toeicdoit.user.domain.model;


import jakarta.persistence.*;
import lombok.*;
import site.toeicdoit.user.domain.BaseEntity;

@Entity(name = "replys")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = {"id"})
public class ReplyModel extends BaseEntity {

    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

}
