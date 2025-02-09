package me.gom.springbootdeveloper.dto;

import lombok.Getter;
import me.gom.springbootdeveloper.domain.WorkInformation;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ManagerWorkInformationResponse {

    private LocalDate workDate;  // 근무 날짜
    private LocalTime startTime; // 근무 시작 시간
    private LocalTime endTime;   // 근무 종료 시간
    private boolean isVacant;    // 공석 여부
    private String userName;     // 근무자 이름
    private String storeName;    // 점포 이름
    private String realTimeWorkerName; // 실제 대타 근무자 이름

    public ManagerWorkInformationResponse(WorkInformation workInformation) {
        this.workDate = workInformation.getWorkDate();
        this.startTime = workInformation.getStartTime();
        this.endTime = workInformation.getEndTime();
        this.isVacant = workInformation.getIsVacant();
        this.userName = workInformation.getUser().getUserName();
        this.storeName = workInformation.getStore().getStoreName();
        this.realTimeWorkerName = workInformation.getRealTimeWorker() != null ? workInformation.getRealTimeWorker().toString() : "N/A";
    }
}
