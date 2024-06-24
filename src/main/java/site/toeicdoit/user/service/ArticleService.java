package site.toeicdoit.user.service;

import site.toeicdoit.user.domain.dto.ArticleDto;
import site.toeicdoit.user.domain.model.ArticleModel;


public interface ArticleService extends CommandService<ArticleDto>, QueryService<ArticleDto> {

    default ArticleModel dtoToEntity(ArticleDto dto) {
        return ArticleModel.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    default ArticleDto entityToDto(ArticleModel entity){
        return ArticleDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getUserId().getUsername())
                .build();
    }


}
