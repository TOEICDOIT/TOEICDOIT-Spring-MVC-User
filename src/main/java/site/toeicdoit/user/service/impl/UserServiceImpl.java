package site.toeicdoit.user.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.toeicdoit.user.service.UserService;
import site.toeicdoit.user.repository.MemoryUserRepository;
import site.toeicdoit.user.domain.model.UserModel;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final MemoryUserRepository userRepository;


    public Long join(UserModel user) {

        validateDuplicateUser(user); // 아이디 중복 확인

        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(UserModel user) {
        userRepository.findById(user.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<UserModel> findUsernames(String username) {
        return userRepository.findAll();
    }

    public Optional<UserModel> findOne(Long userId){
        return userRepository.findById(userId);
    }
}
