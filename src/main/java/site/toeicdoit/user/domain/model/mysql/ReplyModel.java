package site.toeicdoit.user.domain.model.mysql;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = {"id"})
public class  ReplyModel extends BaseModel {

    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ReplyModel parentId;

    @OneToMany(mappedBy = "parentId", orphanRemoval = true)
    private List<ReplyModel> childrenIds = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardModel boardId;

}
