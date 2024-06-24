package site.toeicdoit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.toeicdoit.user.domain.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    UserModel save(UserModel user);
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findById(Long id);
    List<UserModel> findAll();
}
