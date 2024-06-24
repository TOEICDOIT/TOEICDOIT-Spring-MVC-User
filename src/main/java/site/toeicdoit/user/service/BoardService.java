package site.toeicdoit.user.service;

import org.springframework.stereotype.Service;
import site.toeicdoit.user.domain.dto.BoardDto;
import site.toeicdoit.user.domain.model.BoardModel;



public interface BoardService extends CommandService<BoardDto>, QueryService<BoardDto> {

    default BoardModel dtoToEntity(BoardDto dto) {
        return BoardModel.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    default BoardDto entityToDto(BoardModel entity){
        return BoardDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();
    }


}
