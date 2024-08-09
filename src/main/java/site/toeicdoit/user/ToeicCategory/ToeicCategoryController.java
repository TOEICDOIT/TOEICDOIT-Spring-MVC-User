package site.toeicdoit.user.ToeicCategory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/toeic-category")
@Slf4j
@RestController
@RequiredArgsConstructor
public class ToeicCategoryController {

    private final ToeicCategoryService toeicCategoryService;

    @GetMapping("/find-all")
    public ResponseEntity<Page<ToeicCategoryDto>> findAll(@RequestParam(value = "title", required = false)
                                                                String title, Pageable pageable) {
        return ResponseEntity.ok(toeicCategoryService.findAll(title, pageable));
    }

}
