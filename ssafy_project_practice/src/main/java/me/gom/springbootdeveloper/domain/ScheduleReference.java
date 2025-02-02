// ScheduleReference.java
package me.gom.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleReference {

    // 희망 근무 참조 일정 고유 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleReferenceId;

    // 대타 희망자
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    // 필요한가 ,,?
    // 있다면 다대다 관계 아닌가 ,,?
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "storeId", nullable = false)
//    private Store store;

    // 대타 희망 날짜
    private LocalDate scheduleDate;

    // 대타 희망 시작 시간
    private LocalDateTime scheduleStartTime;

    // 대타 희망 종료 시간
    private LocalDateTime scheduleEndTime;
}
