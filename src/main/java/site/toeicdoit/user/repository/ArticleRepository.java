package site.toeicdoit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.toeicdoit.user.domain.model.ArticleModel;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleModel, Long> , CustomArticleRepository {
}
