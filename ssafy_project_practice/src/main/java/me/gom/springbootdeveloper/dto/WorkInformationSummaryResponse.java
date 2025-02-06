package me.gom.springbootdeveloper.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class WorkInformationSummaryResponse {
    private double totalExtraWorkHours; // 총 추가 근무 시간
    private List<WorkInformationResponse> workInfoList; // 개별 근무 정보 리스트

    public WorkInformationSummaryResponse(double totalExtraWorkHours, List<WorkInformationResponse> workInfoList) {
        this.totalExtraWorkHours = totalExtraWorkHours;
        this.workInfoList = workInfoList;
    }
}
