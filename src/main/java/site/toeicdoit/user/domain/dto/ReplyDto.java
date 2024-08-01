package site.toeicdoit.user.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDto {
    private Long id;
    private String content;
}
