package site.toeicdoit.user.service.impl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.toeicdoit.user.domain.dto.BoardDto;
import site.toeicdoit.user.domain.model.mysql.BoardModel;
import site.toeicdoit.user.domain.model.mysql.QBoardModel;
import site.toeicdoit.user.domain.vo.MessageStatus;
import site.toeicdoit.user.domain.vo.Messenger;
import site.toeicdoit.user.repository.mysql.BoardRepository;
import site.toeicdoit.user.service.BoardService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final JPAQueryFactory queryFactory;
    private final BoardRepository boardRepository;
    private final QBoardModel qBoard = QBoardModel.boardModel;

    @Transactional
    @Override
    public Messenger save(BoardDto dto) {
        log.info(">>> Board Service Save 진입: {}", dto);
        BoardModel result = boardRepository.save(dtoToEntity(dto));

        return Messenger.builder()
                .message(MessageStatus.SUCCESS.name())
                .build();
    }

    @Override
    public Messenger deleteById(Long id) {
        log.info(">>> Board Service Delete 진입: {}", id);
        if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
            return Messenger.builder().message("SUCCESS").build();
        } else {
            return Messenger.builder().message("FAILURE").build();
        }

    }

    @Override
    public List<BoardDto> findAll() {
        return boardRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public Optional<BoardDto> findById(Long id) {
        log.info(">>> Board Service findById 진입: {}", id);
        return boardRepository.findById(id).map(this::entityToDto);
    }


    @Override
    public Boolean existsById(Long id) {
        return boardRepository.existsById(id);
    }


    @Transactional
    @Override
    public Messenger modify(BoardDto dto) {
        log.info(">>> Board Service Modify 진입: {}", dto);
        Long result = queryFactory.update(qBoard)
                .set(qBoard.title, dto.getTitle())
                .set(qBoard.content, dto.getContent())
                .set(qBoard.category, dto.getCategory())
                .where(qBoard.id.eq(dto.getId()))
                .execute();
        log.info(">>> Board modify 결과(Query DSL): {}", result);
        return Messenger.builder()
                .message(MessageStatus.SUCCESS.name())
                .build();
    }


    @Override
    public List<BoardDto> findByTypes(String type) {
        log.info(">>> board findByTypes 진입 : {}", type);
        List<BoardModel> result = queryFactory.selectFrom(qBoard)
                .where(qBoard.type.eq(type))
                .fetch();
        return result.stream().map(this::entityToDto).toList();
    }

    @Override
    public List<BoardDto> findByUserId(Long id) {
        List<BoardModel> result = queryFactory.selectFrom(qBoard)
                .where(qBoard.userId.id.eq(id))
                .fetch();
        return result.stream().map(this::entityToDto).toList();
    }
}
