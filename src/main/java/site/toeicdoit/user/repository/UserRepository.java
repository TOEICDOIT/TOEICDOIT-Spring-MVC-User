package site.toeicdoit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.toeicdoit.user.domain.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
