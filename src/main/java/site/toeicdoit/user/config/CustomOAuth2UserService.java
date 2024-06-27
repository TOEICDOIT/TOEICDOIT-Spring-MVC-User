package site.toeicdoit.user.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import site.toeicdoit.user.domain.RegistrationId;
import site.toeicdoit.user.domain.Role;
import site.toeicdoit.user.domain.model.RoleModel;
import site.toeicdoit.user.domain.model.UserModel;
import site.toeicdoit.user.repository.RoleRepository;
import site.toeicdoit.user.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@RequiredArgsConstructor
@Service
@Log4j2
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 1. 유저 정보(attributes) 가져오기
        Map<String, Object> oAuth2UserAttributes = super.loadUser(userRequest).getAttributes();
        log.info("1. 유저 정보(attributes) 가져오기 : "+oAuth2UserAttributes);


        log.info("1-1. registrationId : {}", RegistrationId.findByName(userRequest.getClientRegistration().getRegistrationId()));
        // 2. resistrationId 가져오기 (third-party id)
        RegistrationId registrationId = RegistrationId.findByName(userRequest.getClientRegistration().getRegistrationId());
        log.info("2. resistrationId 가져오기 (third-party id) : "+registrationId);


        // 3. userNameAttributeName 가져오기
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        log.info("3. userNameAttributeName 가져오기 : "+userNameAttributeName);

        // 4. 유저 정보 dto 생성
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(registrationId, oAuth2UserAttributes);
        log.info("4. 유저 정보 dto 생성 : "+oAuth2UserInfo);

        // 5. 회원가입 및 로그인
        UserModel user = getOrSave(oAuth2UserInfo);
        log.info("5. 회원가입 및 로그인 : "+ user);

        // 6. OAuth2User로 반환
        return new PrincipalDetails(user, oAuth2UserAttributes, userNameAttributeName);
//         new DefaultOAuth2User();

    }

    private UserModel getOrSave(OAuth2UserInfo oAuth2UserInfo) {
        return userRepository.findByEmail(oAuth2UserInfo.email())
                .orElseGet(() ->
                    Stream.of(userRepository
                            .save(UserModel.builder()
                                    .name(oAuth2UserInfo.name())
                                    .email(oAuth2UserInfo.email())
                                    .profile(oAuth2UserInfo.profile())
                            .build()))
//                            .peek(i -> roleRepository.save(RoleModel.builder()
//                                            .userId(i)
//                                            .role(Role.USER)
//                                    .build()))
                            .map(i -> Stream.of(roleRepository.save(RoleModel.builder()
                                            .userId(i)
                                            .role(Role.USER)
                                    .build()))
                                    .peek(j -> i.setRoleModels(List.of(j)))
                                    .map(j -> i)
                                    .findAny()
                                    .get()
                            )
                            .findAny()
                            .get()
                );
    }
}