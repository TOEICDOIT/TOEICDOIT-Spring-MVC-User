//package site.toeicdoit.user.repository.dsl;
//
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import site.toeicdoit.user.domain.dto.BoardDto;
//import site.toeicdoit.user.domain.model.jpa.BoardModel;
//import site.toeicdoit.user.domain.model.jpa.QBoardModel;
//import site.toeicdoit.user.domain.vo.MessengerVo;
//import site.toeicdoit.user.repository.jpa.BoardRepository;
//import site.toeicdoit.user.service.BoardService;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class CustomBoardRepositoryImpl implements CustomBoardRepository {
//    private final JPAQueryFactory queryFactory;
//    private final BoardRepository boardRepository;
//    private final BoardService boardService;
//    private final QBoardModel boardModel = QBoardModel.boardModel;
//
//
//
//    @Transactional
//    public MessengerVo modify(BoardDto dto) {
//        log.info(">>> Board Service Modify 진입: {}", dto);
//        BoardModel ent = boardService.dtoToEntity(dto);
//        Long id = dto.getId();
//        String title = dto.getTitle();
//        String content = dto.getContent();
//         Long result = queryFactory.update(boardModel)
//                .set(boardModel.title, title)
//                .set(boardModel.content, content)
//                .where(boardModel.id.eq(id))
//                .execute();
//        log.info(">>> Board modify 결과(Query DSL): {}", result);
//        System.out.println((ent instanceof BoardModel) ? "SUCCESS" : "FAILURE");
//        return MessengerVo.builder()
//                .message((ent instanceof BoardModel) ? "SUCCESS" : "FAILURE")
//                .build();
//    }
//
//    @Override
//    public BoardModel findByTitle(String title) {
//        return null;
//    }
//}
