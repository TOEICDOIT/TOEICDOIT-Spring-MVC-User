package site.toeicdoit.security.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tokens")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = "id")
public class TokenModel {

    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refreshToken;
    private String expiredDate;

}
