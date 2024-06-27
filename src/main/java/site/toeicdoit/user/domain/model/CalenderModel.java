package site.toeicdoit.user.domain.model;


import jakarta.persistence.*;
import lombok.*;
import site.toeicdoit.user.domain.BaseModel;

import java.time.LocalDateTime;

@Entity(name = "calender")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = {"id"})
public class CalenderModel extends BaseModel {

    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String allday;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel userId;
}
