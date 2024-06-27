package site.toeicdoit.user.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import site.toeicdoit.user.domain.BaseFluxModel;
import site.toeicdoit.user.domain.Role;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
@Document
public class UserFluxModel extends BaseFluxModel implements UserDetails {

    @Id
    private String id;
    private List<Role> roles;

    @Indexed(unique = true)
    private String email;
    private String profile;
    private String name;
    private String nickName;

    // 임시 컬럼
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(i -> "ROLE_" + i.name()).map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getUsername() {
        return email;
    }
}
