// ScheduleReferenceResponse.java
package me.gom.springbootdeveloper.dto;

import lombok.Getter;
import me.gom.springbootdeveloper.domain.ScheduleReference;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ScheduleReferenceResponse {
    private Long scheduleReferenceId;
    private Long userId;
    private LocalDate scheduleDate;
    private LocalDateTime scheduleStartTime;
    private LocalDateTime scheduleEndTime;

    public ScheduleReferenceResponse(ScheduleReference scheduleReference) {
        this.scheduleReferenceId = scheduleReference.getScheduleReferenceId();
        this.userId = scheduleReference.getUser().getUserId();
        this.scheduleDate = scheduleReference.getScheduleDate();
        this.scheduleStartTime = scheduleReference.getScheduleStartTime();
        this.scheduleEndTime = scheduleReference.getScheduleEndTime();
    }
}