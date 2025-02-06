// UpdateScheduleReferenceRequest.java
package me.gom.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateScheduleReferenceRequest {
    private LocalDate scheduleDate;
    private LocalDateTime scheduleStartTime;
    private LocalDateTime scheduleEndTime;
}
