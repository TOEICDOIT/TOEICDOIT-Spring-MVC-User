package site.toeicdoit.user.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.toeicdoit.user.domain.dto.ArticleDto;
import site.toeicdoit.user.domain.model.ArticleModel;
import site.toeicdoit.user.repository.ArticleRepository;
import site.toeicdoit.user.domain.Messenger;
import site.toeicdoit.user.service.ArticleService;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository repository;

    @Transactional
    @Override
    public Messenger save(ArticleDto dto) {
        log.info("ArticleModel save Impl: {}", dto);
        ArticleModel result = repository.save(dtoToEntity(dto));
        System.out.println((result instanceof ArticleModel) ? "SUCCESS" : "FAILURE");
        return Messenger.builder()
                .message((result instanceof ArticleModel) ? "SUCCESS" : "FAILURE")
                .build();
    }

    @Override
    public Messenger deleteById(Long id) {
        log.info("deleteById Impl: " + id);
        // Check if the user exists before attempting to delete
        if (repository.existsById(id)) {
            repository.deleteById(id); // This will now trigger cascading deletes
            return Messenger.builder().message("SUCCESS").build();
        } else {
            return Messenger.builder().message("FAILURE").build();
        }

    }

    @Transactional
    @Override
    public Messenger modify(ArticleDto dto) {
        log.info("ArticleModel modify Impl: {}", dto);
        ArticleModel ent = dtoToEntity(dto);
        Long id = dto.getId();
        String title = dto.getTitle();
        String content = dto.getContent();

        System.out.println((ent instanceof ArticleModel) ? "SUCCESS" : "FAILURE");
        return Messenger.builder()
                .message((ent instanceof ArticleModel) ? "SUCCESS" : "FAILURE")
                .build();
    }

    @Override
    public List<ArticleDto> findAll() {
        return repository.findAll().stream().map(i -> entityToDto(i)).toList();
    }

    @Override
    public Optional<ArticleDto> findById(Long id) {
        log.info("ArticleModel findById Impl: {}", id);
        return repository.findById(id).map(i -> entityToDto(i));
    }


    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
