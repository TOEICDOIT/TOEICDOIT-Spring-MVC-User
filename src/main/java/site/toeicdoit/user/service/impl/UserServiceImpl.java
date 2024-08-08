package site.toeicdoit.user.service.impl;

import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.toeicdoit.user.domain.dto.LoginResultDto;
import site.toeicdoit.user.domain.dto.OAuth2UserDto;
import site.toeicdoit.user.domain.dto.UserDto;
import site.toeicdoit.user.domain.model.QUserModel;
import site.toeicdoit.user.domain.model.RoleModel;
import site.toeicdoit.user.domain.model.UserModel;
import site.toeicdoit.user.domain.vo.Registration;
import site.toeicdoit.user.domain.vo.Role;
import site.toeicdoit.user.exception.ExceptionStatus;
import site.toeicdoit.user.exception.UserException;
import site.toeicdoit.user.repository.RoleRepository;
import site.toeicdoit.user.service.UserService;
import site.toeicdoit.user.repository.UserRepository;

import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JPAQueryFactory queryFactory;
    private final QUserModel qUser = QUserModel.userModel;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public UserDto save(UserDto dto) {
        if (dto == null) {
            throw new UserException(ExceptionStatus.INVALID_INPUT, "param is null");
        } else {
            dto.setRegistration(Registration.LOCAL.name());
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
            UserModel saveUser = Stream.of(userRepository.save(dtoToEntity(dto)))
                    .map(i -> roleRepository.save(RoleModel.builder().role(0).userId(i).build()))
                    .map(RoleModel::getUserId)
                    .findFirst()
                    .orElseThrow(() -> new UserException(ExceptionStatus.BAD_REQUEST, "Bad Request"));
            return UserDto.builder()
                    .id(saveUser.getId())
                    .name(saveUser.getName())
                    .email(saveUser.getEmail())
                    .phone(saveUser.getPhone())
                    .roles(List.of(Role.getRole(0)))
                    .registration(saveUser.getRegistration())
                    .createdAt(saveUser.getCreatedAt())
                    .updatedAt(saveUser.getUpdatedAt())
                    .build();

        }
    }

    @Override
    @Transactional
    public LoginResultDto oauthJoinOrLogin(OAuth2UserDto dto, String registration) {
        if (dto == null || registration.isEmpty()) {
            throw new UserException(ExceptionStatus.INVALID_INPUT, "param is null");
        }
        UserModel user = userRepository.findByEmail(dto.email())
                .stream()
                .peek(i -> i.setName(dto.name()))
                .peek(i -> i.setProfile(dto.profile()))
                .findAny()
                .orElseGet(() -> UserModel.builder()
                        .email(dto.email())
                        .name(dto.name())
                        .profile(dto.profile())
                        .oauthId(dto.id())
                        .registration(registration)
                        .build());
        if (existByEmail(dto.email())) {
            if (user.getRegistration().equals(Registration.GOOGLE.name())) {
                var existOauthUpdate = userRepository.save(user);
                return LoginResultDto.builder()
                        .user(UserDto.builder()
                                .id(existOauthUpdate.getId())
                                .email(existOauthUpdate.getEmail())
                                .roles(existOauthUpdate.getRoleIds().stream().map(i -> Role.getRole(i.getRole())).toList())
                                .registration(existOauthUpdate.getRegistration())
                                .build())
                        .build();
            } else {
                throw new UserException(ExceptionStatus.UNAUTHORIZED, "local에 이미 가입된 정보가 있습니다.");
            }
        } else {
            RoleModel saveOauth = Stream.of(userRepository.save(user))
                    .map(i -> roleRepository.save(RoleModel.builder().role(0).userId(i).build()))
                    .findFirst().orElseThrow(() -> new UserException(ExceptionStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"));
            return LoginResultDto.builder()
                    .user(UserDto.builder()
                            .id(saveOauth.getUserId().getId())
                            .email(saveOauth.getUserId().getEmail())
                            .roles(List.of(Role.getRole(saveOauth.getRole())))
                            .registration(saveOauth.getUserId().getRegistration())
                            .build())
                    .build();
        }
    }

    @Override
    @Transactional
    public LoginResultDto login(UserDto dto) {
        if (dto == null) {
            throw new UserException(ExceptionStatus.INVALID_INPUT, "param is null");
        }
        if (dto.getEmail().equals("admin@test.com")) {
            UserModel adminAccount = userRepository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> new UserException(ExceptionStatus.NOT_FOUND, "Email not found"));
            return adminAccount.getPassword().equals(dto.getPassword()) ?
                    LoginResultDto.builder()
                            .user(UserDto.builder()
                                    .id(adminAccount.getId())
                                    .email(adminAccount.getEmail())
                                    .roles(adminAccount.getRoleIds()
                                            .stream().map(i -> Role.getRole(i.getRole())).toList())
                                    .registration("LOCAL")
                                    .build())
                            .build() : null;
        }
        UserModel existEmail = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserException(ExceptionStatus.NOT_FOUND, "Email not found"));
        if (passwordEncoder.matches(dto.getPassword(), existEmail.getPassword())) {
            return LoginResultDto.builder()
                    .user(UserDto.builder()
                            .id(existEmail.getId())
                            .email(existEmail.getEmail())
                            .roles(existEmail.getRoleIds().stream().map(i -> Role.getRole(i.getRole())).toList())
                            .registration(existEmail.getRegistration())
                            .build())
                    .build();
        } else{
            throw new UserException(ExceptionStatus.UNAUTHORIZED, "wrong password");
        }
    }

    @Override
    public Boolean existByEmail(String email) {
        if (email.isEmpty()) {
            throw new UserException(ExceptionStatus.INVALID_INPUT, "param is null");
        }
        return userRepository.existsByEmail(email);
    }

    @Override
    public void deleteById(Long id) {
        if (existById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserException(ExceptionStatus.NOT_FOUND, "User not found");
        }
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public UserDto findById(Long id) {
        if (id == null) {
            throw new UserException(ExceptionStatus.INVALID_INPUT, "param is null");
        }
        return userRepository.findById(id)
                .map(this::entityToDto)
                .orElseThrow(() -> new UserException(ExceptionStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public UserDto findByEmail(String email) {
        if (email.isEmpty()) {
            throw new UserException(ExceptionStatus.INVALID_INPUT, "param is null");
        }
        return userRepository.findByEmail(email).map(this::entityToDto)
                .orElseThrow(() -> new UserException(ExceptionStatus.NOT_FOUND, "User Email not found"));
    }

    @Override
    public Boolean existById(Long id) {
        if (id == null) {
            throw new UserException(ExceptionStatus.INVALID_INPUT, "param is null");
        }
        return userRepository.existsById(id);
    }

    @Override
    @Transactional
    public Boolean modifyByPassword(String email, String oldPassword, String newPassword) {
        if (existByEmail(email)) {
            UserModel updateUser = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UserException(ExceptionStatus.NOT_FOUND, "User not found"));
            if (passwordEncoder.matches(oldPassword, updateUser.getPassword())) {
                queryFactory.update(qUser)
                        .set(qUser.password, passwordEncoder.encode(newPassword))
                        .where(qUser.id.eq(updateUser.getId()))
                        .execute();
                return Boolean.TRUE;
            } else {
                throw new UserException(ExceptionStatus.UNAUTHORIZED, "비밀번호가 다릅니다.");
            }
        } else {
            throw new UserException(ExceptionStatus.NOT_FOUND, "Email이 존재하지 않습니다.");
        }
    }

    @Override
    @Transactional
    public UserDto modifyByNameAndPhone(UserDto dto) {
        if (dto == null) {
            throw new UserException(ExceptionStatus.INVALID_INPUT, "param is null");
        } else if (existById(dto.getId())) {
            queryFactory.update(qUser)
                    .set(qUser.name, dto.getName())
                    .set(qUser.phone, dto.getPhone())
                    .where(qUser.id.eq(dto.getId()))
                    .execute();
            return findById(dto.getId());
        } else {
            throw new UserException(ExceptionStatus.NOT_FOUND, "계정이 존재하지 않습니다.");
        }
    }


    @Override
    public Map<Long, List<String>> findByNameAndProfile(Map<String, List<Long>> ids) {
        Map<Long, List<String>> userMap = new HashMap<>();
        for (List<Long> list : ids.values()) {
            for (Long id : list) {
                UserDto user = findById(id);
                userMap.put(user.getId(), List.of(user.getName(), user.getProfile() == null ? "" : user.getProfile()));
            }
        }
        return userMap;
    }


    @Override
    @Transactional
    public UserDto modifyByKeyword(Long id, String keyword, String info) {

        StringPath updateSet = switch (keyword) {
            case "email" -> qUser.email;
            case "profile" -> qUser.profile;
            case "phone" -> qUser.phone;
            case "name" -> qUser.name;
            case "toeicLevel" -> qUser.toeicLevel;
            default -> throw new UserException(ExceptionStatus.NOT_FOUND, "Keyword not found");
        };
        if (existById(id)) {
            var updateUser = findById(id);
            queryFactory
                    .update(qUser)
                    .set(updateSet, info)
                    .where(qUser.id.eq(updateUser.getId()))
                    .execute();
            return findById(id);
        } else {
            throw new UserException(ExceptionStatus.NOT_FOUND, "Email이 존재하지 않습니다.");
        }
    }
}
