package site.toeicdoit.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.toeicdoit.user.domain.dto.UserDto;
import site.toeicdoit.user.domain.vo.MessageStatus;
import site.toeicdoit.user.domain.vo.Messenger;
import site.toeicdoit.user.domain.vo.PageRequestVo;
import site.toeicdoit.user.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping(path = "/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

    private final UserService service;

    @SuppressWarnings("static-access")
    @GetMapping("/find-all")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("/find-by-email")
    public ResponseEntity<Optional<UserDto>> findByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Optional<UserDto>> findById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/modify")
    public ResponseEntity<Messenger> modify(@RequestBody UserDto dto) {
        return ResponseEntity.ok(service.modify(dto));
    }

    @PutMapping("/modify-by-password")
    public ResponseEntity<Messenger> modifyByPassword(@RequestParam("email") String email,
                                                      @RequestParam("oldPassword") String oldPassword,
                                                      @RequestParam("newPassword") String newPassword) {
        return ResponseEntity.ok(service.modifyByPassword(email, oldPassword, newPassword));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> deleteById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping("/exist-by-id")
    public ResponseEntity<Boolean> existById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.existById(id));
    }

    @GetMapping("/count-all")
    public ResponseEntity<Messenger> countAll() {
        return ResponseEntity.ok(service.countAll());
    }

    @PutMapping("/modify-by-name-phone")
    public ResponseEntity<Messenger> modifyByNameAndPhone(@RequestBody UserDto dto) {
        return ResponseEntity.ok(service.modifyByNameAndPhone(dto));
    }

    @PostMapping("/find-by-name-profile")
    public ResponseEntity<Map<Long, List<String>>> findByNameAndProfile(@RequestBody Map<String, List<Long>> ids){
        return ResponseEntity.ok(service.findByNameAndProfile(ids));
    }

    @PutMapping("/modify-by/{id}/{keyword}")
    public ResponseEntity<Messenger> modifyByKeyword(@PathVariable("id") Long id,
                                                     @PathVariable("keyword") String keyword,
                                                     @RequestParam("info") String info) {
        return ResponseEntity.ok(Messenger.builder()
                .message("user modifyByKeyword : "+ MessageStatus.SUCCESS.name())
                .state(Boolean.TRUE)
                .data(service.modifyByKeyword(id, keyword, info))
                .build());
    }

}
