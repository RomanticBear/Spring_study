package me.gom.springbootdeveloper.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class WorkInformationSummaryResponse {

    private double totalExtraWorkHours; // 총 추가 근무 시간
    private double expectedBonus;       // 예상 추가 수당
    private List<WorkInformationResponse> workInfoList; // 근무 정보 리스트

    public WorkInformationSummaryResponse(double totalExtraWorkHours, List<WorkInformationResponse> workInfoList) {
        this.totalExtraWorkHours = totalExtraWorkHours;
        this.expectedBonus = calculateExpectedBonus(totalExtraWorkHours);  // 예상 추가 수당 계산
        this.workInfoList = workInfoList;
    }

    // 💰 예상 추가 수당 계산 함수 (최저 시급 10,030원)
    private double calculateExpectedBonus(double totalExtraWorkHours) {
        final int MINIMUM_WAGE = 10030;
        return totalExtraWorkHours * MINIMUM_WAGE;
    }
}
