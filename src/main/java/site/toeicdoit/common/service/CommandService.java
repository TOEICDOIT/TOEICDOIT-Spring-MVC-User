package site.toeicdoit.common.service;

import site.toeicdoit.common.domain.Messenger;

public interface CommandService<T> {
    Messenger save(T t);
    Messenger deleteById(Long id);
    Messenger modify(T t);
}