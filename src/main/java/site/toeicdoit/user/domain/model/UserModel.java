package site.toeicdoit.user.domain.model;

import jakarta.persistence.*;
import lombok.*;
import site.toeicdoit.user.domain.BaseEntity;

import java.util.List;

@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = {"id"})
public class UserModel extends BaseEntity {

    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String profile;
    private String name;
    private String nickName;
    private String phone;

//    private List<Role> role;


    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardModel> boardModels;
}
