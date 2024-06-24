package site.toeicdoit.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.toeicdoit.user.domain.dto.ArticleDto;
import site.toeicdoit.user.service.ArticleService;
import site.toeicdoit.user.domain.Messenger;

import java.util.Optional;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService service;

    @PostMapping("/save")
    public ResponseEntity<Messenger> save(@RequestBody ArticleDto dto) {
        log.info("article save con: {}", dto);
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/modify")
    public ResponseEntity<Messenger> modify(@RequestBody ArticleDto dto) {
        log.info("article modify con: {}", dto);
        return ResponseEntity.ok(service.modify(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> deleteById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping("/detail")
    public ResponseEntity<Optional<ArticleDto>> findById(@RequestParam("id") Long id) {
        log.info("ArticleModel findById con: {}", id);
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsById(@RequestParam("id") Long id) {

        return ResponseEntity.ok(service.existsById(id));
    }

}
