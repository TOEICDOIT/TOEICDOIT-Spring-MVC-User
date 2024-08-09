package site.toeicdoit.user.ToeicCategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import site.toeicdoit.user.domain.dto.BoardDto;
import site.toeicdoit.user.domain.model.BoardModel;
import site.toeicdoit.user.domain.model.ToeicCategoryModel;
import site.toeicdoit.user.domain.model.UserModel;

public interface ToeicCategoryService {

//    default ToeicCategoryModel toEntity(BoardDto dto) {
//        return BoardModel.builder()
//
//                .build();
//    }

    default ToeicCategoryDto toDto(ToeicCategoryModel entity) {
        return ToeicCategoryDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .take(entity.getTake())
                .build();
    }
    Page<ToeicCategoryDto> findAll(String title, Pageable pageable);
}
