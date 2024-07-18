package site.toeicdoit.user.domain.dto;


import lombok.*;
import site.toeicdoit.user.domain.model.mysql.RoleModel;
import site.toeicdoit.user.domain.vo.Registration;
import site.toeicdoit.user.domain.vo.Role;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String profile;
    private String name;
    private String phone;
    private Integer toeicLevel;
    private String registration;
    private List<Role> role;
    private String oauthId;


    private Long calendarId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
