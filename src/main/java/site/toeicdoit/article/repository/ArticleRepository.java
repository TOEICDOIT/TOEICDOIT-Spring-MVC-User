package site.toeicdoit.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.toeicdoit.article.domain.ArticleModel;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleModel, Long> , CustomArticleRepository {
}
