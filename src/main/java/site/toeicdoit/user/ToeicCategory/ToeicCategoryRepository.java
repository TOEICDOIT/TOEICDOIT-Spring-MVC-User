package site.toeicdoit.user.ToeicCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.toeicdoit.user.domain.model.ToeicCategoryModel;

@Repository
public interface ToeicCategoryRepository extends JpaRepository<ToeicCategoryModel, Long> {
}
