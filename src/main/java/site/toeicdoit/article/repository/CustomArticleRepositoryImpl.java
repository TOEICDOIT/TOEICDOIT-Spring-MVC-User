package site.toeicdoit.article.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.toeicdoit.article.domain.ArticleModel;
import site.toeicdoit.article.domain.QArticleModel;


@RequiredArgsConstructor
public class CustomArticleRepositoryImpl implements CustomArticleRepository {
    private final JPAQueryFactory queryFactory;
    private final QArticleModel article = QArticleModel.articleModel;

    @Override
    public ArticleModel findByTitle(String title) {
        return queryFactory.selectFrom(article)
                .where(article.title.eq(title))
                .fetchFirst();
    }
}
