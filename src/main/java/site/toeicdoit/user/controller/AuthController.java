package site.toeicdoit.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import site.toeicdoit.user.domain.dto.UserDto;
import site.toeicdoit.user.domain.vo.Messenger;
import site.toeicdoit.user.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
     private final UserService service;

    @PostMapping("/login")
    public ResponseEntity<Messenger> login(@RequestBody UserDto dto) {
        log.info("login con: " + dto);
        return ResponseEntity.ok(service.login(dto));
    }

    @PostMapping("/join")
    public ResponseEntity<Messenger> join(@RequestBody UserDto dto) {
        log.info(">>> join con: {}", dto);
        return ResponseEntity.ok(service.save(dto));
    }

    @PostMapping("/oauth2/{reg}")
    public ResponseEntity<Messenger> oauthJoin(@RequestBody UserDto dto) {
        log.info(">>> oauthJoin con: {}", dto);
        return ResponseEntity.ok(service.save(dto));
    }


    @GetMapping("/exists-email")
    public ResponseEntity<Messenger> existsByEmail(@RequestParam("email") String email) {
        log.info(">>> existsByEmail con: {}", email);
        return ResponseEntity.ok(service.existsByEmail(email));
    }

}
