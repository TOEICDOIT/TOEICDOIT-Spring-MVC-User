package site.toeicdoit.user.service.impl;

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
import site.toeicdoit.user.domain.model.mysql.*;
import site.toeicdoit.user.domain.vo.MessageStatus;
import site.toeicdoit.user.domain.vo.Messenger;
import site.toeicdoit.user.domain.vo.Registration;
import site.toeicdoit.user.domain.vo.Role;
import site.toeicdoit.user.handler.AlreadyExistElementException;
import site.toeicdoit.user.repository.mysql.CalendarRepository;
import site.toeicdoit.user.repository.mysql.RoleRepository;
import site.toeicdoit.user.service.UserService;
import site.toeicdoit.user.repository.mysql.UserRepository;

import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JPAQueryFactory queryFactory;
    private final QUserModel qUser = QUserModel.userModel;
    private final QRoleModel qRole = QRoleModel.roleModel;
    private final RoleRepository roleRepository;
    private final CalendarRepository calendarRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Messenger save(UserDto dto) {
        if (dto == null) {
            throw new ArithmeticException("입력된 내용이 없습니다.");
        } else if (!existByEmail(dto.getEmail())) {
            dto.setRegistration(Registration.LOCAL.name());
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));

            var joinUser = userRepository.save(dtoToEntity(dto));
            log.info(">>> user save 결과 : {}", joinUser);
            var joinUserRole = roleRepository.save(RoleModel.builder().role(0).userId(joinUser).build());
            log.info(">>> ROLE save 결과 : {}", joinUserRole);
            var joinUserCalendar = calendarRepository.save(CalendarModel.builder().userId(joinUser).build());
            log.info(">>> 캘린더 save 결과 : {}", joinUserCalendar);

            return Messenger.builder()
                    .message(MessageStatus.SUCCESS.name())
                    .data(entityToDto(joinUser))
                    .build();
        } else {
            return Messenger.builder()
                    .message("회원가입에 실패했습니다.")
                    .build();
        }
    }

    @Override
    public LoginResultDto oauthJoinOrLogin(OAuth2UserDto dto, String registration) {
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
        if (user.getRegistration().equals(Registration.GOOGLE.name())) {
            if (existByEmail(dto.email())) {
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
                var saveUser = userRepository.save(user);
                var roleSave = roleRepository.save(RoleModel.builder().role(0).userId(saveUser).build());
                var calendarSave = calendarRepository.save(CalendarModel.builder().userId(roleSave.getUserId()).build());
                return LoginResultDto.builder()
                        .user(UserDto.builder()
                                .id(saveUser.getId())
                                .email(saveUser.getEmail())
                                .roles(Stream.of(roleSave.getRole()).map(Role::getRole).toList())
                                .registration(saveUser.getRegistration())
                                .build())
                        .build();
            }
        } else {
            throw new AlreadyExistElementException("접근 경로가 잘못되었습니다.");
        }
    }


    @Transactional
    @Override
    public LoginResultDto login(UserDto dto) {
        if (dto.getEmail().equals("admin")) {
            UserModel admin = userRepository.findByEmail(dto.getEmail()).get();
            return admin.getPassword().equals(dto.getPassword()) ?
                    LoginResultDto.builder()
                            .user(UserDto.builder()
                                    .id(admin.getId())
                                    .email(admin.getEmail())
                                    .roles(admin.getRoleIds().stream().map(i -> Role.getRole(i.getRole())).toList())
                                    .build())
                            .build() : null;
        }
        var existEmail = userRepository.findByEmail(dto.getEmail()).get();
        return passwordEncoder.matches(dto.getPassword(), existEmail.getPassword()) ?
                LoginResultDto.builder()
                        .user(UserDto.builder()
                                .id(existEmail.getId())
                                .email(existEmail.getEmail())
                                .roles(existEmail.getRoleIds().stream().map(i -> Role.getRole(i.getRole())).toList())
                                .registration(existEmail.getRegistration())
                                .build())
                        .build() : null;
    }

    @Override
    public Boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    @Transactional
    @Override
    public Messenger deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return Messenger.builder().message(MessageStatus.SUCCESS.name()).build();
        } else {
            return Messenger.builder().message(MessageStatus.FAILURE.name()).build();
        }
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return Optional.of(userRepository.findById(id).map(this::entityToDto))
                .orElseThrow(() -> new AlreadyExistElementException("존재하는 아이디가 없습니다."));
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return Optional.of(userRepository.findByEmail(email).map(this::entityToDto))
                .orElseThrow(() -> new AlreadyExistElementException("존재하는 Email이 없습니다."));
    }

    @Override
    public Messenger countAll() {
        return Messenger.builder()
                .count(userRepository.count())
                .build();
    }

    @Override
    public Boolean existById(Long id) {
        return userRepository.existsById(id);
    }

    @Transactional
    @Override
    public Messenger modify(UserDto dto) {
        if (dto == null) {
            return Messenger.builder()
                    .message("입력된 정보가 없습니다.")
                    .build();
        } else if (existByEmail(dto.getEmail())){
            var updateUser = queryFactory.update(qUser)
                    .set(qUser.name, dto.getName())
                    .set(qUser.email, dto.getEmail())
                    .set(qUser.profile, dto.getProfile())
                    .where(qUser.id.eq(dto.getId()))
                    .execute();
            return Messenger.builder()
                    .message(MessageStatus.SUCCESS.name())
                    .data(findById(updateUser))
                    .build();
        } else {
            return Messenger.builder()
                    .message("Email 정보가 존재하지 않습니다.")
                    .build();
        }
    }

    @Override
    @Transactional
    public Messenger modifyByPassword(Long id, String oldPassword, String newPassword) {
        if (userRepository.existsById(id)) {
            var updateUser = userRepository.findById(id).get();
            if (passwordEncoder.matches(oldPassword, updateUser.getPassword())) {
                queryFactory.update(qUser)
                        .set(qUser.password, passwordEncoder.encode(newPassword))
                        .where(qUser.id.eq(updateUser.getId()))
                        .execute();
                return Messenger.builder().message(MessageStatus.SUCCESS.name()).build();
            } else {
                return Messenger.builder().message("이전 비밀번호와 다릅니다.").build();
            }
        } else {
            return Messenger.builder().message("Email 정보가 존재하지 않습니다.").build();
        }
    }

    @Transactional
    @Override
    public Messenger modifyByNameAndPhone(UserDto dto) {
        if (dto == null) {
            return Messenger.builder()
                    .message("입력된 정보가 없습니다.")
                    .build();
        } else if (existByEmail(dto.getEmail())){
            long user = queryFactory.update(qUser)
                    .set(qUser.name, dto.getName())
                    .set(qUser.phone, dto.getPhone())
                    .where(qUser.id.eq(dto.getId()))
                    .execute();
            return Messenger.builder()
                    .message(MessageStatus.SUCCESS.name())
                    .data(findById(user))
                    .build();
        } else {
            return Messenger.builder()
                    .message("Email 정보가 존재하지 않습니다.")
                    .build();
        }
    }
}
