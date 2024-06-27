package site.toeicdoit.user.domain.model;

import jakarta.persistence.*;
import lombok.*;
import site.toeicdoit.user.domain.BaseModel;

import java.time.LocalDateTime;

@Entity(name = "tokens")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = {"id"})
public class TokenModel extends BaseModel {

    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime expired;

}
