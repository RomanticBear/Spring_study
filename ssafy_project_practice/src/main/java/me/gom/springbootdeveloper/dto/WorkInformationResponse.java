package me.gom.springbootdeveloper.dto;

import lombok.Getter;
import me.gom.springbootdeveloper.domain.WorkInformation;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class WorkInformationResponse {

    // 근무 시간
    private LocalDate workDate;
    private LocalTime startTime;
    private LocalTime endTime;

    // 근무 여부
    private boolean worked;


    // 계산된 시간
    private LocalTime calStartTime;
    private LocalTime calEndTime;

    public WorkInformationResponse(WorkInformation workInformation, boolean worked,
                                   LocalTime calStartTime, LocalTime calEndTime) {
        this.workDate = workInformation.getWorkDate();
        this.startTime = workInformation.getStartTime();
        this.endTime = workInformation.getEndTime();
        this.worked = worked;
        this.calStartTime = calStartTime;
        this.calEndTime = calEndTime;
    }
}
