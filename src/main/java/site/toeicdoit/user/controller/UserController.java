package site.toeicdoit.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import site.toeicdoit.user.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;


}
