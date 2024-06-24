package site.toeicdoit.user.repository;

import site.toeicdoit.user.domain.model.ArticleModel;

public interface CustomArticleRepository {

    ArticleModel findByTitle(String title);

}
