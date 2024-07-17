package site.toeicdoit.user.domain.model;

import lombok.Getter;
import lombok.Setter;
import site.toeicdoit.user.domain.model.mysql.UserModel;

import java.util.Map;

@Getter
@Setter
public class PrincipalUserDetails {
    private UserModel user;
    private Map<String, Object> attributes;

    public PrincipalUserDetails(UserModel user) {
        this.user = user;
    }

    public PrincipalUserDetails(UserModel user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }
}
