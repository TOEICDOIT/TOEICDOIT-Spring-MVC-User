package site.toeicdoit.user.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import site.toeicdoit.user.domain.BaseModel;

import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = {"id"})
public class UserModel extends BaseModel implements UserDetails {

    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String profile;
    private String name;
    private String nickName;
    private String phone;
    private Integer toeicLevel;

    @Setter
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<RoleModel> roleModels;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardModel> boardModels;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReplyModel> replyModels;

    @OneToOne(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private CalenderModel calenderId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleModels.stream().map(i -> "ROLE_" + i.getRole()).map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getUsername() {
        return email;
    }
}
