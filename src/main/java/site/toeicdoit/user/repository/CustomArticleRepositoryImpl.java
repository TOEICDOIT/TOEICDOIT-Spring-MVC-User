package site.toeicdoit.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import site.toeicdoit.user.domain.model.ArticleModel;
import site.toeicdoit.user.domain.QArticleModel;


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
