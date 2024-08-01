package site.toeicdoit.user.service;

import site.toeicdoit.user.domain.dto.ReplyDto;
import site.toeicdoit.user.domain.model.mysql.ReplyModel;

public interface ReplyService {

    default ReplyModel dtoToEntity(ReplyDto dto) {
        return null;
    }

    default ReplyDto entityToDto(ReplyModel entity) {

        return null;
    }

}
