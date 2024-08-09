package site.toeicdoit.user.ToeicCategory;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import site.toeicdoit.user.domain.model.QBoardModel;
import site.toeicdoit.user.domain.model.QToeicCategoryModel;
import site.toeicdoit.user.domain.model.ToeicCategoryModel;
import site.toeicdoit.user.repository.BoardRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ToeicCategoryServiceImpl implements ToeicCategoryService {
    private final ToeicCategoryRepository toeicCategoryRepository;

    private final JPAQueryFactory queryFactory;
    private final QToeicCategoryModel qToeicCategory = QToeicCategoryModel.toeicCategoryModel;

    @Override
    public Page<ToeicCategoryDto> findAll(String title, Pageable pageable) {
        var toeicCategory = queryFactory.selectFrom(qToeicCategory)
                .where(eqTitle(title))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().map(this::toDto).toList();
        JPAQuery<Long> countQuery = queryFactory.select(qToeicCategory.count())
                .from(qToeicCategory)
                .where(eqTitle(title));
        return PageableExecutionUtils.getPage(toeicCategory, pageable, countQuery::fetchOne);
    }


    private BooleanExpression eqTitle(String title) {
        if (title == null) {
            return null;
        }
        return qToeicCategory.title.contains(title);
    }
}
