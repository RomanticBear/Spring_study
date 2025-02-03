package me.gom.springbootdeveloper.Service;

import lombok.RequiredArgsConstructor;
import me.gom.springbootdeveloper.domain.WorkInformation;
import me.gom.springbootdeveloper.dto.WorkInformationResponse;
import me.gom.springbootdeveloper.repository.WorkInformationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkInformationService {

    private final WorkInformationRepository workInformationRepository;

    // 특정 유저의 근무 정보 조회
    public List<WorkInformationResponse> getUserWorkInformation(Long userId) {
        List<WorkInformation> workInformationList = workInformationRepository.findAll();

        return workInformationList.stream()
                .filter(info -> info.getUser().getUserId().equals(userId)) // 해당 유저의 정보 필터링
                .filter(info -> LocalDateTime.now().isAfter(info.getWorkDate().atTime(info.getEndTime()))) // 현재 시간 이전의 스케줄만 처리
                .map(info -> {
                    boolean worked = info.getUser().getUserId().equals(info.getRealTimeWorker());
                    return new WorkInformationResponse(info, worked);
                })
                .collect(Collectors.toList());
    }
}
