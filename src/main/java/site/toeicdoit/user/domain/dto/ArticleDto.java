package site.toeicdoit.user.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String type;
    private String answer;
    private String createDate;
    private String updateDate;
}
