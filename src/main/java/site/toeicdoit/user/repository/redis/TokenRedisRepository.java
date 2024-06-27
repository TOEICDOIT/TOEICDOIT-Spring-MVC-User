package site.toeicdoit.user.repository.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import site.toeicdoit.user.domain.model.redis.TokenModel;

@Repository
public interface TokenRedisRepository extends CrudRepository<TokenModel, String> {
}
