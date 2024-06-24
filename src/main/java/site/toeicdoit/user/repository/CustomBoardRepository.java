package site.toeicdoit.user.repository;

import site.toeicdoit.user.domain.model.BoardModel;

public interface CustomBoardRepository {

    BoardModel findByTitle(String title);

}
