package site.toeicdoit.user.service;

import site.toeicdoit.user.domain.dto.OAuth2UserDTO;
import site.toeicdoit.user.domain.dto.UserDto;
import site.toeicdoit.user.domain.model.PrincipalUserDetails;
import site.toeicdoit.user.domain.model.mysql.UserModel;
import site.toeicdoit.user.domain.vo.Messenger;
import site.toeicdoit.user.domain.vo.Role;

import java.util.Map;
import java.util.Optional;

public interface UserService extends CommandService<UserDto>, QueryService<UserDto> {

    default UserModel dtoToEntity(UserDto dto){
        return UserModel.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .profile(dto.getProfile())
                .name(dto.getName())
                .phone(dto.getPhone())
                .toeicLevel(dto.getToeicLevel())
                .registration(dto.getRegistration())
                .oauthId(dto.getOauthId())
                .build();
    }

    default UserDto entityToDto(UserModel userModel){
        return UserDto.builder()
                .id(userModel.getId())
                .email(userModel.getEmail())
                .password(userModel.getPassword())
                .profile(userModel.getProfile())
                .name(userModel.getName())
                .phone(userModel.getPhone())
                .toeicLevel(userModel.getToeicLevel())
                .registration(userModel.getRegistration())
                .role(userModel.getRoleIds().stream().map(i -> Role.getRole(i.getRole())).toList())
                .calendarId(userModel.getCalendarId().getId())
                .oauthId(userModel.getOauthId())
                .createdAt(userModel.getCreatedAt())
                .updatedAt(userModel.getUpdatedAt())
                .build();
    }

    Map<?, ?> oauthJoin(OAuth2UserDTO dto);
    Map<?, ?> login(UserDto dto);
    Optional<UserDto> findByEmail(String email);
}
