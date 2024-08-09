package site.toeicdoit.user.ToeicCategory;

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
public class ToeicCategoryDto {
    private Long id;
    private String title;
    private Boolean take;
}
