// AddScheduleReferenceRequest.java
package me.gom.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gom.springbootdeveloper.domain.ScheduleReference;
import me.gom.springbootdeveloper.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddScheduleReferenceRequest {
    private Long userId;
    private LocalDate scheduleDate;
    private LocalDateTime scheduleStartTime;
    private LocalDateTime scheduleEndTime;

    public ScheduleReference toEntity(User user) {
        return ScheduleReference.builder()
                .user(user)
                .scheduleDate(scheduleDate)
                .scheduleStartTime(scheduleStartTime)
                .scheduleEndTime(scheduleEndTime)
                .build();
    }
}
