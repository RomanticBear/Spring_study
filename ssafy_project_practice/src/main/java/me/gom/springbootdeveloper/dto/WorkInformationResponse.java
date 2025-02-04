package me.gom.springbootdeveloper.dto;

import lombok.Getter;
import me.gom.springbootdeveloper.domain.WorkInformation;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class WorkInformationResponse {
    private LocalDate workDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean worked; // 근무 여부
    private String storeName; // 스토어 이름 추가

    public WorkInformationResponse(WorkInformation workInformation, boolean worked) {
        this.workDate = workInformation.getWorkDate();
        this.startTime = workInformation.getStartTime();
        this.endTime = workInformation.getEndTime();
        this.worked = worked;
        this.storeName = workInformation.getStore().getStoreName();
    }
}
