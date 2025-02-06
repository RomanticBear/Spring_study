package me.gom.springbootdeveloper.dto;

import lombok.Getter;
import me.gom.springbootdeveloper.domain.WorkInformation;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class WorkInformationResponse {

    private LocalDate workDate;    // 근무 날짜
    private LocalTime startTime;    // 근무 시작 시간
    private LocalTime endTime;    // 근무 종료 시간
    private boolean worked;   // 근무 여부
    private LocalTime checkInTime;    // 체크인 시간
    private LocalTime checkOutTime;    // 체크아웃 시간
    private LocalTime calStartTime;    // 계산된 출석 시간
    private LocalTime calEndTime;    // 계산된 퇴근 시간
    private String storeName;    // 근무 지점


    // 필요없는 반환 필드 추후 삭제
    public WorkInformationResponse(WorkInformation workInformation, boolean worked,
                                   LocalTime checkInTime, LocalTime checkOutTime, LocalTime calStartTime, LocalTime calEndTime, String storeName) {
        this.workDate = workInformation.getWorkDate();
        this.startTime = workInformation.getStartTime();
        this.endTime = workInformation.getEndTime();
        this.worked = worked;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.calStartTime = calStartTime;
        this.calEndTime = calEndTime;
        this.storeName = storeName;
    }
}
