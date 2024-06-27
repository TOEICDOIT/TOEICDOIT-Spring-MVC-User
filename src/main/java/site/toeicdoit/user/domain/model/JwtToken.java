package site.toeicdoit.user.domain.model;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;


@Getter
public class JwtToken extends AbstractAuthenticationToken {
    private final String token;
    private final UserDetails principal;

    public JwtToken(String token, UserDetails principal) {
        super(principal.getAuthorities());
        this.token = token;
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public Authentication withAuthentication(Boolean isAuthenticated) {
        JwtToken cloned = new JwtToken(token, principal);
        cloned.setAuthenticated(isAuthenticated);
        return cloned;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof JwtToken test))
            return false;
        if(this.getToken() == null || test.getToken() != null)
            return false;
        return false;
    }

    @Override
    public int hashCode() {
        int code = super.hashCode();
        if(this.getToken() != null)
            code ^= this.getToken().hashCode();
        return code;
    }
}
