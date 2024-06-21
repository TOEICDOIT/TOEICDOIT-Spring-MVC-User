package site.toeicdoit.article.repository;

import site.toeicdoit.article.domain.ArticleModel;

public interface CustomArticleRepository {

    ArticleModel findByTitle(String title);

}
