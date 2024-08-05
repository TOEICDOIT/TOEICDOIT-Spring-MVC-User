package site.toeicdoit.user.service;

import site.toeicdoit.user.domain.dto.*;
import site.toeicdoit.user.domain.model.mysql.CalendarModel;
import site.toeicdoit.user.domain.model.mysql.ReplyModel;
import site.toeicdoit.user.domain.model.mysql.UserModel;
import site.toeicdoit.user.domain.vo.Messenger;
import site.toeicdoit.user.domain.vo.Role;

import java.util.Optional;

public interface UserService extends CommandService<UserDto>, QueryService<UserDto> {

    default UserModel dtoToEntity(UserDto dto){
        return UserModel.builder()
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
                .profile(userModel.getProfile())
                .name(userModel.getName())
                .phone(userModel.getPhone())
                .toeicLevel(userModel.getToeicLevel())
                .registration(userModel.getRegistration())
                .roles(userModel.getRoleIds().stream().map(i -> Role.getRole(i.getRole())).toList())
                .calendarId(userModel.getCalendarIds().stream().map(this::calendarToDto).toList())
                .oauthId(userModel.getOauthId())
                .createdAt(userModel.getCreatedAt())
                .updatedAt(userModel.getUpdatedAt())
                .build();
    }

    default CalendarDto calendarToDto(CalendarModel model) {
        return CalendarDto.builder()
                .id(model.getId())
                .userId(model.getUserId().getId())
                .title(model.getTitle())
                .isAllDay(model.isAllDay())
                .startTime(model.getStartTime())
                .endTime(model.getEndTime())
                .build();

    }

    LoginResultDto oauthJoinOrLogin(OAuth2UserDto dto, String registration);
    LoginResultDto login(UserDto dto);
    Optional<UserDto> findByEmail(String email);

    Messenger modifyByPassword(Long id, String oldPassword, String newPassword);
    Messenger modifyByNameAndPhone(UserDto dto);
}
