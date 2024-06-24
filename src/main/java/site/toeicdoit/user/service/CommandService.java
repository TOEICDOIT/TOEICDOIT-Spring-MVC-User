package site.toeicdoit.user.service;

import site.toeicdoit.user.domain.Messenger;

public interface CommandService<T> {
    Messenger save(T t);
    Messenger deleteById(Long id);
    Messenger modify(T t);
}