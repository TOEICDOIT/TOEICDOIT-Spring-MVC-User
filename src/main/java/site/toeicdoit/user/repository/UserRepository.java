package site.toeicdoit.user.repository;

import org.springframework.data.repository.CrudRepository;
import site.toeicdoit.user.domain.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserModel, Long>, CustomUserRepository {
    Optional<UserModel> findByEmail(String email);
}
