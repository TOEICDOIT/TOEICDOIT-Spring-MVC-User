package site.toeicdoit.user.domain.model.mysql;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = {"id"})
public class UserModel extends BaseModel{

    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    // 디폴트 이미지 추가
    private String profile;
    private String name;
    private String phone;
    private Integer toeicLevel;
    private String registration;
    private Long subId;

    
    // ====================== user ========================

    @Setter
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<RoleModel> roleModels;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardModel> boardModels;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReplyModel> replyModels;

    // ======================= tx =========================

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<PaymentModel> paymentIds;
    
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<SubscribeModel> subscribeIds;

    @OneToOne(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private CalendarModel calendarId;

    
}
