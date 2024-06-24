package site.toeicdoit.user.repository;

import org.springframework.stereotype.Repository;
import site.toeicdoit.user.domain.UserModel;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository {


    private static Map<Long, UserModel> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public UserModel save(UserModel user) {
        user.setId(++sequence);
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return store.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny();
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<UserModel> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
