package site.toeicdoit.user.domain.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String password;

}
