package site.toeicdoit.user.service;

import site.toeicdoit.user.domain.vo.MessengerVo;

public interface CommandService<T> {
    MessengerVo save(T t);
    MessengerVo deleteById(Long id);
    MessengerVo modify(T t);
}