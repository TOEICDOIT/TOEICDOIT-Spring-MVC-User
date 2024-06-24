package site.toeicdoit.user.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import site.toeicdoit.user.domain.UserModel;
import site.toeicdoit.user.repository.MemoryUserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserServiceImpl service;
    MemoryUserRepository repository;

    @BeforeEach
    public void berforeEach(){
        repository = new MemoryUserRepository();
        service = new UserServiceImpl(repository);
    }

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        UserModel user = new UserModel();
        user.setUsername("hello");

        //when
        Long userId = service.join(user);
        assertThat(userId).isEqualTo(user.getId());


        //then
//        UserModel one = service.findOne(userId).get();
//        Assertions.assertThat(one.getUsername()).isEqualTo(user.getUsername());

    }

    @Test
    void findUsernames() {
    }

    @Test
    void findOne() {
    }
}