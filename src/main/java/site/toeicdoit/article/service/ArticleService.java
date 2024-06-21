package site.toeicdoit.article.service;

import org.springframework.stereotype.Service;
import site.toeicdoit.article.domain.ArticleDto;
import site.toeicdoit.article.domain.ArticleModel;
import site.toeicdoit.common.service.CommandService;
import site.toeicdoit.common.service.QueryService;
import site.toeicdoit.user.domain.UserModel;


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
