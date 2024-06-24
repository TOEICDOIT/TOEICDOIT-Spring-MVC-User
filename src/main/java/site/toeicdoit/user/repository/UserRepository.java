package site.toeicdoit.user.repository;

import site.toeicdoit.user.domain.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    UserModel save(UserModel user);
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findById(Long id);
    List<UserModel> findAll();
}
