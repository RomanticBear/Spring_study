package me.gom.springbootdeveloper.Service;

import lombok.RequiredArgsConstructor;
import me.gom.springbootdeveloper.domain.WorkInformation;
import me.gom.springbootdeveloper.dto.WorkInformationResponse;
import me.gom.springbootdeveloper.dto.WorkInformationSummaryResponse;
import me.gom.springbootdeveloper.repository.WorkInformationRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkInformationService {

    private final WorkInformationRepository workInformationRepository;

    public WorkInformationSummaryResponse getUserWorkInformation(Long userId) {
        List<WorkInformation> workInformationList = workInformationRepository.findAll();

        double totalExtraWorkHours = workInformationList.stream()
                .filter(info -> info.getUser().getUserId().equals(userId))
                .filter(info -> LocalDateTime.now().isAfter(info.getWorkDate().atTime(info.getEndTime())))
                .mapToDouble(info -> {
                    boolean worked = info.getUser().getUserId().equals(info.getRealTimeWorker());   // 근무 여부
                    LocalTime calStartTime = calculateCalStartTime(info.getStartTime(), info.getCheckInTime()); // 계산된 근무 출석 시간
                    LocalTime calEndTime = calculateCalEndTime(info.getEndTime(), info.getCheckOutTime());  // 계산된 근무 퇴근 시간
                    return calculateExtraWorkHours(calStartTime, calEndTime, worked); // 추가 근무 시간
                })
                .sum();

        List<WorkInformationResponse> workInfoList = workInformationList.stream()
                .filter(info -> info.getUser().getUserId().equals(userId))
                .filter(info -> LocalDateTime.now().isAfter(info.getWorkDate().atTime(info.getEndTime())))
                .map(info -> {
                    boolean worked = info.getUser().getUserId().equals(info.getRealTimeWorker());   // 근무 여부
                    LocalTime calStartTime = calculateCalStartTime(info.getStartTime(), info.getCheckInTime()); // 계산된 근무 출석 시간
                    LocalTime calEndTime = calculateCalEndTime(info.getEndTime(), info.getCheckOutTime());  // 계산된 근무 퇴근 시간
                    String storeName = info.getStore().getStoreName();  // 근무 지점
                    LocalTime checkInTime = info.getCheckInTime();  // 체크인 시간
                    LocalTime checkOutTime = info.getCheckOutTime();    // 체크아웃 시간

                    double extraWorkHours = calculateExtraWorkHours(calStartTime, calEndTime, worked); // 추가 근무 시간

                    return new WorkInformationResponse(info, worked, checkInTime, checkOutTime, calStartTime, calEndTime, storeName, extraWorkHours);
                })
                .collect(Collectors.toList());

        return new WorkInformationSummaryResponse(totalExtraWorkHours, workInfoList);
    }

    // (지각 고려) 출석 시간 계산 함수
    private LocalTime calculateCalStartTime(LocalTime startTime, LocalTime checkInTime) {
        if (checkInTime == null || checkInTime.isBefore(startTime) || checkInTime.equals(startTime)) {
            return startTime;
        } else {
            return checkInTime.plusHours(1).withMinute(0);
        }
    }

    // (퇴근 고려) 퇴근 시간 계산 함수
    private LocalTime calculateCalEndTime(LocalTime endTime, LocalTime checkOutTime) {
        if (checkOutTime == null || checkOutTime.isAfter(endTime) || checkOutTime.equals(endTime)) {
            return endTime;
        } else {
            return checkOutTime.withMinute(0);
        }
    }

    // 추가 근무 시간 계산 함수 (시간 단위 반환)
    private long calculateExtraWorkHours(LocalTime calStartTime, LocalTime calEndTime, boolean worked) {
        long hours = Duration.between(calStartTime, calEndTime).toHours();
        return worked ? hours : -hours;
    }
}
