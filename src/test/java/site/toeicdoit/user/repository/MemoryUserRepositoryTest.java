package site.toeicdoit.user.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import site.toeicdoit.user.domain.UserModel;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryUserRepositoryTest {

    MemoryUserRepository userService;

    @AfterEach
    public void afterEach() {
        userService.clearStore();
    }

    @Test
    public void save(){

        UserModel user = new UserModel();
        user.setUsername("spring");

        userService.save(user);
        UserModel result = userService.findById(user.getId()).get();

        assertThat(result).isEqualTo(user);
    }

    @Test
    public void findByUsername(){
        UserModel user1 = new UserModel();
        user1.setUsername("spring1");
        userService.save(user1);

        UserModel user2 = new UserModel();
        user2.setUsername("spring2");
        userService.save(user2);

        UserModel result = userService.findByUsername("spring1").get();
        assertThat(result).isEqualTo(user1);

    }


    @Test
    public void findAll(){
        UserModel user1 = new UserModel();
        user1.setUsername("spring1");
        userService.save(user1);

        UserModel user2 = new UserModel();
        user2.setUsername("spring2");
        userService.save(user2);

        List<UserModel> result = userService.findAll();

        assertThat(result.size()).isEqualTo(2);


    }
}
