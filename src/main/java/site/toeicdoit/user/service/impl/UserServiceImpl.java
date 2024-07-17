package site.toeicdoit.user.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.toeicdoit.user.domain.dto.OAuth2UserDTO;
import site.toeicdoit.user.domain.dto.UserDto;
import site.toeicdoit.user.domain.model.PrincipalUserDetails;
import site.toeicdoit.user.domain.model.mysql.*;
import site.toeicdoit.user.domain.vo.MessageStatus;
import site.toeicdoit.user.domain.vo.Messenger;
import site.toeicdoit.user.domain.vo.Registration;
import site.toeicdoit.user.domain.vo.Role;
import site.toeicdoit.user.repository.mysql.CalendarRepository;
import site.toeicdoit.user.repository.mysql.RoleRepository;
import site.toeicdoit.user.service.UserService;
import site.toeicdoit.user.repository.mysql.UserRepository;

import java.util.List;
import java.util.Optional;

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
        // 아이디 있는지 없는지 찾는 로직 추가 필요 >> exists email 기능 구현됨
        log.info(">>> user save Impl 진입: {} ", dto);
        dto.setRegistration(Registration.LOCAL.name());
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        var joinUser = userRepository.save(dtoToEntity(dto));
        log.info(">>> user save 결과 : {}", joinUser);
        var joinUserRole= roleRepository.save(RoleModel.builder().role(0).userId(joinUser).build());
        log.info(">>> ROLE save 결과 : {}", joinUserRole);
        var joinUserCalendar = calendarRepository.save(CalendarModel.builder().userId(joinUser).build());
        log.info(">>> 캘린더 save 결과 : {}", joinUserCalendar);

        return Messenger.builder()
                .message(MessageStatus.SUCCESS.name())
                .data(Role.getRole(joinUserRole.getRole()))
                .build();
    }

    @Override
    public PrincipalUserDetails oauthJoin(OAuth2UserDTO dto) {
        log.info(">>> oauthJoin 진입: {}", dto);
        UserModel oauthUser = UserModel.builder()
                .email(dto.email())
                .name(dto.name())
                .oauthId(dto.id())
                .profile(dto.profile())
                .registration(Registration.GOOGLE.name())
                .build();
        if (userRepository.existsByEmail(oauthUser.getEmail())){
            Optional<UserModel> existOauthUser = userRepository.findByEmail(dto.email()).stream()
                    .map(i -> userRepository.save(oauthUser)).findFirst();
            return new PrincipalUserDetails(existOauthUser.get());
        } else {
            UserModel oauthSaveUser = userRepository.save(oauthUser);
            roleRepository.save(RoleModel.builder().role(0).userId(oauthUser).build());
            calendarRepository.save(CalendarModel.builder().userId(oauthUser).build());
            return new PrincipalUserDetails(oauthSaveUser);
        }
    }


    @Transactional
    @Override
    public PrincipalUserDetails login(UserDto dto) {
        log.info(">>> localLogin Impl 진입: {} ", dto);
        var loginUser = userRepository.findByEmail(dto.getEmail()).get();
//        log.info(">>> loginUser 결과 : {}", loginUser);

        return passwordEncoder.matches(dto.getPassword(), loginUser.getPassword()) ?
                new PrincipalUserDetails(loginUser): new PrincipalUserDetails(null);

    }

    @Override
    public Messenger existsByEmail(String email) {
        log.info(">>> existsByEmail Impl 진입: {}", email);
        return userRepository.existsByEmail(email) ?
                Messenger.builder().message(MessageStatus.SUCCESS.name()).build() :
                Messenger.builder().message(MessageStatus.FAILURE.name()).build();
    }


    @Transactional
    @Override
    public Messenger deleteById(Long id) {
        log.info(">>> user deleteById Impl 진입: {} ", id);

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
        log.info("user findById 결과 : {}", userRepository.findById(id).map(this::entityToDto));
        // 결과 보고 없을 경우도 코딩 필요
        return userRepository.findById(id).map(this::entityToDto);
    }

    @Override
    public Messenger count() {
        return Messenger.builder()
                .message(userRepository.count() + "")
                .build();
    }

    @Override
    public Boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Transactional
    @Override
    public Messenger modify(UserDto dto) {
        log.info(">>> user modify Impl 진입: {}", dto);
        var result = queryFactory.update(qUser)
                .set(qUser.email, dto.getEmail())
                .set(qUser.password, passwordEncoder.encode(dto.getPassword()))
                .set(qUser.profile, dto.getProfile())
                .set(qUser.phone, dto.getPhone())
                .where(qUser.id.eq(dto.getId()))
                .execute();
        log.info(">>> user modify 결과 : {}", result);

        return Messenger.builder()
                .message(MessageStatus.SUCCESS.name())
                .build();
    }

}
