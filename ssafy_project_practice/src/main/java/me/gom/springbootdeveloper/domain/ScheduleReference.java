// ScheduleReference.java
package me.gom.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, foreignKey = @ForeignKey(name = "fk_user_id_schedule_ref"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    // 대타 희망 날짜
    private LocalDate scheduleDate;

    // 대타 희망 시작 시간
    private LocalDateTime scheduleStartTime;

    // 대타 희망 종료 시간
    private LocalDateTime scheduleEndTime;
}
