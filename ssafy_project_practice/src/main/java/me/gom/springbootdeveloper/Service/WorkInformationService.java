package me.gom.springbootdeveloper.Service;

import lombok.RequiredArgsConstructor;
import me.gom.springbootdeveloper.domain.WorkInformation;
import me.gom.springbootdeveloper.dto.WorkInformationResponse;
import me.gom.springbootdeveloper.repository.WorkInformationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkInformationService {

    private final WorkInformationRepository workInformationRepository;

    public List<WorkInformationResponse> getUserWorkInformation(Long userId) {
        List<WorkInformation> workInformationList = workInformationRepository.findAll();

        return workInformationList.stream()
                .filter(info -> info.getUser().getUserId().equals(userId))
                .filter(info -> LocalDateTime.now().isAfter(info.getWorkDate().atTime(info.getEndTime())))
                .map(info -> {
                    boolean worked = info.getUser().getUserId().equals(info.getRealTimeWorker());

                    // ✅ 계산된 근무 시작 시간 (calStartTime)
                    LocalTime calStartTime = calculateCalStartTime(info.getStartTime(), info.getCheckInTime());

                    // ✅ 계산된 근무 종료 시간 (calEndTime)
                    LocalTime calEndTime = calculateCalEndTime(info.getEndTime(), info.getCheckOutTime());

                    return new WorkInformationResponse(info, worked, calStartTime, calEndTime);
                })
                .collect(Collectors.toList());
    }

    // ⏱️ 계산된 근무 시작 시간
    private LocalTime calculateCalStartTime(LocalTime startTime, LocalTime checkInTime) {
        if (checkInTime == null || checkInTime.isBefore(startTime) || checkInTime.equals(startTime)) {
            return startTime;
        } else {
            return checkInTime.plusHours(1).withMinute(0);
        }
    }

    // ⏱️ 계산된 근무 종료 시간
    private LocalTime calculateCalEndTime(LocalTime endTime, LocalTime checkOutTime) {
        if (checkOutTime == null || checkOutTime.isAfter(endTime) || checkOutTime.equals(endTime)) {
            return endTime;
        } else {
            return checkOutTime.withMinute(0);
        }
    }
}
